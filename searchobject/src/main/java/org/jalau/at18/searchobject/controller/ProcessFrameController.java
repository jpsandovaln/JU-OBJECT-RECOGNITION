package org.jalau.at18.searchobject.controller;

import org.jalau.at18.searchobject.model.FileSource;
import org.jalau.at18.searchobject.model.MatchInfo;
import org.jalau.at18.searchobject.service.FilesStorageService;
import org.jalau.at18.searchobject.service.ProcessFrameService;
import org.jalau.at18.searchobject.service.ProcessMatchService;
import org.jalau.at18.searchobject.service.ProcessVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

@RestController
public class ProcessFrameController {
    @Autowired
    FilesStorageService storageService;
    @Autowired
    ProcessVideoService processVideoService;
    @Autowired
    ProcessFrameService processFrameService;
    @Autowired
    ProcessMatchService processMatchService;

    @PostMapping("/processFrame")
    public ResponseEntity<List<MatchInfo>> readDataCriteriaFrame(@RequestParam("file") MultipartFile file,
                                                                 @RequestParam String searchCriteria,
                                                                 @RequestParam int occurrencyPercentage,
                                                                 @RequestParam String modelObjectRecognizer) {
        // save file, in this case now we must read a folder compress with the drames
        // at the same time, we got the path where the folder is saved
        Path path = storageService.save(file);
        // get the route file
        FileSource fileSource = new FileSource(path.toFile().getAbsolutePath());

        List<MatchInfo> matchInfos = processFrameService.processFrameAccordingCriteria(path, searchCriteria,
                occurrencyPercentage,
                modelObjectRecognizer);

        processMatchService.processMatches(matchInfos);

        return ResponseEntity.status(HttpStatus.OK).body(matchInfos);
    }
}
