package org.jalau.at18.searchobject.model.yolo;

import org.jalau.at18.searchobject.model.yolo.YOLO.yoloImage;

import java.io.Serializable;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class YOLOclasstrain {
    private Path pathFolder;
    private String searchCriteria;
    private int ocurrencyPercentage;
    private ArrayList<Double> scores;
    private ArrayList<String> matchImage;

    public YOLOclasstrain(Path pathFolder, String searchCriteria, int ocurrencyPercentage) {
        this.pathFolder = pathFolder;
        this.searchCriteria = searchCriteria;
        this.ocurrencyPercentage = ocurrencyPercentage;
        scores = new ArrayList<Double>();
        matchImage = new ArrayList<String>();
    }

    public List<ArrayList<? extends Serializable>> getImageAndScore(){
        for (int i = 0; i <= pathFolder.getNameCount(); i++){
            String image = pathFolder + String.format("00-00-0%s.jpg", i);
            yoloImage yoloForImage = new yoloImage(image, ocurrencyPercentage);
            yoloForImage.loadPipeline();
            if (yoloForImage.getTheResult(searchCriteria) >= ocurrencyPercentage){
                scores.add(yoloForImage.getTheResult(searchCriteria));
                matchImage.add(String.format("00-00-0%s.jpg", i));
            }
        }
        return Arrays.asList(scores, matchImage);
    }
}
