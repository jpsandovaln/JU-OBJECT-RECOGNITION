package org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes.yolo.YOLO;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class yoloImageTest {

    @Test
    public void shouldConstructYoloImage() {
        yoloImage image = new yoloImage("../searchobject/src/test/java/testemotionfaces/motocat.jpeg", 90);
        assertEquals(yoloImage.class, image.getClass());
    }

    @Test
    public void shouldLoadPipeline() {
        yoloImage yoloForImage = new yoloImage("../searchobject/src/test/java/testemotionfaces/motocat.jpeg", 90);
        yoloForImage.loadPipeline();
        assertEquals(0.0, Optional.of(yoloForImage.getTheResult("person")));
        assertEquals(0.0, Optional.of(yoloForImage.getTheResult("motorcycle")));
        assertEquals(0.0, Optional.of(yoloForImage.getTheResult("cat")));
    }

}