/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and property information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
package org.jalau.at18.searchobject.controller.endpoint;

import org.jalau.at18.searchobject.common.exception.ObjectRecognizerException;
import org.jalau.at18.searchobject.common.exception.NotifierTypeException;
import org.jalau.at18.searchobject.common.exception.UnzipFileException;
import org.jalau.at18.searchobject.controller.response.ErrorResponse;
import org.jalau.at18.searchobject.controller.service.FilesStorageService;
import org.jalau.at18.searchobject.controller.service.ProcessFrameService;
import org.jalau.at18.searchobject.controller.service.ProcessMatchService;
import org.jalau.at18.searchobject.model.objectrecognizer.UnzipFile;
import org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes.MatchInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.nio.file.Path;
import java.util.List;
/**
 * Send the parameters in POSTMAN and with the data start the process of model recognition
 *
 * @author Maria Hurtado
 * @version 1.0
 */
@RestController
public class ProcessFrameController {

    //storage the file
    @Autowired
    FilesStorageService storageService;
    //process frame with match result
    @Autowired
    ProcessFrameService processFrameService;
    //process match service
    @Autowired
    ProcessMatchService processMatchService;
    /**
     *
     *method to process the matches result
     *@param file file upload by user
     *@param searchCriteria object to search
     *@param occurrencyPercentage score match
     *@param modelObjectRecognizer type of model recognizer
     *@param notifierType whatsapp
     *@param recipient cellphone number
     */
    @PostMapping("/processFrame")
    public ResponseEntity readDataCriteriaFrame(@RequestParam("file") MultipartFile file,
                                                @RequestParam String searchCriteria,
                                                @RequestParam int occurrencyPercentage,
                                                @RequestParam String modelObjectRecognizer,
                                                @RequestParam String notifierType,
                                                @RequestParam String recipient)  {

        try {
            // save file, it is saving the zip file and getting the path
            Path path = storageService.save(file);
            //LOG.info(path.toString());


            // get the route file
            UnzipFile unzip = new UnzipFile(path);
            //save the result in the list marchinfos
            List<MatchInfo> matchInfos = processFrameService.processFrameAccordingCriteria(unzip.getPath(), searchCriteria,
                    occurrencyPercentage,
                    modelObjectRecognizer);

            //process to send the notification
            processMatchService.processMatches(matchInfos, notifierType, recipient);
            return ResponseEntity.status(HttpStatus.OK).body(matchInfos);
        } catch (UnzipFileException | ObjectRecognizerException | NotifierTypeException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse("400",e.getMessage()));
        }
    }
}