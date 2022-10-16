/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and proprietary information of Jala University
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jala University
 */

import ai.djl.ModelException;
import ai.djl.translate.TranslateException;

import java.io.IOException;
import java.io.File;

/**
 * It performs a path of images found at an address,
 * and sends the image along with a percentage
 * and an object to be searched
 * to the ObjectDetection class.
 *
 * @author Jose Romay
 * @version 1.0
 */


public class Main {

    public static void main(String[] args) throws ModelException, TranslateException, IOException {
        String sCarpAct = System.getProperty("user.dir");
        String filepath = "src/test/resources/";

        File folder = new File(sCarpAct+"/"+filepath);

        String[] listImg = folder.list();
        if (listImg == null || listImg.length == 0) {
            System.out.println("There are no elements inside the current folder");
            return;
        }
        else {
            for (int i=0; i< listImg.length; i++) {
                System.out.println(filepath+listImg[i]);
               ObjectDetection object = new ObjectDetection("person",80,filepath+listImg[i]);
            }
        }
    }
}
