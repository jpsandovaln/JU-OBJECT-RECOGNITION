package org.jalau.at18.searchobject.service;

import org.jalau.at18.searchobject.model.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProcessFrameService {
    // The modelo needs to analize each frame, according the criteria
    // should be returned a list of frames that match with the criteria
    public List<MatchInfo> processFrameAccordingCriteria(Path zipFilePath,
                                                         String searchCriteria,
                                                         int occurrencyPercentage,
                                                         String modelObjectRecognizer) {

        VerifyModelRecognizer verifyModelRecognizer = new VerifyModelRecognizer();
        ModelRecognizer modelRecognizer = verifyModelRecognizer.getModelRecognizer(modelObjectRecognizer);
        Path unzipFolderPath = null;
        //UnzipUtil.unzip(zipFilePath, unzipFolderPath);
        List<MatchInfo> matchInfos = modelRecognizer.matching(unzipFolderPath, searchCriteria, occurrencyPercentage);
        return matchInfos;

        // to be implemented by
        // Mauricio, Libertad, and Jose
        // this is only an example to know that should return the result of frames that match with the criteria

        //List<MatchInfo> matches = new ArrayList<>();
        //matches.add(new MatchInfo( new FrameInfo("25.png", LocalTime.of(0, 5, 3))));
        //return matches;
    }
}
