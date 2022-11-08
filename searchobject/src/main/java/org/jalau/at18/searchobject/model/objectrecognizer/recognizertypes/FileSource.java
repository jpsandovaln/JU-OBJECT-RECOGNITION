package org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes;
/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and property information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
/**
 * It is responsible for determine if there it's a file or not.
 *
 * @author Maria Hurtado
 * @version 1.0
 */
public class FileSource {
    String fileName;

    public FileSource(String fileName) {
        this.fileName = fileName;
    }
    /**
     * Method to get the file data
     */
    public InputStream getInputStream() {
        try {
            InputStream fileInputStream = new FileInputStream(fileName);
            return fileInputStream;
        } catch (FileNotFoundException ex) {
            System.out.println("File not found !");
        }
        return null;
    }
}
