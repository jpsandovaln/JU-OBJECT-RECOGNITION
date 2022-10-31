/*
  Copyright (c) 2022 Jala University.
  This software is the confidential and property information of Jalasoft
  ("Confidential Information"). You shall not disclose such Confidential
  Information and shall use it only in accordance with the terms of the
  Licence agreement you entered into with Jalasoft
*/
package org.jalau.at18.searchobject.model.objectrecognizer;

import org.jalau.at18.searchobject.common.exception.UnzipFileException;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UnzipFileTest {

    @Test
    public void shouldConstructUnzipFile() throws IOException {
        UnzipFile unzipFile = new UnzipFile(new File("../searchobject/src/test/java/testemotionfaces/angry.jpg").toPath());
        assertEquals(UnzipFile.class, unzipFile.getClass());
    }

    @Test(expected = UnzipFileException.class)
    public void shouldGetPathNotFoundExceptionWhenPathNotFound() throws IOException {
        UnzipFile unzipFile = new UnzipFile(new File("invalid_path").toPath());
    }

    @Test
    public void shouldGetNullFilePath() throws IOException {
        UnzipFile unzipFile = new UnzipFile(new File("../searchobject/src/test/java/testemotionfaces/angry.jpg").toPath());
        assertNull(unzipFile.getPath());
    }
}