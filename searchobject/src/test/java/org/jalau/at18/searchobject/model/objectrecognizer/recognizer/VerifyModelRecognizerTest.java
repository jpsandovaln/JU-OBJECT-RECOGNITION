package org.jalau.at18.searchobject.model.objectrecognizer.recognizer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class VerifyModelRecognizerTest {

    @Test
    public void shouldGetSSDModelRecognizer() {
        VerifyModelRecognizer verifyModelRecognizer = new VerifyModelRecognizer();
        String ssd = "ssd";
        assertEquals(SSD.class, verifyModelRecognizer.getModelRecognizer(ssd).getClass());
    }

    @Test
    public void shouldGetYoloModelRecognizer() {
        VerifyModelRecognizer verifyModelRecognizer = new VerifyModelRecognizer();
        String yolo = "yolo";
        assertEquals(Yolo.class, verifyModelRecognizer.getModelRecognizer(yolo).getClass());
    }

    @Test
    public void shouldGetObjectDetectionModelRecognizer() {
        VerifyModelRecognizer verifyModelRecognizer = new VerifyModelRecognizer();
        String objectDetection = "objectdetection";
        assertEquals(ObjectDetection.class, verifyModelRecognizer.getModelRecognizer(objectDetection).getClass());
    }

    @Test
    public void shouldReturnNullWhenGettingModelRecognizer() {
        VerifyModelRecognizer verifyModelRecognizer = new VerifyModelRecognizer();
        String nullValue = "null";
        assertNull(verifyModelRecognizer.getModelRecognizer(nullValue));
    }
}