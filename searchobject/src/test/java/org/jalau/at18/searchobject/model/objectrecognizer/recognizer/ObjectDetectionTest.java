package org.jalau.at18.searchobject.model.objectrecognizer.recognizer;


import org.junit.Test;

import java.nio.file.Path;

import static org.junit.Assert.*;

public class ObjectDetectionTest {

    @Test(expected = Exception.class)
    public void shouldReturnException() throws Exception {
        ObjectDetection objectDetection = new ObjectDetection();
        objectDetection.matching(Path.of("searcbject/src/test/java/testemotionfaces"), "dog", 80);
    }
}