package org.jalau.at18.searchobject.model.objectrecognizer;
import org.jalau.at18.searchobject.common.exception.UnzipFileException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.nio.file.Path;
public class UnzipFile {
    Path dir;
    public UnzipFile(Path path) throws IOException {
        unzip(path);
    }
    private void unzip(Path path) throws IOException {
            Path fileZip = path;
            File destDir = new File(getFolder(fileZip.toString()));
            byte[] buffer = new byte[1024];
            ZipInputStream zis= new ZipInputStream(new FileInputStream(fileZip.toString()));
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                File newFile = newFile(destDir, zipEntry);
                dir = Path.of(newFile.getParent());
                if (zipEntry.isDirectory()) {
                    if (!newFile.isDirectory() && !newFile.mkdirs()) {
                        throw new UnzipFileException("Failed to create directory " + newFile);
                    }
                } else {
                    // fix for Windows-created archives
                    File parent = newFile.getParentFile();
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
    }
    private File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new UnzipFileException("Entry is outside of the target dir: " + zipEntry.getName());
        }
        return destFile;
    }
    private String getFolder(String name) {
        String[] parts = name.split("\\.");
        return parts[0];
    }
    public Path getPath() {
        return  dir;
    }
}
