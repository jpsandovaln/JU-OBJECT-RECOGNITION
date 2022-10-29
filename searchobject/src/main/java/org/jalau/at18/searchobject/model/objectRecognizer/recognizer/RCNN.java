package org.jalau.at18.searchobject.model.objectrecognizer.recognizer;

import org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes.MatchInfo;
import org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes.rcnn.RcnnDetection;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class RCNN implements ModelRecognizer {
    RcnnDetection object;
    @Override
    public List<MatchInfo> matching(Path pathFolder, String searchCriteria, int occurrencyPercentage) {
        List<MatchInfo> matches = new ArrayList<>();
        try {
            File file = pathFolder.toFile();
            String[] listImg = file.list();
            if (listImg == null || listImg.length == 0) {
                System.out.println("There are no elements inside the current folder");
            } else {
                for (int j=0;j< listImg.length;j++){
                    object = new RcnnDetection(searchCriteria,80,pathFolder+"\\"+listImg[j]);
                    String avg = String.valueOf(object.getInfoList().get(listImg[j].toString()));
                    matches.add(new MatchInfo(listImg[j],Double.parseDouble(avg)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matches;
    }
}