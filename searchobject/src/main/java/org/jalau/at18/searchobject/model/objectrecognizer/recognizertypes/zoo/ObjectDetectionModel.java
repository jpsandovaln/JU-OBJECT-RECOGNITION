/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and proprietary information of Jala University
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jala University
 */
package org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes.zoo;
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
import org.jalau.at18.searchobject.common.logger.At18Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.jalau.at18.searchobject.common.logger.At18Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * It is responsible to start the process of the model objectdetection and find the matches
 *
 * @author Jose Romay
 * @version 1.0
 *
 * End the model to integrate with all the models and the spring
 * @author Rodrigo Valda
 *  * @version 2.0
 */
public class ObjectDetectionModel {

    //private static final Logger logger = At18Logger.getLogger();

    private static String imageDir; //image path
    private static String objFind; //object to find in the images
    private static double scoreFind; //score of the object find
    Map<String, Double> dataList = new HashMap<String, Double>(); //save the result
    /**
     * constructor of the class
     */
    private ObjectDetectionModel() {}
    /**
     * method to detect the object
     * @param inputObj object to find
     * @param score of the object fin in the image
     * @param direction image path
     * @throws IOException of a general error
     * @throws  ModelException if the model happen something wrong
     */
    public ObjectDetectionModel(String inputObj, int score, String direction) throws IOException, ModelException, TranslateException {
        this.imageDir=direction;  //image path direction
        this.objFind=inputObj; //object to find
        this.scoreFind=score; //score of the object find it
        double average = 0; //average of the all result
        double sumProbabilities = 0; //summatory of the probabilities
        String fileName = ""; //filename of every single image in the path
        //split the direction of the images in the path to take only the name and the extendion
        fileName = imageDir.split("\\\\")[imageDir.split("\\\\").length-1];
        //start the method detectedobject
        DetectedObjects detection = ObjectDetectionModel.predict();

        //logger.info("{}", detection);

        //if there is the object of search in the image
        if (objFind.equals(detection.getClassNames().get(0))) {
            for (int index = 0; index < detection.getNumberOfObjects(); index++) {
                //summatory of the probabilities if the object it's detected
                sumProbabilities += (Double.parseDouble(detection.getProbabilities().get(index).toString()) * 100);
            }
            //average of all the result
            average = sumProbabilities/detection.getNumberOfObjects();

        } else {
            //average when the score it's 0
            average = 0;
        }
        //save the result
        dataList.put(fileName,average);
    }
    /**
     * method to detect the object
     *
     * @throws IOException of a general error
     * @throws  ModelException if the model happen something wrong
     * @throws  TranslateException if something get wrong in decode the image
     */
    public static DetectedObjects predict() throws IOException, ModelException, TranslateException {
        //direction of the path
        Path imageFile = Paths.get(imageDir);
        //single image
        Image img = ImageFactory.getInstance().fromFile(imageFile);

        //determine the arquitecture
        String backbone;
        //using the Tensorflow model
        if ("TensorFlow".equals(Engine.getDefaultEngineName())) {
            backbone = "mobilenet_v2"; //arquitecture
        } else {
            backbone = "resnet50"; //arquitecture
        }
        //start the process to find the match, with the process of the arquitecture
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
                            break;
                        }
                    }
                }

                return detection;
            }
        }
    }

    public Map<String, Double> getDataList() {
        return dataList;
    }
}
