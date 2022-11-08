/*
  Copyright (c) 2022 Jala University.
  This software is the confidential and property information of Jalasoft
  ("Confidential Information"). You shall not disclose such Confidential
  Information and shall use it only in accordance with the terms of the
  Licence agreement you entered into with Jalasoft
*/
package org.jalau.at18.searchobject.common.exception;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UnzipFileExceptionTest {

    @Test
    public void shouldBuildUnzipFileException() {
        UnzipFileException unzipFileException = new UnzipFileException("The unzip file exception");
        assertEquals(UnzipFileException.class, unzipFileException.getClass());
    }

    @Test
    public void shouldBuildUnzipFileExceptionWithThrowable() {
        UnzipFileException unzipFileException = new UnzipFileException("The unzip file exception", new Throwable());
        assertEquals(UnzipFileException.class, unzipFileException.getClass());
    }
}