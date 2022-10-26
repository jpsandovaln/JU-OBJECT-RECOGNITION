package org.jalau.at18.searchobject.model.objectrecognizer.recognizer;

public enum TypeModelRecognizer {
    YOLO("yolo"),
    SSD("ssd"),
    OBJECTDETECTION("objectdetection");
    private String model;

    TypeModelRecognizer(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }
}
