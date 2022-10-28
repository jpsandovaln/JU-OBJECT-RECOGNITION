package org.jalau.at18.searchobject.controller.endpoint;

/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and proprieraty information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */

import org.jalau.at18.searchobject.controller.service.FilesStorageService;
import org.jalau.at18.searchobject.model.facedetector.FaceDetect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

/**
 * It is responsible to send the information to the postman.
 *
 * @author Daniela Martinez
 * @version 1.0
 */

@RestController
public class FaceDetectionController{

    //Save the file upload in the postman
    @Autowired
    FilesStorageService storageService;

    /**

     * Method that will send the data that will be needed to set in the postman by the user

     * @return The result after the analyze of the image after calling the class Face Detect

     */

    /**

     * PostMapping

     * @param file it's the image that user will upload to analyze
     *
     * @param type the type of face that we want to detec

     */
    @PostMapping("/faceDetection")  //direction of the localhost where we are going to set the info
    public ResponseEntity <String> readData(@RequestParam("file") MultipartFile file,
                                            @RequestParam("type") String type) throws IOException {
        Path path = storageService.save(file);  //Save the upload image
        String fileName = path.toString(); //convert the path direction to an string
        FaceDetect imageAnalize = new FaceDetect(fileName, type); //initialize the class where we are going to analyze the image
        return ResponseEntity.status(HttpStatus.OK).body(imageAnalize.getCommand());
    }
}
