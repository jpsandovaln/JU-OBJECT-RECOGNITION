/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and property information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
package org.jalau.at18.searchobject.controller.response;

/**
 * It is to return a json in the postman with the FaceDetectionController
 *
 * @author Rodrigo Bernal
 * @version 1.0
 */
public class FaceDetectionResponse {
    private String result;

    /**
     * Constructor to the face detection
     * @param result the result of the face detection model
     *
     */

    public FaceDetectionResponse(String result) {
        this.result = result;
    }
    /**
     * Method that returns the result of the face detection model
     * @return the result
     */
    public String getResult() {
        return result;
    }
    /**
     * Method that sets the value of the result
     */
    public void setResult(String result) {
        this.result = result;
    }
}
