package org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes.yolo;
/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and property information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
import java.io.Serializable;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes.yolo.YOLO.yoloImage;
/**
 * In charge of linking the information received from Postman and the YOLO model that will be used to detect objects
 * @author Mauricio Aliendre
 * @version 1.0
 */
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
        for (int i = 0; i < pathFolder.getNameCount(); i++){
            String image = pathFolder + "\\" + String.format("00-00-0%s.png", i);
            yoloImage yoloForImage = new yoloImage(image, ocurrencyPercentage);
            yoloForImage.loadPipeline();
            scores.add(yoloForImage.getTheResult(searchCriteria));
            matchImage.add(String.format("00-00-0%s.jpg", i));
        }
        return Arrays.asList(scores, matchImage);
    }
}
