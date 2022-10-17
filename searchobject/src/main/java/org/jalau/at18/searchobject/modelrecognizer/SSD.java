package org.jalau.at18.searchobject.modelrecognizer;

import org.jalau.at18.searchobject.model.MatchInfo;
import org.jalau.at18.searchobject.model.ssd.DetectedObj;
import org.jalau.at18.searchobject.model.ssd.ObjectDetector;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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
