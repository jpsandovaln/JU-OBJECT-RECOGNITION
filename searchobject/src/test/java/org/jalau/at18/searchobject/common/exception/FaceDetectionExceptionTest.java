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

public class FaceDetectionExceptionTest {
    @Test
    public void shouldBuildFaceDetectionException() {
        FaceDetectionException faceDetectionException = new FaceDetectionException("The exception");
        assertEquals(FaceDetectionException.class, faceDetectionException.getClass());
    }

    @Test
    public void shouldBuildFaceDetectionExceptionWithThrowable() {
        FaceDetectionException faceDetectionException = new FaceDetectionException("The exception", new Throwable());
        assertEquals(FaceDetectionException.class, faceDetectionException.getClass());
    }

    @Test
    public void shouldBuildEmotionRecognizerExceptionWith() {
        FaceDetectionException faceDetectionException = new FaceDetectionException(null, new Throwable());
        assertEquals(FaceDetectionException.class, faceDetectionException.getClass());
    }
}