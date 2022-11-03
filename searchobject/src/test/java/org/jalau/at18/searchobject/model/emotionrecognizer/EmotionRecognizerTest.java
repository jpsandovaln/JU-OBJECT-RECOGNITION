package org.jalau.at18.searchobject.model.emotionrecognizer;

import org.jalau.at18.searchobject.common.exception.EmotionRecognizerException;
import org.junit.Test;

public class EmotionRecognizerTest {

    @Test (expected = EmotionRecognizerException.class)
    public void shouldReturnNullPathException() throws EmotionRecognizerException {
        EmotionRecognizer emotionRecognizer = new EmotionRecognizer(null, "1234567887");

    }
    @Test (expected = EmotionRecognizerException.class)
    public void shouldReturnNullEmotionExceptionBothNull() throws EmotionRecognizerException {
        EmotionRecognizer emotionRecognizer = new EmotionRecognizer(null, null);
    }

    @Test (expected = EmotionRecognizerException.class)
    public void shouldReturnNullTokenEmotionException() throws EmotionRecognizerException {
        EmotionRecognizer emotionRecognizer = new EmotionRecognizer("java/testemotionfaces/angry.jpg" , null);
    }

    @Test (expected = EmotionRecognizerException.class)
    public void shouldReturnNonexistentException() throws EmotionRecognizerException {
        EmotionRecognizer emotionRecognizer = new EmotionRecognizer("java/ttttestemotionfaces/angry.jpg" , "4564646465646");
       
    }
}