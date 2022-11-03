package org.jalau.at18.searchobject.model.objectrecognizer.recognizer;
/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and property information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */


/**
 * Enum class with the model available, it can be add more model in the future
 *
 * @author Maria Hurtado
 * @version 1.0
 */


public enum TypeModelRecognizer {
    YOLO("yolo"),
    SSD("ssd"),
    OBJECTDETECTION("objectdetection"),
    RCNN("rcnn");
    private String model;
    /**
     * Constructor to recognize the model
     * @param model type of model
     */
    TypeModelRecognizer(String model) {
        this.model = model;
    }
    /**
     * Method to get the type of model
     */
    public String getModel() {
        return model;
    }
}
