/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and proprieraty information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
package org.jalau.at18.searchobject.controller.endpoint;

import org.jalau.at18.searchobject.common.exception.ModelRecognizerTypeException;
import org.jalau.at18.searchobject.common.exception.NotifierTypeException;
import org.jalau.at18.searchobject.common.exception.UnzipFileException;
import org.jalau.at18.searchobject.common.logger.At18Logger;
import org.jalau.at18.searchobject.controller.service.FilesStorageService;
import org.jalau.at18.searchobject.controller.service.ProcessFrameService;
import org.jalau.at18.searchobject.controller.service.ProcessMatchService;
import org.jalau.at18.searchobject.model.objectrecognizer.UnzipFile;
import org.jalau.at18.searchobject.model.objectrecognizer.recognizer.ModelRecognizer;
import org.jalau.at18.searchobject.model.objectrecognizer.recognizer.SSD;
import org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes.FileSource;
import org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes.MatchInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class ProcessFrameController {

    //private static final Logger LOG = At18Logger.getLogger();

    @Autowired
    FilesStorageService storageService;
    @Autowired
    ProcessFrameService processFrameService;
    @Autowired
    ProcessMatchService processMatchService;

    @PostMapping("/processFrame")
    public ResponseEntity<List<MatchInfo>> readDataCriteriaFrame(@RequestParam("file") MultipartFile file,
                                                                 @RequestParam String searchCriteria,
                                                                 @RequestParam int occurrencyPercentage,
                                                                 @RequestParam String modelObjectRecognizer,
                                                                 @RequestParam String notifierType,
                                                                 @RequestParam String recipient) throws IOException {

        try {
            // save file, it is saving the zip file and getting the path
            Path path = storageService.save(file);
            //LOG.info(path.toString());


            // get the route file
            UnzipFile unzip = new UnzipFile(path);
            List<MatchInfo> matchInfos = processFrameService.processFrameAccordingCriteria(unzip.getPath(), searchCriteria,
                    occurrencyPercentage,
                    modelObjectRecognizer);

            processMatchService.processMatches(matchInfos, notifierType, recipient);
            return ResponseEntity.status(HttpStatus.OK).body(matchInfos);
        } catch (ModelRecognizerTypeException e) {
            System.out.println(e.getMessage());
        } catch (NotifierTypeException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
