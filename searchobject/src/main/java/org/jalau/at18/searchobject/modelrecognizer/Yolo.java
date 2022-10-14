package org.jalau.at18.searchobject.modelrecognizer;

import org.jalau.at18.searchobject.model.MatchInfo;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Yolo implements ModelRecognizer {
    @Override
    public List<MatchInfo> matching(Path pathFolder, String searchCriteria, int occurrencyPercentage) {
        List<MatchInfo> matches = new ArrayList<>();
        matches.add(new MatchInfo("60.png", 82.9));
        matches.add(new MatchInfo("80.png", 90.9));
        matches.add(new MatchInfo("120.png", 85.9));
        matches.add(new MatchInfo("152.png", 93.2));
        return matches;
    }
}
