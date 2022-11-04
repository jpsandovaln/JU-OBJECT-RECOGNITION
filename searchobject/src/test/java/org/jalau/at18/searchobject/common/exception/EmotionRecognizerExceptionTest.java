package org.jalau.at18.searchobject.common.exception;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EmotionRecognizerExceptionTest {

    @Test
    public void shouldBuildEmotionRecognizerException() {
        EmotionRecognizerException recognizerException = new EmotionRecognizerException("The exception");
        assertEquals(EmotionRecognizerException.class, recognizerException.getClass());
    }

    @Test
    public void shouldBuildEmotionRecognizerExceptionWithThrowable() {
        EmotionRecognizerException recognizerException = new EmotionRecognizerException("The exception", new Throwable());
        assertEquals(EmotionRecognizerException.class, recognizerException.getClass());
    }
}