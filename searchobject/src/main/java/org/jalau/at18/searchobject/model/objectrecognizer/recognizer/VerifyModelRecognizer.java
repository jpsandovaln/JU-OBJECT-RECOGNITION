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
 * It is responsible for verify if the model introduce it's correct
 *
 * @author Maria Hurtado
 * @version 1.0
 */

public class VerifyModelRecognizer {
    Logger log = At18Logger.getLogger(); //show the message in console

    /**
     * get the model and start the process of recognition
     * @param modelRecognizer the model input by user it's one of the selected
     * @throws ObjectRecognizerException if the model recognizer type is not one of the available recognizers
     */
    public ModelRecognizer getModelRecognizer(String modelRecognizer) throws ObjectRecognizerException {
        //if model it's same as Yolo model
        if (modelRecognizer.equals(TypeModelRecognizer.YOLO.getModel())) {
            log.info("Model selected: YOLO");
            return new Yolo();
        }
        //if model it's same as ssd model
        if (modelRecognizer.equals(TypeModelRecognizer.SSD.getModel())) {
            log.info("Model selected: SSD");
            return new SSD();
        }
        //if model it's same as objectdetection model
        if (modelRecognizer.equals(TypeModelRecognizer.OBJECTDETECTION.getModel())) {
            log.info("Model selected: OBJECTDETECTION");
            return new ObjectDetection();
        }
        //if model it's same as rcnn model
        if (modelRecognizer.equals(TypeModelRecognizer.RCNN.getModel())) {
            log.info("Model selected: RCNN");
            return new RCNN();
        }
        throw new ObjectRecognizerException("The model recognizer type is not avaiable");
    }
}
