package org.jalau.at18.searchobject.service;

import org.jalau.at18.searchobject.model.FrameInfo;
import org.jalau.at18.searchobject.model.MatchInfo;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProcessFrameService {
    // The modelo needs to analize each frame, according the criteria
    // should be returned a list of frames that match with the criteria
    public List<MatchInfo> processFrameAccordingCriteria(List<FrameInfo> framess,
                                                         String searchCriteria,
                                                         int occurrencyPercentage,
                                                         String modelObjectRecognizer) {
        // to be implemented by
        // Mauricio, Libertad, and Jose
        // this is only an example to know that should return the result of frames that match with the criteria
        List<MatchInfo> matches = new ArrayList<>();
        matches.add(new MatchInfo( new FrameInfo("25.png", LocalTime.of(0, 5, 3))));
        return matches;
    }
}
