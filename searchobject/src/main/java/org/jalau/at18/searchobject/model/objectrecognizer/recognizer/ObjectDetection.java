package org.jalau.at18.searchobject.model.objectrecognizer.recognizer;

import org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes.MatchInfo;
import org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes.zoo.ObjectDetectionModel;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import ai.djl.ModelException;
import ai.djl.translate.TranslateException;

import java.io.IOException;
import java.io.File;

public class ObjectDetection implements ModelRecognizer {
    ObjectDetectionModel object;
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
                    object = new ObjectDetectionModel(searchCriteria,80,pathFolder+"\\"+listImg[j]);
                    String avg = String.valueOf(object.getDataList().get(listImg[j].toString()));
                    matches.add(new MatchInfo(listImg[j],Double.parseDouble(avg)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matches;
    }
}
