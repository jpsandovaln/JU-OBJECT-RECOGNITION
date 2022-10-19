package org.jalau.at18.searchobject.controller;
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

    @PostMapping("/emotionRecognition")
    public ResponseEntity <List<String>> readData(@RequestParam("file") MultipartFile file,
                                                        @RequestParam String searchEmotion,
                                                        @RequestParam String occurrencyPercentage) throws IOException {
        // save file, it is saving the zip file and getting the path
        Path path = storageService.save(file);
        System.out.println(path.getParent());
        List<String> test = new ArrayList<>();
        test.add(searchEmotion);
        test.add(occurrencyPercentage);
        // get the route file
        //UnzipFile unzip = new UnzipFile(path);
        //FileSource fileSource = new FileSource(path.toFile().getAbsolutePath());
        /*List<MatchInfo> matchInfos = processFrameService.processFrameAccordingCriteria(unzip.getPath(), searchCriteria,
                occurrencyPercentage,
                modelObjectRecognizer);*/

        //processMatchService.processMatches(matchInfos, notifierType, recipient);

        return ResponseEntity.status(HttpStatus.OK).body(test);
    }
}

