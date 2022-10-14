package org.jalau.at18.searchobject.service;

import org.jalau.at18.searchobject.model.MatchInfo;
import org.jalau.at18.searchobject.model.ModelRecognizer;
import org.jalau.at18.searchobject.model.VerifyModelRecognizer;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
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
        /*
        List<MatchInfo> matches = new ArrayList<>();
        matches.add(new MatchInfo("60.png", 82.9));
        matches.add(new MatchInfo("80.png", 90.9));
        matches.add(new MatchInfo("120.png", 85.9));
        matches.add(new MatchInfo("152.png", 93.2));
        return matches;

         */
    }
}
