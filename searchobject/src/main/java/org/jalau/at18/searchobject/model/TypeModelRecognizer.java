package org.jalau.at18.searchobject.model;

public enum TypeModelRecognizer {
    YOLO("yolo"),
    SSD("ssd"),
    ZOOMODEL("zoomodel");
    private String model;

    TypeModelRecognizer(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }
}
