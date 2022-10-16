/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and proprietary information of Jala University
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jala University
 */

import ai.djl.Application;
import ai.djl.ModelException;
import ai.djl.engine.Engine;
import ai.djl.inference.Predictor;
import ai.djl.modality.cv.Image;
import ai.djl.modality.cv.ImageFactory;
import ai.djl.modality.cv.output.DetectedObjects;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.training.util.ProgressBar;
import ai.djl.translate.TranslateException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Performs a search for the object within the image,
 * and what percentage of probability exists.
 *
 * @author Jose Romay
 * @version 1.0
 */
public class ObjectDetection {

    private static final Logger logger = LoggerFactory.getLogger(ObjectDetection.class);
    private static String imageDir;
    private static String objFind;
    private static double scoreFind;


    private ObjectDetection() {}

    public ObjectDetection(String inputObj,int score,String direction) throws IOException, ModelException, TranslateException {
        this.imageDir=direction;
        this.objFind=inputObj;
        this.scoreFind=score;

        DetectedObjects detection = ObjectDetection.predict();
        logger.info("{}", detection);
    }

    public static DetectedObjects predict() throws IOException, ModelException, TranslateException {

        Path imageFile = Paths.get(imageDir);
        Image img = ImageFactory.getInstance().fromFile(imageFile);

        String backbone;
        if ("TensorFlow".equals(Engine.getDefaultEngineName())) {
            backbone = "mobilenet_v2";
        } else {
            backbone = "resnet50";
        }
        Criteria<Image, DetectedObjects> criteria =
                Criteria.builder()
                        .optApplication(Application.CV.OBJECT_DETECTION)
                        .setTypes(Image.class, DetectedObjects.class)
                        .optFilter("backbone", backbone)
                        .optEngine(Engine.getDefaultEngineName())
                        .optProgress(new ProgressBar())
                        .build();

        try (ZooModel<Image, DetectedObjects> model = criteria.loadModel()) {
            try (Predictor<Image, DetectedObjects> predictor = model.newPredictor()) {

                DetectedObjects detection = predictor.predict(img);




                if (detection.get(objFind)!= null) {
                    for (Double item : detection.getProbabilities()) {
                        if (item > (scoreFind * 0.01)) {
                            System.out.println("frame: " + imageFile.getFileName() + " -> score: " + detection.getProbabilities());
                            //return matchInfo(imageFile.getFileName(),score)
                            break;
                        }
                    }
                }

                return detection;
            }
        }
    }
}