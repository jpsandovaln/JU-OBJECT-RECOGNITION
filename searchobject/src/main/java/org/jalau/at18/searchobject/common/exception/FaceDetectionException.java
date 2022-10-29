/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and proprieraty information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
package org.jalau.at18.searchobject.common.exception;

/**
 * Throws this FaceDetectionException if there is a problem in the fyle or in the type when the faceDetect model is used
 *
 * @author Rodrigo Bernal
 * @version 1.0
 */
public class FaceDetectionException extends Exception {

    public FaceDetectionException(String message) {
        super(message);
    }

    public FaceDetectionException(String message, Throwable ex) {
        super(message,ex);
    }
}
