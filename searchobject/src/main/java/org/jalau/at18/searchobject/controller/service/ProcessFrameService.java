package org.jalau.at18.searchobject.controller.service;

import org.jalau.at18.searchobject.model.objectrecognizer.recognizer.ModelRecognizer;
import org.jalau.at18.searchobject.model.objectrecognizer.recognizer.VerifyModelRecognizer;
import org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes.MatchInfo;
import org.springframework.stereotype.Service;
import java.nio.file.Path;
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
        List<MatchInfo> matchInfos = modelRecognizer.matching(zipFilePath, searchCriteria, occurrencyPercentage);
        return matchInfos;
    }
}
