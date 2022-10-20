package org.jalau.at18.searchobject.controller;
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
public class EmotionRecognitionController{
    @Autowired
    FilesStorageService storageService;

    @Autowired
    EmotionRecognitionService emotionRecognitionService;
    @PostMapping("/emotionRecognition")
    public ResponseEntity <List<String>> readData(@RequestParam("file") MultipartFile file) throws IOException {
        Path path = storageService.save(file);
        List<String> data = emotionRecognitionService.processImage(path.getParent());
        return ResponseEntity.status(HttpStatus.OK).body(data);
    }
}

