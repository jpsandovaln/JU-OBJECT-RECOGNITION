package org.jalau.at18.searchobject.controller;
import org.jalau.at18.searchobject.model.MatchInfo;
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
    public ResponseEntity <String[]> readData(@RequestParam("file") MultipartFile file,
                                               @RequestParam String token) throws IOException {
        Path path = storageService.save(file);
        String[] data = emotionRecognitionService.processImage(path.toAbsolutePath(), token);
        return ResponseEntity.status(HttpStatus.OK).body(data);
    }
}

