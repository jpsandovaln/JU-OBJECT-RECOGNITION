package org.jalau.at18.searchobject.modelrecognizer;

import org.example.YOLOclasstrain;
import org.jalau.at18.searchobject.model.MatchInfo;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Yolo implements ModelRecognizer {
    @Override
    public List<MatchInfo> matching(Path pathFolder, String searchCriteria, int occurrencyPercentage) {
        YOLOclasstrain yolOclasstrain = new YOLOclasstrain(pathFolder, searchCriteria, occurrencyPercentage);
        ArrayList<String> imagesNames =  (ArrayList<String>) yolOclasstrain.getImageAndScore().get(1);
        ArrayList<Double> scores = (ArrayList<Double>) yolOclasstrain.getImageAndScore().get(0);
        List<MatchInfo> compatibleInformation = new ArrayList<>();
        for (int i = 0; i <= imagesNames.size(); i++){
            MatchInfo matchInfo = new MatchInfo(imagesNames.get(i), scores.get(i));
            compatibleInformation.add(matchInfo);
        }
        return compatibleInformation;
    }
}
