package org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class FileSourceTest {

    @Test
    public void shouldConstructFileSource() {
        FileSource fileSource = new FileSource("fileName");
        assertEquals(FileSource.class, fileSource.getClass());
    }

    @Test(expected = FileNotFoundException.class)
    public void shouldGetFileNotFoundExceptionInputStream(){
        FileSource fileSource = new FileSource("fileName");
        fileSource.getInputStream();
    }

    @Test
    public void shouldGetInputStream(){
        FileSource fileSource = new FileSource(new File("../searchobject/src/test/java/testemotionfaces/angry.jpg").getPath());
        assertEquals(FileInputStream.class, fileSource.getInputStream().getClass());
    }
}