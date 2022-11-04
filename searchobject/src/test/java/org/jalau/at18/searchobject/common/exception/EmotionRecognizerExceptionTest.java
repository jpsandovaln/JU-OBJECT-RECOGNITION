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