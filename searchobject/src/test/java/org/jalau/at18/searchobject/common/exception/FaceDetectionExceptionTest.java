package org.jalau.at18.searchobject.common.exception;

import org.junit.Test;

import static org.junit.Assert.*;

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