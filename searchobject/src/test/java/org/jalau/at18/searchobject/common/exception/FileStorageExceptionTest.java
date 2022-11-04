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

public class FileStorageExceptionTest {

    @Test
    public void shouldBuildFileStorageException() {
        FileStorageException fileStorageException = new FileStorageException("The file storage exception");
        assertEquals(FileStorageException.class, fileStorageException.getClass());
    }

    @Test
    public void shouldBuildFileStorageExceptionWithThrowable() {
        FileStorageException fileStorage = new FileStorageException("The file storage exception", new Throwable());
        assertEquals(FileStorageException.class, fileStorage.getClass());
    }
}