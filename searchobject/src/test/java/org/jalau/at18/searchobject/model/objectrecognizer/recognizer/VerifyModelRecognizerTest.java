package org.jalau.at18.searchobject.model.objectrecognizer.recognizer;

import org.jalau.at18.searchobject.common.exception.ObjectRecognizerException;
import org.jalau.at18.searchobject.common.exception.UnzipFileException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class VerifyModelRecognizerTest {

    @Test
    public void shouldGetSSDModelRecognizer() throws ObjectRecognizerException {
        VerifyModelRecognizer verifyModelRecognizer = new VerifyModelRecognizer();
        String ssd = "ssd";
        assertEquals(SSD.class, verifyModelRecognizer.getModelRecognizer(ssd).getClass());
    }

    @Test
    public void shouldGetYoloModelRecognizer() throws ObjectRecognizerException {
        VerifyModelRecognizer verifyModelRecognizer = new VerifyModelRecognizer();
        String yolo = "yolo";
        assertEquals(Yolo.class, verifyModelRecognizer.getModelRecognizer(yolo).getClass());
    }

    @Test
    public void shouldGetObjectDetectionModelRecognizer() throws ObjectRecognizerException {
        VerifyModelRecognizer verifyModelRecognizer = new VerifyModelRecognizer();
        String objectDetection = "objectdetection";
        assertEquals(ObjectDetection.class, verifyModelRecognizer.getModelRecognizer(objectDetection).getClass());
    }

    @Test (expected = ObjectRecognizerException.class)
    public void shouldReturnNullWhenGettingModelRecognizer() throws ObjectRecognizerException {
        VerifyModelRecognizer verifyModelRecognizer = new VerifyModelRecognizer();
        String nullValue = "null";
        verifyModelRecognizer.getModelRecognizer(nullValue);
    }
}