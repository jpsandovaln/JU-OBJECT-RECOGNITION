package org.jalau.at18.searchobject.modelrecognizer;

import org.jalau.at18.searchobject.model.MatchInfo;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SSD implements ModelRecognizer {
    @Override
    public List<MatchInfo> matching(Path pathFolder, String searchCriteria, int occurrencyPercentage) {
        List<MatchInfo> matches = new ArrayList<>();
        matches.add(new MatchInfo("2.png", 82.9));
        matches.add(new MatchInfo("16.png", 90.9));
        matches.add(new MatchInfo("500.png", 81.9));
        matches.add(new MatchInfo("700.png", 90.2));
        return matches;
    }
}
