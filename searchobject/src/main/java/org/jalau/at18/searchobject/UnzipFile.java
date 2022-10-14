package org.jalau.at18.searchobject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnzipFile {
    public static void main(String[] args) throws IOException {
    String fileZip = "main/resources/unzipTest/compressed.zip";
    File destDir = new File("main/resources/unzipTest");
    byte[] buffer = new byte[1024];
    ZipInputStream zis= new ZipInputStream(new FileInputStream(fileZip));
    ZipEntry zipEntry = zis.getNextEntry();
    while(zipEntry !=null) {
        File newFile = newFile(destDir, zipEntry);
        if (zipEntry.isDirectory()) {
            if (!newFile.isDirectory() && !newFile.mkdirs()) {
                throw new IOException("Failed to create directory " + newFile);
            }
        } else {
            // fix for Windows-created archives
            File parent = newFile.getParentFile();
            if (!parent.isDirectory() && !parent.mkdirs()) {
                throw new IOException("Failed to create directory " + parent);
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
	System.out.println("Unzipping complete");
    }
    public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }
        return destFile;
    }
    
    //another example
    /*public static void main(String[] args) throws Exception {
        if(args.length <2 ) {
            throw new Exception("Insufficient no of parameters. Required-Parameter1: Full zip file name, Parameter2: Dest dir");

        }
        String zipFileName = "src/main/resources/unzipTest/compressed.zip";
        String destDirectory = "src/main/resources/unzipTest";
        File destDirectoryFolder = new File(destDirectory);
        if (!destDirectoryFolder.exists()) {
            destDirectoryFolder.mkdir();
        }
        byte[] buffer = new byte[1024];
        ZipInputStream zis= new ZipInputStream(new FileInputStream(zipFileName));
        ZipEntry zipEntry = zis.getNextEntry();
        while(zipEntry !=null) {
            String filePath = destDirectory + File.separator + zipEntry.getName();
            System.out.println("Unzipping "+filePath);
            if(!zipEntry.isDirectory()) {
                FileOutputStream fos = new FileOutputStream(filePath);
                int len;
                while ((len = zis.read(buffer)) >0){
                    fos.write(buffer,0,len);
                }
                fos.close();
            }
            else {
                File dir = new File(filePath);
                dir.mkdir();
            }
            zis.closeEntry();
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
        System.out.println("Unzipping complete");
    }*/
}
