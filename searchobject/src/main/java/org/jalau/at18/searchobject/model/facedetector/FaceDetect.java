package org.jalau.at18.searchobject.model.facedetector;
/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and proprieraty information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */


import org.jalau.at18.searchobject.common.logger.At18Logger;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.util.logging.Logger;


/**
 * It is responsible for analyze an image and detect if there it's a face or not.
 *
 * @author Daniela Martinez
 * @version 1.0
 */
public class FaceDetect {
    private String faceDetection;
    private static String current_dir; //full image path direction
    private static String result; //result of the face detection in a multiple or profile image

    private static int face_Detect;
    public FaceDetect (String file, String type) {
        Logger log = At18Logger.getLogger();
        faceDetection (file, type);

    }

    public static void faceDetection (String file, String type) {
        Logger log = At18Logger.getLogger();
        //Current_dir will take the direction of the path
        current_dir = System.getProperty("user.dir");

        //run the dll file, so the Open CV works correctly
        System.load(current_dir + "\\src\\main\\resources\\libraries\\opencv\\x64\\opencv_java454.dll");

        //imgFile it's the file we want to analyze if there it's a person in the photo
        String imgFile = current_dir +"\\"+ file;

        //Decodes the images
        Mat src = Imgcodecs.imread(imgFile.toString());

        // Are the connecting dots of a facial layout. This xml exactly defines a front-face layout
        String xmlFile ="D:\\workspacejala\\progra102\\JU-OBJECT-RECOGNITION\\resourcesdetect\\xml\\lbpcascade_frontalface.xml";

        //cascade classifiers is an effective object detection method
        CascadeClassifier cc = new CascadeClassifier(xmlFile);

        //detectMultiScale used to detect the faces. This function will return a rectangle with coordinates(x,y,w,h)
        MatOfRect faceDetection = new MatOfRect();

        //faceDetection will determine the amount of face that has in the image
        cc.detectMultiScale(src, faceDetection);

        //Condition to detect if the input image its a profile or its more people
        if (type.equals( "profile") && faceDetection.toArray().length == 1 ) {
            for(Rect rect: faceDetection.toArray()) {
                Imgproc.rectangle(src, new Point(rect.x, rect.y),
                        new Point(rect.x + rect.width, rect.y + rect.height) , new Scalar(0, 0, 255), 3);
            }

            //Save the image already processed with the face detection
            Imgcodecs.imwrite("D:\\workspacejala\\progra102\\JU-OBJECT-RECOGNITION\\resourcesdetect\\images\\facedetect.jpg", src);

            //logger: info that everything it right
            log.info("It's only one person in the image");

            //result if the image it's a profile
            result = "profile detected";//"";

        }  else if (type.equals("multiple") && faceDetection.toArray().length > 1 ) { //
            for(Rect rect: faceDetection.toArray()) {
                Imgproc.rectangle(src, new Point(rect.x, rect.y),
                        new Point(rect.x + rect.width, rect.y + rect.height) , new Scalar(0, 0, 255), 3);
            }

            Imgcodecs.imwrite("D:\\workspacejala\\progra102\\JU-OBJECT-RECOGNITION\\resourcesdetect\\images\\peopledetected.jpg", src);

            //the amount of face detected
            face_Detect=faceDetection.toArray().length;

            //logger: info that everything it right
            log.info("multiple people in the image");
            //final result of the process
            result ="multiple person detected" + String.valueOf(face_Detect);//String.format("Detected faces: %d" + faceDetection.toArray().length);
        } else {
            log.warning("It's a problem in the file or in the type");
           result = "Probably the type of image it's different from the image";
        }

    }
    public String getCommand() {
        Logger log = At18Logger.getLogger();
        log.info("the proccess go successfully");
        return result;
    }

}
