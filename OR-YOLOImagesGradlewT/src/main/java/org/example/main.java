package org.example;

import org.example.YOLO.yoloImage;

public class main {
    public static void main(String[] args) {
        yoloImage yoloForImage = new yoloImage("C:\\Users\\TUF GAMER\\Desktop\\yoloV3\\motorbike_thumbnail608.jpg", 90);
        yoloForImage.loadPipeline();
        System.out.println(yoloForImage.getTheResult("person"));
        System.out.println(yoloForImage.getTheResult("motorcycle"));
    }
}
