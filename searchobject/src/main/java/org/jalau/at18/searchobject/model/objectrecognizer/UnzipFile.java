/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and property information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
package org.jalau.at18.searchobject.model.objectrecognizer;
import org.jalau.at18.searchobject.common.exception.UnzipFileException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.nio.file.Path;

/**
 * It is responsible for take out all the files from the zip folder
 *
 * @author Hugo Solares
 * @version 1.0
 */
public class UnzipFile {
    Path dir;
    public UnzipFile(Path path) throws UnzipFileException {
        unzip(path);
    }
    private void unzip(Path path) throws UnzipFileException {
        try {
            Path fileZip = path;
            File destDir = new File(getFolder(fileZip.toString()));  //direction path
            byte[] buffer = new byte[1024]; //save the path
            //tool from java
            ZipInputStream zis= new ZipInputStream(new FileInputStream(fileZip.toString()));
            //tool from java
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) { //if file zip is valid
                File newFile = newFile(destDir, zipEntry); //every single file from the zip it's read
                dir = Path.of(newFile.getParent());
                if (zipEntry.isDirectory()) { //
                    if (!newFile.isDirectory() && !newFile.mkdirs()) {
                        throw new UnzipFileException("Failed to create directory " + newFile);
                    }
                } else {
                    // fix for Windows-created archives
                    File parent = newFile.getParentFile();
                    //error if file zip it's wrong create it
                    if (!parent.isDirectory() && !parent.mkdirs()) {
                        throw new UnzipFileException("Failed to create directory " + parent);
                    }
                    // write file content
                    FileOutputStream fos = new FileOutputStream(newFile);
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                }
                zipEntry = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();
        } catch (Exception e) {
            throw new UnzipFileException("Failed to create directory");
        }

    }
    /**
     *
     *
     */
    private File newFile(File destinationDir, ZipEntry zipEntry) throws UnzipFileException {
        try {
            File destFile = new File(destinationDir, zipEntry.getName());

            String destDirPath = destinationDir.getCanonicalPath();
            String destFilePath = destFile.getCanonicalPath();

            if (!destFilePath.startsWith(destDirPath + File.separator)) {
                throw new UnzipFileException("Entry is outside of the target dir: " + zipEntry.getName());
            }
            return destFile;
        } catch (Exception e) {
            throw new UnzipFileException("Failed to create a newFile");
        }

    }
    private String getFolder(String name) {
        String[] parts = name.split("\\.");
        return parts[0];
    }
    public Path getPath() {
        return  dir;
    }
}
