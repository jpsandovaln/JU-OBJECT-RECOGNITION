package org.jalau.at18.searchobject.model.facedetector;
/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and property information of Jalasoft
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
    private static String currentDir; //full image path direction
    private static String result; //result of the face detection in a multiple or profile image
    private static int faceDetect;
    /**
     * Constructor to the face detection
     * @param file the image that it's going to be in the process of detection
     * @param type the type of result we desire to find in the input image
     */
    public FaceDetect (String file, String type) {
        Logger log = At18Logger.getLogger();
        faceDetection (file, type);

    }
    /**
     * Method that it's the process of the face detection where create the square in the face if it's detected
     */
    public static void faceDetection (String file, String type) {
        Logger log = At18Logger.getLogger();
        //Current_dir will take the direction of the path
        currentDir = System.getProperty("user.dir");

        //run the dll file, so the Open CV works correctly
        System.load(currentDir + "\\src\\main\\resources\\libraries\\opencv\\x64\\opencv_java454.dll");

        //imgFile it's the file we want to analyze if there it's a person in the photo
        String imgFile = currentDir +"\\"+ file;

        //Decodes the images
        Mat src = Imgcodecs.imread(imgFile);

        // Are the connecting dots of a facial layout. This xml exactly defines a front-face layout
        String xmlFile =currentDir + "\\src\\main\\resources\\xml\\lbpcascade_frontalface.xml";

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

            String finalFile = file.substring(22,file.length());
            //Save the image already processed with the face detection
            Imgcodecs.imwrite(currentDir + "\\src\\main\\resources\\images\\" + finalFile, src);
            //logger: info that everything it right
            log.info("It's only one person in the image");
            //result if the image it's a profile
            result = "profile detected";//"";

        }  else if (type.equals("multiple") && faceDetection.toArray().length > 1 ) { //
            for(Rect rect: faceDetection.toArray()) {
                Imgproc.rectangle(src, new Point(rect.x, rect.y),
                        new Point(rect.x + rect.width, rect.y + rect.height) , new Scalar(0, 0, 255), 3);
            }
            String finalFile = file.substring(22,file.length());
            Imgcodecs.imwrite(currentDir + "\\src\\main\\resources\\images\\" + finalFile, src);

            //the amount of face detected
            faceDetect=faceDetection.toArray().length;
            //logger: info that everything it right
            log.info("multiple people in the image");
            //final result of the process
            result ="multiple person detected" +" "+ "\n" + "Quantity of face detect: " + String.valueOf(faceDetect);
        } else {
            //
            log.warning("It's a problem in the file or in the type");
           result = "Probably the type of image it's different from the image";
        }
    }

    /**
     * Method that return the result after the face detection in the image
     * @return The result if the face detection, if its a profile or multiple
     */
    public String getCommand() {
        Logger log = At18Logger.getLogger();
        log.info("the proccess go successfully");
        return result;
    }
}
