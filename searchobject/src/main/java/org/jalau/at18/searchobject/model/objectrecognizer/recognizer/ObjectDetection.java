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
import org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes.zoo.ObjectDetectionModel;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import ai.djl.ModelException;
import ai.djl.translate.TranslateException;

import java.io.IOException;
import java.io.File;
/**
 * It is responsible for save the matching from the process of the model.
 *
 * @author Jose romay
 * @version 1.0
 */
public class ObjectDetection implements ModelRecognizer {
    ObjectDetectionModel object;  //call the model of detection zoo

    /**
     * Method of list of the matching images with the object search
     * @param pathFolder  zip folder
     * @param searchCriteria object to search
     * @param occurrencyPercentage  the score of the match
     *
     */
    @Override
    public List<MatchInfo> matching(Path pathFolder, String searchCriteria, int occurrencyPercentage) {
        List<MatchInfo> matches = new ArrayList<>(); //start the list of the matches
        try {
            //convert the path to a file
            File file = pathFolder.toFile();
            //save the images in a list so every single image will be process
            String[] listImg = file.list();
            //if images are null or 0 it would not start the process
            if (listImg == null || listImg.length == 0) {
                System.out.println("There are no elements inside the current folder");
            } else {
                //start the process of detect the object in every single image
                for (int j=0;j< listImg.length;j++){
                    object = new ObjectDetectionModel(searchCriteria,80,pathFolder+"\\"+listImg[j]);
                    String avg = String.valueOf(object.getDataList().get(listImg[j].toString()));
                    //add the images that match to the list
                    matches.add(new MatchInfo(listImg[j],Double.parseDouble(avg)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matches;
    }
}
