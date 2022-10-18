package org.jalau.at18.searchobject.model.yolo;

import org.jalau.at18.searchobject.model.yolo.YOLO.yoloImage;

public class main {
    public static void main(String[] args) {
        yoloImage yoloForImage = new yoloImage("C:\\Users\\TUF GAMER\\Desktop\\yoloV3\\motorbike_thumbnail608.jpg", 90);
        yoloForImage.loadPipeline();
        System.out.println("CONFIDENCE PERSON: " + yoloForImage.getTheResult("person"));
        System.out.println("CONFIDENCE MOTORCYCLE: " + yoloForImage.getTheResult("motorcycle"));
        System.out.println("CONFIDENCE CAT: " + yoloForImage.getTheResult("cat"));
    }
}
