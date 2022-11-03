package org.jalau.at18.searchobject.facedetec;
import org.jalau.at18.searchobject.common.exception.FaceDetectionException;
import org.jalau.at18.searchobject.model.facedetector.FaceDetect;
import org.jalau.at18.searchobject.model.objectrecognizer.UnzipFile;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
public class FaceDetectTest {
    @Test (expected = FaceDetectionException.class)
    public void shouldGetErrorNullFile() throws FaceDetectionException{
        FaceDetect face = new FaceDetect(null, "profile");
        face.faceDetection(null, "profile");
    }
    @Test (expected = FaceDetectionException.class)
    public void shouldGetErrorEmpthyType() throws FaceDetectionException{
        FaceDetect face = new FaceDetect(null, "\\src\\test\\java\\testfacedetection\\");
        face.faceDetection(null, "\\src\\test\\java\\testfacedetection\\");
    }
    @Test (expected = FaceDetectionException.class)
    public void shouldGetErrorWrongType() throws FaceDetectionException{
        FaceDetect face = new FaceDetect("\\src\\test\\java\\testfacedetection\\avengers.jpg", "profile");
        face.faceDetection("\\src\\test\\java\\testfacedetection\\avengers.jpg", "profile");
    }

    @Test (expected = FaceDetectionException.class)
    public void shouldGetErrorWrongFile() throws FaceDetectionException{
        FaceDetect face = new FaceDetect("\\src\\test\\java\\testfacedetection\\pandas2.jpg", "profile");
        face.faceDetection("\\src\\test\\java\\testfacedetection\\pandas2.jpg", "profile");
    }

    @Test
    public void shouldGetCorrectinputProfile() throws FaceDetectionException{
        FaceDetect face = new FaceDetect("\\src\\test\\java\\testfacedetection\\perfil.jpg", "profile");
        boolean status = true;
        String result = "It's a person: " + status;
        assertEquals(result, face.getCommand());
    }

    @Test
    public void shouldGetCorrectinputMultiple() throws FaceDetectionException{
        FaceDetect face = new FaceDetect("\\src\\test\\java\\testfacedetection\\avengers.jpg", "multiple");
        boolean status = true;
        String result = "multiple person detected, there are person in it: " + status +"\n" + "Quantity of face detect: " + "6";
        assertEquals(result, face.getCommand());
    }
}
