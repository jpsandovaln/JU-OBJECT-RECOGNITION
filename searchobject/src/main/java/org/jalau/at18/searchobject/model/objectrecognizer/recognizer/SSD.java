package org.jalau.at18.searchobject.model.objectrecognizer.recognizer;
/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and property information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
import org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes.MatchInfo;
import org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes.ssd.DetectedObj;
import org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes.ssd.ObjectDetector;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
/**
 * This class obtains matches found according to the SSD Model
 * @author Libertad Tolaba
 * @version 1.0
 */
public class SSD implements ModelRecognizer {
    @Override
    public List<MatchInfo> matching(Path pathFolder, String searchCriteria, int occurrencyPercentage) {
        List<MatchInfo> listObject = new ArrayList<>();
        ObjectDetector detector = new ObjectDetector();
        try {
            detector.loadModel();
            File file = pathFolder.toFile();
            File[] listImages= file.listFiles();
            if(listImages==null || listImages.length==0){
                System.out.println("There aren't elements in the folder");
            }
            else{
                for (int i = 0; i < listImages.length; i++) {
                    BufferedImage img = ImageIO.read(listImages[i]);
                    List<DetectedObj> result = detector.detectObjects(img);
                    double score = validateObject(result, searchCriteria, occurrencyPercentage);
                    MatchInfo matchInfo = new MatchInfo(listImages[i].getName(), score);
                    listObject.add(matchInfo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listObject;
    }
    /**
     * Method is in charge of validating the detected objects found according to the percentage and search criteria
     * @return it returns the Image score average
     */
    private double validateObject(List<DetectedObj> result, String searchCriteria, int occurrencyPercentage) {
        double scoreOfImage = 0;
        int counter = 0;
        for(int j=0; j < result.size(); ++j){
            DetectedObj object = result.get(j);
            double score = (object.getScore()*100);
            if((score>=occurrencyPercentage) && object.getLabel().equalsIgnoreCase(searchCriteria)){
                scoreOfImage += score;
                counter++;
            }
        }
        scoreOfImage = scoreOfImage/counter;
        return scoreOfImage;
    }
}
