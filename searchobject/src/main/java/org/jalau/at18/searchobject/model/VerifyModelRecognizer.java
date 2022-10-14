package org.jalau.at18.searchobject.model;

public class VerifyModelRecognizer {
    public ModelRecognizer getModelRecognizer(String modelRecognizer) {
        if (modelRecognizer.equals(TypeModelRecognizer.YOLO.getModel())) {
            return new Yolo();
        }
        if (modelRecognizer.equals(TypeModelRecognizer.SSD.getModel())) {
            return new SSD();
        }
        if (modelRecognizer.equals(TypeModelRecognizer.OBJECTDETECTION.getModel())) {
            return new ObjectDetection();
        }
        return null;
    }
}
