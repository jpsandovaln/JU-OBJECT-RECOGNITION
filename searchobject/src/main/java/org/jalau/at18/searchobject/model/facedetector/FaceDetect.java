package org.jalau.at18.searchobject.model.facedetector;
/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and proprieraty information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

/**
 * It is responsible for analyze an image and detect if there it's a face or not.
 *
 * @author Daniela Martinez
 * @version 1.0
 */
public class FaceDetect {
    private String faceDetection;
    public static void main(String[] args) {

        faceDetection ("images/avengers.jpg", "personal");

    }

    public static void faceDetection (String file, String type) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        String imgFile = file;//"images/avengers.jpg" //imgFile it's the file we want to analyze if there it's a person in the photo

        Mat src = Imgcodecs.imread(imgFile);
        // Are the connecting dots of a facial layout. This xml exactly defines a front-face layout
        String xmlFile = "xml/lbpcascade_frontalface.xml";
        //cascade classifiers is an effective object detection method
        CascadeClassifier cc = new CascadeClassifier(xmlFile);
        // run a face detector on the imag
        MatOfRect faceDetection = new MatOfRect();
        //detectMultiScale used to detect the faces. This function will return a rectangle with coordinates(x,y,w,h)
        //faceDetection will determine the amount of face that whas fine in the image
        cc.detectMultiScale(src, faceDetection);
        if (type == "personal" && faceDetection.toArray().length == 1) {
            for(Rect rect: faceDetection.toArray()) {
                Imgproc.rectangle(src, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height) , new Scalar(0, 0, 255), 3);
            }

            Imgcodecs.imwrite("images/facedetect.jpg", src);
            System.out.println("Profile detection");
            System.exit(0);
        }  else {
            System.out.println("the image it's wrong, upload the image again");
            System.exit(0);
        }
        if (type == "multiple" && faceDetection.toArray().length > 1) {
            for(Rect rect: faceDetection.toArray()) {
                Imgproc.rectangle(src, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height) , new Scalar(0, 0, 255), 3);
            }

            Imgcodecs.imwrite("images/peopledetected.jpg", src);
            System.out.println(String.format("Detected faces: %d", faceDetection.toArray().length));
            System.out.println("Group detection");
            System.exit(0);
        } else {
            System.out.println("the image it's wrong, upload the image again");
            System.exit(0);
        }
    }
}
