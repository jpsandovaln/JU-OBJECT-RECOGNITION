package org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes.yolo.TransformTXT;
/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and property information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Retrieves the name of the object that is being detected by using an index
 *
 * @author Mauricio Aliendre
 * @version 1.0
 */
public class Transform{
    public ArrayList<String> getTheVectorClass(){
        File archivo = null;
        FileReader fr = null;
        ArrayList<String> vector = new ArrayList<String>();
        int c=80;
        try {
            archivo = new File (System.getProperty("user.dir") + "\\src\\main\\resources\\YOLOConfigurationFiles\\yolov3.txt");
            String m;
            fr = new FileReader (archivo);
            BufferedReader br = new BufferedReader(fr);
            for (int i = 0; i < c; i++) {
                m = br.readLine();
                vector.add(m);
            }
        }
        catch(IOException e){
            System.out.println(e);
        }
        return vector;
    }
    }