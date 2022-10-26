package org.jalau.at18.searchobject.controller;
import org.jalau.at18.searchobject.model.MatchInfo;
import org.jalau.at18.searchobject.model.facedetector.FaceDetect;
import org.jalau.at18.searchobject.service.EmotionRecognitionService;
import org.jalau.at18.searchobject.service.FilesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FaceDetectionController{
    @Autowired
    FilesStorageService storageService;

    @PostMapping("/faceDetection")
    public ResponseEntity <String> readData(@RequestParam("file") MultipartFile file,
                                            @RequestParam("type") String type) throws IOException {
        //String fileName = storageService.save(file);
        Path path = storageService.save(file);
        System.out.println("imagen   " + path);
        String fileName = path.toString();
        System.out.println("filename" + fileName);
        FaceDetect imageconverter = new FaceDetect(fileName, type);
        return ResponseEntity.status(HttpStatus.OK).body(imageconverter.getCommand());
    }
}
