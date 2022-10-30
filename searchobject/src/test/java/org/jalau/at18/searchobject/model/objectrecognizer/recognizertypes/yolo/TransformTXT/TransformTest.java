package org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes.yolo.TransformTXT;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TransformTest {

    @Test
    public void shouldConstructTheTransformClass() {
        Transform transform = new Transform();
        assertEquals(Transform.class, transform.getClass());
    }

    @Test
    public void shouldGetTheVectorClass() {
        Transform transform = new Transform();
        assertEquals(new ArrayList<>(), transform.getTheVectorClass());
    }
}