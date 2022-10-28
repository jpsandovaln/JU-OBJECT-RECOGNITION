/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and proprieraty information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
package org.jalau.at18.searchobject.controller.endpoint;
import org.jalau.at18.searchobject.common.exception.EmotionRecognizerException;
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

@RestController
public class EmotionRecognitionController{
    @Autowired
    FilesStorageService storageService;
    @Autowired
    EmotionRecognitionService emotionRecognitionService;
    @PostMapping("/emotionRecognition")
    public ResponseEntity <String[]> readData(@RequestParam("file") MultipartFile file,
                                               @RequestParam String token)  {
        try {
            Path path = storageService.save(file);
            String[] data = emotionRecognitionService.processImage(path.toAbsolutePath(), token);
            return ResponseEntity.status(HttpStatus.OK).body(data);
        } catch (EmotionRecognizerException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }
}

