/*
  Copyright (c) 2022 Jala University.
  This software is the confidential and property information of Jalasoft
  ("Confidential Information"). You shall not disclose such Confidential
  Information and shall use it only in accordance with the terms of the
  Licence agreement you entered into with Jalasoft
*/
package org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes.yolo.YOLO;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

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