/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and proprietary information of Jala University
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jala University
 */
package org.jalau.at18.searchobject.model.objectDetection;
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

<<<<<<< HEAD
<<<<<<< HEAD
import org.jalau.at18.searchobject.common.logger.At18Logger;
=======
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
>>>>>>> be98d6e (ZooModel integration and add new logic to merge with the MatchInfo)
=======
import org.jalau.at18.searchobject.common.logger.At18Logger;
<<<<<<< HEAD
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
>>>>>>> 15f7662 (Create common folder and add at18logger class, remplace logger of the ObjectDetectionModel to the at18logger)
=======
>>>>>>> 786beda (Delete unnecesary comments)

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
<<<<<<< HEAD
<<<<<<< HEAD
import java.util.logging.Logger;


public class ObjectDetectionModel {
    //private static final Logger logger = At18Logger.getLogger();
=======


public class ObjectDetectionModel {
    private static final Logger logger = LoggerFactory.getLogger(ObjectDetectionModel.class);
>>>>>>> be98d6e (ZooModel integration and add new logic to merge with the MatchInfo)
=======
import java.util.logging.Logger;


public class ObjectDetectionModel {
    private static final Logger logger = At18Logger.getLogger();
>>>>>>> 15f7662 (Create common folder and add at18logger class, remplace logger of the ObjectDetectionModel to the at18logger)
    private static String imageDir;
    private static String objFind;
    private static double scoreFind;
    Map<String, Double> dataList = new HashMap<String, Double>();

    private ObjectDetectionModel() {}

    public ObjectDetectionModel(String inputObj, int score, String direction) throws IOException, ModelException, TranslateException {
        this.imageDir=direction;
        this.objFind=inputObj;
        this.scoreFind=score;
        double average = 0;
        double sumProbabilities = 0;
        String fileName = "";

        fileName = imageDir.split("\\\\")[imageDir.split("\\\\").length-1];
        DetectedObjects detection = ObjectDetectionModel.predict();
<<<<<<< HEAD
<<<<<<< HEAD
        //logger.info("{}", detection);
        //logger.info("{}");
=======
        logger.info("{}", detection);
>>>>>>> be98d6e (ZooModel integration and add new logic to merge with the MatchInfo)
=======
        //logger.info("{}", detection);
        logger.info("{}");
>>>>>>> 15f7662 (Create common folder and add at18logger class, remplace logger of the ObjectDetectionModel to the at18logger)
        if (objFind.equals(detection.getClassNames().get(0))) {
            for (int index = 0; index < detection.getNumberOfObjects(); index++) {
                sumProbabilities += (Double.parseDouble(detection.getProbabilities().get(index).toString()) * 100);
            }
            average = sumProbabilities/detection.getNumberOfObjects();

        } else {
            average = 0;
        }
        dataList.put(fileName,average);
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
<<<<<<< HEAD
                DetectedObjects detection = predictor.predict(img);
                if (detection.get(objFind)!= null) {
                    for (Double item : detection.getProbabilities()) {
                        if (item > (scoreFind * 0.01)) {
=======

                DetectedObjects detection = predictor.predict(img);




                if (detection.get(objFind)!= null) {
                    for (Double item : detection.getProbabilities()) {
                        if (item > (scoreFind * 0.01)) {
                            //System.out.println("frame: " + imageFile.getFileName() + " -> score: " + detection.getProbabilities());
                            //return matchInfo(imageFile.getFileName(),score)
>>>>>>> be98d6e (ZooModel integration and add new logic to merge with the MatchInfo)
                            break;
                        }
                    }
                }
<<<<<<< HEAD
=======

>>>>>>> be98d6e (ZooModel integration and add new logic to merge with the MatchInfo)
                return detection;
            }
        }
    }
<<<<<<< HEAD
=======

>>>>>>> be98d6e (ZooModel integration and add new logic to merge with the MatchInfo)
    public Map<String, Double> getDataList() {
        return dataList;
    }
}
