/*
  Copyright (c) 2022 Jala University.
  This software is the confidential and property information of Jalasoft
  ("Confidential Information"). You shall not disclose such Confidential
  Information and shall use it only in accordance with the terms of the
  Licence agreement you entered into with Jalasoft
*/
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
    @Test
    public void shouldGetEnumTypeRcnn() {
        String expected = "rcnn";
        assertEquals(expected, TypeModelRecognizer.RCNN.getModel());
    }
}