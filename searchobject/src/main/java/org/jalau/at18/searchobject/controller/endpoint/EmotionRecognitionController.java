/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and property information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
package org.jalau.at18.searchobject.controller.endpoint;
import org.jalau.at18.searchobject.common.exception.EmotionRecognizerException;
import org.jalau.at18.searchobject.controller.response.ErrorResponse;
import org.jalau.at18.searchobject.controller.service.EmotionRecognitionService;
import org.jalau.at18.searchobject.controller.service.FilesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
/**
 *Endpoint of the service of the API the google for emotion recognition
 *
 * @author Alvaro Sivila
 * @version 1.0
 */
@RestController
public class EmotionRecognitionController{
    @Autowired
    FilesStorageService storageService;  //storage file
    @Autowired
    EmotionRecognitionService emotionRecognitionService;  //emotion recognition service
    /**
     *Method to take the data from the data send for postman
     * @param file upload image for analyze
     * @param token access to the service
     * @throws EmotionRecognizerException if the file or the token it's incorrect and can't continue with the process
     */
    @PostMapping("/emotionRecognition")
    public ResponseEntity readData(@RequestParam("file") MultipartFile file,
                                               @RequestParam String token)  {
        try {
            Path path = storageService.save(file);//save file
            //process the image to be analyze
            String[] data = emotionRecognitionService.processImage(path.toAbsolutePath(), token);
            //return the result
            return ResponseEntity.status(HttpStatus.OK).body(data);
        } catch (EmotionRecognizerException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse("400",e.getMessage()));
        }

    }
}

