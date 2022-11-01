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
import org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes.rcnn.RcnnDetection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
/**
 * This class obtains matches found according to the RCNN model
 * @author Fernanda Aguilar
 * @version 1.0
 */
public class RCNN implements ModelRecognizer {
    RcnnDetection object;
    private static final Logger logger = LoggerFactory.getLogger(RCNN.class);
    @Override
    public List<MatchInfo> matching(Path pathFolder, String searchCriteria, int percentage) {
        List<MatchInfo> matches = new ArrayList<>();
        try {
            File file = pathFolder.toFile();
            String[] listImg = file.list();
            if (listImg == null || listImg.length == 0) {
                logger.info("There are no elements inside the current folder");
            } else {
                for (int j=0;j< listImg.length;j++){
                    object = new RcnnDetection(searchCriteria,percentage,pathFolder+"\\"+listImg[j]);
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