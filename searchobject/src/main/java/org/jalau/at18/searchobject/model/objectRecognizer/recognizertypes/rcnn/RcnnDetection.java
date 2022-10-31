package org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes.rcnn;
/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and property information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */

import ai.djl.Application;
import ai.djl.Model;
import ai.djl.ModelException;
import ai.djl.engine.Engine;
import ai.djl.inference.Predictor;
import ai.djl.modality.cv.Image;
import ai.djl.modality.cv.ImageFactory;
import ai.djl.modality.cv.output.BoundingBox;
import ai.djl.modality.cv.output.DetectedObjects;
import ai.djl.modality.cv.output.Rectangle;
import ai.djl.modality.cv.util.NDImageUtils;
import ai.djl.ndarray.NDArray;
import ai.djl.ndarray.NDList;
import ai.djl.ndarray.NDManager;
import ai.djl.ndarray.types.DataType;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ModelZoo;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.training.util.ProgressBar;
import ai.djl.translate.Batchifier;
import ai.djl.translate.TranslateException;
import ai.djl.translate.Translator;
import ai.djl.translate.TranslatorContext;
import ai.djl.util.JsonUtils;
import com.google.gson.annotations.SerializedName;

import org.jalau.at18.searchobject.common.exception.FaceDetectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Recognizes objects in images accoridng to type of object and score
 *
 * @author Fernanda Aguilar
 * @version 1.0
 */

public class RcnnDetection {
    private static final Logger logger = LoggerFactory.getLogger(RcnnDetection.class);
    private Map<String, Double> infoList = new HashMap<String, Double>();
    private static String imagePat;
    private static String objFind;
    private static double percent;
    static int count = 0;

    /**
     * Constructor for Object detection with model Faster Rcnn Inception
     * @param inputObj determines the object that it will look for
     * @param prob represents the minimum prob that object must have in order to be considered
     * @param path image path
     */
    public RcnnDetection(String inputObj, int prob, String path) throws ModelException, TranslateException, IOException {
        this.imagePat=path;
        this.objFind=inputObj;
        this.percent=prob;
        String fileName = "";
        double probAverage = 0;
        double probTotal = 0;

        fileName = imagePat.split("\\\\")[imagePat.split("\\\\").length-1];
        DetectedObjects detection = RcnnDetection.predict();
        if (objFind.equals(detection.getClassNames().get(0))) {
            for (int index = 0; index < detection.getNumberOfObjects(); index++) {
                probTotal += (Double.parseDouble(detection.getProbabilities().get(index).toString()) * 100);
            }
            probAverage = probTotal/detection.getNumberOfObjects();

        } else {
            probAverage = 0;
        }
        infoList.put(fileName,probAverage);
    }

    public static DetectedObjects predict() throws IOException, ModelException, TranslateException {
        System.out.println(Engine.getInstance().getEngineName());
        if (!"TensorFlow".equals(Engine.getInstance().getEngineName())) {
            return null;
        }
        Path imageFile = Paths.get(imagePat);
        Image img = ImageFactory.getInstance().fromFile(imageFile);
        String modelUrl =
                "http://download.tensorflow.org/models/object_detection/faster_rcnn_inception_v2_coco_2018_01_28.tar.gz";

        Criteria<Image, DetectedObjects> criteria =
                Criteria.builder()
                        .optApplication(Application.CV.OBJECT_DETECTION)
                        .setTypes(Image.class, DetectedObjects.class)
                        .optModelUrls(modelUrl)
                        // saved_model.pb file is in the subfolder of the model archive file
                        .optModelName("faster_rcnn_inception_v2_coco_2018_01_28/saved_model")
                        .optTranslator(new MyTranslator())
                        .optProgress(new ProgressBar())
                        .build();
        try (ZooModel<Image, DetectedObjects> model = ModelZoo.loadModel(criteria);
             Predictor<Image, DetectedObjects> predictor = model.newPredictor()) {
            DetectedObjects detection = predictor.predict(img);
            return detection;
        }
    }


    static Map<Integer, String> loadSynset() throws IOException {
        URL synsetUrl =
                new URL(
                        "https://raw.githubusercontent.com/tensorflow/models/master/research/object_detection/data/mscoco_label_map.pbtxt");
        Map<Integer, String> map = new ConcurrentHashMap<>();
        int maxId = 0;
        try (InputStream is = synsetUrl.openStream();
             Scanner scanner = new Scanner(is, StandardCharsets.UTF_8.name())) {
            scanner.useDelimiter("item ");
            while (scanner.hasNext()) {
                String content = scanner.next();
                content = content.replaceAll("(\"|\\d)\\n\\s", "$1,");
                Item item = JsonUtils.GSON.fromJson(content, Item.class);
                map.put(item.id, item.displayName);
                if (item.id > maxId) {
                    maxId = item.id;
                }
            }
        }
        return map;
    }

    private static final class Item {
        int id;

        @SerializedName("display_name")
        String displayName;
    }

    private static final class MyTranslator implements Translator<Image, DetectedObjects> {

        private Map<Integer, String> classes;
        private int maxBoxes;
        private float threshold;

        MyTranslator() {
            maxBoxes = 10;
            threshold = 0.7f;
        }

        @Override
        public NDList processInput(TranslatorContext ctx, Image input) {
            // list of tensors
            NDArray array = input.toNDArray(ctx.getNDManager(), Image.Flag.COLOR);
            // Resizes image for faster processing
            array = NDImageUtils.resize(array, 224);
            array = array.toType(DataType.UINT8, true);
            array = array.expandDims(0);
            return new NDList(array);
        }

        public void prepare(NDManager manager, Model model) throws IOException {
            if (classes == null) {
                classes = loadSynset();
            }
        }

        @Override
        public DetectedObjects processOutput(TranslatorContext ctx, NDList list) {
            int[] classIds = null;
            float[] probabilities = null;
            NDArray boundingBoxes = null;
            for (NDArray array : list) {
                if ("detection_boxes".equals(array.getName())) {
                    boundingBoxes = array.get(0);
                } else if ("detection_scores".equals(array.getName())) {
                    probabilities = array.get(0).toFloatArray();
                } else if ("detection_classes".equals(array.getName())) {
                    classIds = array.get(0).toType(DataType.INT32, true).toIntArray();
                }
            }
            Objects.requireNonNull(classIds);
            Objects.requireNonNull(probabilities);
            Objects.requireNonNull(boundingBoxes);
            Map<String,Double> infoList = new HashMap<>();
            List<String> retNames = new ArrayList<>();
            List<Double> retProbs = new ArrayList<>();
            List<BoundingBox> retBB = new ArrayList<>();

            // Sorts Results
            for (int i = 0; i < Math.min(classIds.length, maxBoxes); ++i) {
                int classId = classIds[i];
                double probability = probabilities[i];
                if (classId > 0 && probability > threshold) {
                    String className = classes.getOrDefault(classId, "#" + classId);
                    float[] box = boundingBoxes.get(i).toFloatArray();
                    float yMin = box[0];
                    float xMin = box[1];
                    float yMax = box[2];
                    float xMax = box[3];
                    Rectangle rect = new Rectangle(xMin, yMin, xMax - xMin, yMax - yMin);
                    retNames.add(className);
                    retProbs.add(probability);
                    retBB.add(rect);
                    if(className == objFind && probability > percent && count < 1){
                        infoList.put(className, probability);
                        count++;
                    }

                }
            }

            return new DetectedObjects(retNames, retProbs, retBB);
        }

        @Override
        public Batchifier getBatchifier() {
            return null;
        }
    }
    public Map<String, Double> getInfoList() {
        return infoList;
    }

}

