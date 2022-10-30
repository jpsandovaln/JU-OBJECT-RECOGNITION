/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and property information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
package org.jalau.at18.searchobject.common.exception;

/**
 * Throws this EmotionRecognizerException if the emotionrecognizer model fails reading the file or with the httprequest
 *
 * @author Rodrigo Bernal
 * @version 1.0
 */

public class EmotionRecognizerException extends Exception {

    public EmotionRecognizerException(String message) {
        super(message);
    }

    public EmotionRecognizerException(String message, Throwable ex) {
        super(message,ex);
    }
}
