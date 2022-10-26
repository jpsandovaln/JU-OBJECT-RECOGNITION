package org.jalau.at18.searchobject.model.objectrecognizer.recognizer;

import org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes.MatchInfo;
import org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes.yolo.YOLOclasstrain;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Yolo implements ModelRecognizer {
    List<MatchInfo> compatibleInformation = new ArrayList<>();
    @Override
    public List<MatchInfo> matching(Path pathFolder, String searchCriteria, int occurrencyPercentage) {
        YOLOclasstrain yolOclasstrain = new YOLOclasstrain(pathFolder, searchCriteria, occurrencyPercentage);
        ArrayList<String> imagesNames =  (ArrayList<String>) yolOclasstrain.getImageAndScore().get(1);
        ArrayList<Double> scores = (ArrayList<Double>) yolOclasstrain.getImageAndScore().get(0);
        for(int i = 0; i < imagesNames.size(); i++){
            compatibleInformation.add(new MatchInfo(imagesNames.get(i),scores.get(i)));
        }
        return compatibleInformation;
    }
}
