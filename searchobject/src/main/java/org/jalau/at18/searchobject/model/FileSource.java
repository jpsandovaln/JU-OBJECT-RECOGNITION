package org.jalau.at18.searchobject.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FileSource {
    String fileName;

    public FileSource(String fileName) {
        this.fileName = fileName;
    }

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
