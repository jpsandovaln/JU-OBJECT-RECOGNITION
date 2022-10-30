/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and property information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
package org.jalau.at18.searchobject.controller.service;


import org.jalau.at18.searchobject.common.exception.EmotionRecognizerException;
import org.jalau.at18.searchobject.model.emotionrecognizer.EmotionRecognizer;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

/**
 *
 *
 * @throws EmotionRecognizerException if there is an error reading the file or with the http request
 */

@Service
public class EmotionRecognitionService {
    public String[] processImage(Path pathImage, String token) throws EmotionRecognizerException {
        EmotionRecognizer emotionRecognizer = new EmotionRecognizer(pathImage.toString(), token);
        return emotionRecognizer.getResult();
    }
}
