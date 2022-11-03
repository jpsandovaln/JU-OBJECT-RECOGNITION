/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and property information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
package org.jalau.at18.searchobject.model.objectrecognizer.recognizer;
import org.jalau.at18.searchobject.common.exception.ObjectRecognizerException;
import org.jalau.at18.searchobject.common.logger.At18Logger;
import java.util.logging.Logger;

/**
 * Method identifies the type of model chosen to detect an object
 * @throws ObjectRecognizerException if the model recognizer type is not one of the available recognizers
 */
public class VerifyModelRecognizer {
    Logger log = At18Logger.getLogger();
    /**
     * @param modelRecognizer is the chosen model to execute the object detection
     */
    public ModelRecognizer getModelRecognizer(String modelRecognizer) throws ObjectRecognizerException {
        if (modelRecognizer.equals(TypeModelRecognizer.YOLO.getModel())) {
            log.info("Model selected: YOLO");
            return new Yolo();
        }
        if (modelRecognizer.equals(TypeModelRecognizer.SSD.getModel())) {
            log.info("Model selected: SSD");
            return new SSD();
        }
        if (modelRecognizer.equals(TypeModelRecognizer.OBJECTDETECTION.getModel())) {
            log.info("Model selected: OBJECTDETECTION");
            return new ObjectDetection();
        }
        if (modelRecognizer.equals(TypeModelRecognizer.RCNN.getModel())) {
            log.info("Model selected: RCNN");
            return new RCNN();
        }
        throw new ObjectRecognizerException("The model recognizer type is not avaiable");
    }
}
