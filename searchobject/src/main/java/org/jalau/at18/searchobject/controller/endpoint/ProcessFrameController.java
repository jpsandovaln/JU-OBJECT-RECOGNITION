package org.jalau.at18.searchobject.controller.endpoint;

import org.jalau.at18.searchobject.UnzipFile;
import org.jalau.at18.searchobject.common.logger.At18Logger;
import org.jalau.at18.searchobject.controller.service.FilesStorageService;
import org.jalau.at18.searchobject.controller.service.ProcessFrameService;
import org.jalau.at18.searchobject.controller.service.ProcessMatchService;
import org.jalau.at18.searchobject.model.FileSource;
import org.jalau.at18.searchobject.model.MatchInfo;
import org.jalau.at18.searchobject.modelrecognizer.ModelRecognizer;
import org.jalau.at18.searchobject.modelrecognizer.SSD;
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
    }
}
