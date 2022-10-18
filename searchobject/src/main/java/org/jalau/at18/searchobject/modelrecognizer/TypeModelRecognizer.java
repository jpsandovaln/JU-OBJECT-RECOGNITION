package org.jalau.at18.searchobject.modelrecognizer;

public enum TypeModelRecognizer {
    YOLO("org/jalau/at18/searchobject/model/yolo"),
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
