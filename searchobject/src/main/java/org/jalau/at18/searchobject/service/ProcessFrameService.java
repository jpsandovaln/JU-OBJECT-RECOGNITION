package org.jalau.at18.searchobject.service;

import org.jalau.at18.searchobject.model.MatchInfo;
import org.jalau.at18.searchobject.modelrecognizer.ModelRecognizer;
import org.jalau.at18.searchobject.modelrecognizer.VerifyModelRecognizer;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ProcessFrameService {
    public List<MatchInfo> processFrameAccordingCriteria(Path zipFilePath,
                                                         String searchCriteria,
                                                         int occurrencyPercentage,
                                                         String modelObjectRecognizer) {
        // Verifying the model
        VerifyModelRecognizer verifyModelRecognizer = new VerifyModelRecognizer();
        ModelRecognizer modelRecognizer = verifyModelRecognizer.getModelRecognizer(modelObjectRecognizer);
        //System.out.println(zipFilePath);
        // Here we need unzip the folder with frames(images) and after return the path of the folder
        // UnzipUtil.unzip(zipFilePath, unzipFolderPath);
        //Path unzipFolderPath = Paths.get("unzip");
        //Path unzipFolderPath = Paths.get("D:\\Object\\JU-OBJECT-RECOGNITION\\images");

        List<MatchInfo> matchInfos = modelRecognizer.matching(zipFilePath, searchCriteria, occurrencyPercentage);
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
