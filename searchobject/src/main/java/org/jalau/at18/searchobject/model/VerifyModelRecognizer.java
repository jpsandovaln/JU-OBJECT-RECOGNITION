package org.jalau.at18.searchobject.model;

public class VerifyModelRecognizer {
    public ModelRecognizer getModelRecognizer(String modelRecognizer) {
        if (modelRecognizer == TypeModelRecognizer.YOLO.getModel()) {
            return new Yolo();
        }
        if (modelRecognizer == TypeModelRecognizer.SSD.getModel()) {
            return new SSD();
        }
        if (modelRecognizer == TypeModelRecognizer.ZOOMODEL.getModel()) {
            return new ZooModel();
        }
        return null;
    }
}
