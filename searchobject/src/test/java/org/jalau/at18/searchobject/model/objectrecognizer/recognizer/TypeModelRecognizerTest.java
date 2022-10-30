package org.jalau.at18.searchobject.model.objectrecognizer.recognizer;

import org.junit.Test;

import static org.junit.Assert.*;

public class TypeModelRecognizerTest {

    @Test
    public void shouldGetEnumTypeYolo() {
        String expected = "yolo";
        assertEquals(expected, TypeModelRecognizer.YOLO.getModel());
    }

    @Test
    public void shouldGetEnumTypeSSD() {
        String expected = "ssd";
        assertEquals(expected, TypeModelRecognizer.SSD.getModel());
    }

    @Test
    public void shouldGetEnumTypeObjectDetection() {
        String expected = "objectdetection";
        assertEquals(expected, TypeModelRecognizer.OBJECTDETECTION.getModel());
    }
}