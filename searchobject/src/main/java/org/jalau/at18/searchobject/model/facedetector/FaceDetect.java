package org.jalau.at18.searchobject.model.facedetector;
/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and proprieraty information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */


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
    private static String current_dir;
    private static String result;
    public FaceDetect (String file, String type) {

        faceDetection (file, type);

    }

    public static void faceDetection (String file, String type) {
        current_dir = System.getProperty("user.dir");
        System.out.println(current_dir + "\\src\\main\\resources\\libraries\\opencv\\x64\\opencv_java454.dll");
        System.load(current_dir + "\\src\\main\\resources\\libraries\\opencv\\x64\\opencv_java454.dll");
        Boolean detection = false;

        String imgFile = current_dir +"\\"+ file;//"images/avengers.jpg" //imgFile it's the file we want to analyze if there it's a person in the phot
        System.out.println("resultado   " + imgFile +  type);
        Mat src = Imgcodecs.imread(imgFile.toString());
        System.out.println("leer imagen   " + src);
        //Mat [ 432*768*CV_8UC3, isCont=true, isSubmat=false, nativeObj=0x1f5f246abb0, dataAddr=0x1f5f268b000 ]
        // Are the connecting dots of a facial layout. This xml exactly defines a front-face layout
        String xmlFile ="D:\\workspacejala\\progra102\\JU-OBJECT-RECOGNITION\\resourcesdetect\\xml\\lbpcascade_frontalface.xml";
        System.out.println(xmlFile);
        //cascade classifiers is an effective object detection method
        CascadeClassifier cc = new CascadeClassifier(xmlFile);
        // run a face detector on the imag
        MatOfRect faceDetection = new MatOfRect();
        //detectMultiScale used to detect the faces. This function will return a rectangle with coordinates(x,y,w,h)
        //faceDetection will determine the amount of face that whas fine in the image
        cc.detectMultiScale(src, faceDetection);

        if (type.equals( "personal") && faceDetection.toArray().length == 1 ) { //
            for(Rect rect: faceDetection.toArray()) {
                Imgproc.rectangle(src, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height) , new Scalar(0, 0, 255), 3);
            }

            Imgcodecs.imwrite("D:\\workspacejala\\progra102\\JU-OBJECT-RECOGNITION\\resourcesdetect\\images\\facedetect.jpg", src);
            result = "profile detected";//"";

        }  else if (type.equals("multiple") && faceDetection.toArray().length > 1 ) { //
            for(Rect rect: faceDetection.toArray()) {
                Imgproc.rectangle(src, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height) , new Scalar(0, 0, 255), 3);
            }

            Imgcodecs.imwrite("D:\\workspacejala\\progra102\\JU-OBJECT-RECOGNITION\\resourcesdetect\\images\\peopledetected.jpg", src);
            result ="multiple person detected";//String.format("Detected faces: %d" + faceDetection.toArray().length);
        } else {
           result = "the image it's wrong, upload the image again";
        }

    }
    public String getCommand() {
        return result;
    }

}
