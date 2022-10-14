 
/*
 * Copyright 2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance
 * with the License. A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0/
 *
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
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
 * An example of inference using an object detection model.
 *
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
                System.out.println(imageFile.getFileName());
                System.out.println(detection.getProbabilities());
                Boolean isProbably= false;

                for (Double item: detection.getProbabilities()) {
                    if(item>(scoreFind*0.01)){
                        isProbably= true;
                    }
                }
                if (isProbably){
                    saveBoundingBoxImage(objFind,imageFile.getFileName()+"",img, detection);
                    //return matchInfo(nameFile,score)
                }

                return detection;
            }
        }
    }

    private static void saveBoundingBoxImage(String search, String fileName,Image img, DetectedObjects detection)
            throws IOException {
        Path outputDir = Paths.get("build/output");
        Files.createDirectories(outputDir);

        System.out.println(detection.get(search));

        if (detection.get(search)!= null ){
            System.out.println(detection.get(search).getClassName());
            img.drawBoundingBoxes(detection);
            Path imagePath = outputDir.resolve(fileName);
            img.save(Files.newOutputStream(imagePath), "png");
            logger.info("Detected objects image has been saved in: {}", imagePath);
        }
    }
}