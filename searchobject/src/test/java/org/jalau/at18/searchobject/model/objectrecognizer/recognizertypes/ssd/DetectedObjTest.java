package org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes.ssd;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DetectedObjTest {

    @Test
    public void shouldConstructDetectObj() {
        DetectedObj detectedObj = new DetectedObj("object", 0);
        assertEquals(DetectedObj.class, detectedObj.getClass());
    }

    @Test
    public void shouldTestToString() {
        DetectedObj detectedObj = new DetectedObj("Object", 100);
        String expected = "{ label: " + detectedObj.getLabel() + ", score: " + detectedObj.getScore() + " }";
        assertEquals(expected, detectedObj.toString());
    }
}