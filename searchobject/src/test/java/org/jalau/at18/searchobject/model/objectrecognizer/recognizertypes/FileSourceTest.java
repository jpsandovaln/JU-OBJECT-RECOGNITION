/*
  Copyright (c) 2022 Jala University.
  This software is the confidential and property information of Jalasoft
  ("Confidential Information"). You shall not disclose such Confidential
  Information and shall use it only in accordance with the terms of the
  Licence agreement you entered into with Jalasoft
*/
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