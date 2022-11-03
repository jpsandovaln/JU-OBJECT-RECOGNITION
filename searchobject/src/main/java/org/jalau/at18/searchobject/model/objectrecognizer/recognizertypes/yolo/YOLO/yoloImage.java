package org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes.yolo.YOLO;
/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and property information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
import org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes.yolo.TransformTXT.Transform;
// Source to review the code: https://gurzu.com/blog/YOLO_v3_From_Python_To_Java/
import org.opencv.core.*;
import org.opencv.dnn.Dnn;
import org.opencv.dnn.Net;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
/**
 *
 * Receives an image (608*424 o 608*608) and returns the probability of the object existing
 * By using the YOLO Model
 *
 * @author Mauricio Aliendre
 * @version 1.0
 */

public class yoloImage {
    // Defining some global variables
    private final String model_weights;
    private final String model_config;
    private final String current_dir;
    private final String class_file_name_dir;
    private final List<String> classes;
    private final List<String> output_layers;
    private int ocurrencyPercentage;
    private String input_path;
    private List<String> layer_names;
    private Net network;
    private Size size;
    private Integer height;
    private Integer width;
    private Integer channels;
    private Scalar mean;
    private Mat image;
    private Mat blob;
    private List<Mat> outputs;
    private List<Rect2d> boxes;
    private List<Float> confidences;
    private List<Integer> class_ids;
    private boolean save;
    private boolean errors;

    private Transform arrayClasses;
    private int image_size = 608;
    private double score;

    /**
     * Constructor for Object detection with model Faster Rcnn Inception
     * @param inputPath represents the path of the file
     * @param ocurrencyPercentage represents the minimum prob that object must have in order to be considered
     */

    public yoloImage (String inputPath, int ocurrencyPercentage) {
        current_dir = System.getProperty("user.dir");
        System.load(current_dir + "\\src\\main\\resources\\libraries\\opencv\\x64\\opencv_java454.dll");
        this.input_path = inputPath;
        this.ocurrencyPercentage = ocurrencyPercentage;
        arrayClasses = new Transform();
        boxes = new ArrayList<>();
        classes = new ArrayList<>();
        class_ids = new ArrayList<>();
        layer_names = new ArrayList<>();
        confidences = new ArrayList<>();
        double[] means = {0.0, 0.0, 0.0};
        mean = new Scalar(means);
        output_layers = new ArrayList<>();
        size = new Size(image_size, image_size);
        System.out.println(current_dir);
        model_weights = current_dir + "\\src\\main\\resources\\YOLOConfigurationFiles\\yolov3.weights";
        model_config = current_dir + "\\src\\main\\resources\\YOLOConfigurationFiles\\yolov3.cfg";
        class_file_name_dir = current_dir + "\\src\\main\\resources\\YOLOConfigurationFiles\\yolov3.txt";
        save = true;
    }

    private static int argmax(List<Float> array) {
        float max = array.get(0);
        int re = 0;
        for (int i = 1; i < array.size(); i++) {
            if (array.get(i) > max) {
                max = array.get(i);
                re = i;
            }
        }
        return re;
    }

    private void setClasses() {
        try {
            File f = new File(class_file_name_dir);
            Scanner reader = new Scanner(f);
            while (reader.hasNextLine()) {
                String class_name = reader.nextLine();
                classes.add(class_name);
            }
        } catch (FileNotFoundException e) {
            errors = true;
        }
    }

    private void setNetwork() {
        network = Dnn.readNet(model_weights, model_config);
    }

    private void setUnconnectedLayers() {

        for (Integer i : network.getUnconnectedOutLayers().toList()) {
            output_layers.add(layer_names.get(i - 1));
        }
    }

    private void setLayerNames() {
        layer_names = network.getLayerNames();
    }
    /**
     * In charge of loading the Image and resizing
     */
    private void loadImage() {
        Mat img = Imgcodecs.imread(input_path);
        Mat resizedImage = new Mat();
        Imgproc.resize(img, resizedImage, size, 0.9, 0.9);
        height = resizedImage.height();
        width = resizedImage.width();
        channels = resizedImage.channels();
        image = resizedImage;
        System.out.println(image);
    }

    private void detectObject() {
        Mat blob_from_image = Dnn.blobFromImage(image, 0.00392, size, mean, true, false);
        network.setInput(blob_from_image);
        outputs = new ArrayList<Mat>();
        network.forward(outputs, output_layers);
        blob = blob_from_image;
    }
    /**
     * Determines the box Dimensions for the object found in the image
     */
    private void getBoxDimensions() {
        for (Mat output : outputs) {

            for (int i = 0; i < output.height(); i++) {
                Mat row = output.row(i);
                MatOfFloat temp = new MatOfFloat(row);
                List<Float> detect = temp.toList();
                List<Float> score = detect.subList(5, 85);
                int class_id = argmax(score);
                float conf = score.get(class_id);
                if (conf >= (float) ocurrencyPercentage / 100) {
                    int center_x = (int) (detect.get(0) * width);
                    int center_y = (int) (detect.get(1) * height);
                    int w = (int) (detect.get(2) * width);
                    int h = (int) (detect.get(3) * height);
                    int x = (center_x - w / 2);
                    int y = (center_y - h / 2);
                    Rect2d box = new Rect2d(x, y, w, h);
                    boxes.add(box);
                    confidences.add(conf);
                    class_ids.add(class_id);
                }
            }
        }
    }

    /**
     * In charge of drawing the labels onto the object found
     */
    private void drawLabels() {
        double[] rgb = new double[]{255, 255, 0};
        Scalar color = new Scalar(rgb);
        MatOfRect2d mat = new MatOfRect2d();
        mat.fromList(boxes);
        MatOfFloat confidence = new MatOfFloat();
        confidence.fromList(confidences);
        MatOfInt indices = new MatOfInt();
        int font = Imgproc.FONT_HERSHEY_PLAIN;
        Dnn.NMSBoxes(mat, confidence, (float) (0.4), (float) (0.4), indices);
        List indices_list = indices.toList();
        for (int i = 0; i < boxes.size(); i++) {
            if (indices_list.contains(i)) {
                if (save) {
                    Rect2d box = boxes.get(i);
                    Point x_y = new Point(box.x, box.y);
                    Point w_h = new Point(box.x + box.width, box.y + box.height);
                    Point text_point = new Point(box.x, box.y - 5);
                    Imgproc.rectangle(image, w_h, x_y, color);
                    String label = classes.get(class_ids.get(i));
                    Imgproc.putText(image, label, text_point, font, 1, color);
                }

            }
        }
    }
    /**
     * In charge of loading the pipeline which is the end-to-end construct that orchestrates the flow of data
     * into, and output from, a machine learning model
     */
    public void loadPipeline() {
        try {
            setNetwork();
            System.out.println("set Network done");
            setClasses();
            System.out.println("set Classes done");
            setLayerNames();
            System.out.println("set Layer Names done");
            setUnconnectedLayers();
            System.out.println("set Unconnected Layers (Output)  done");
            loadImage();
            System.out.println("load Image done");
            detectObject();
            System.out.println("detect Object done");
            getBoxDimensions();
            System.out.println("get Box Dimensions done");
            drawLabels();
            System.out.println("draw labels done");
        } catch (Exception e) {
            System.out.println(e);
            errors = true;
        }

    }


    private double median(List<Float> total) {
        double j = 0;
        Collections.sort(total);
        int size = total.size();
        if (size % 2 == 1 && size != 0) {
            j = total.get((size - 1) / 2);
        } else if (size % 2 == 0 && size != 0){
            j = (total.get(size / 2 - 1) + total.get(size / 2) + 0.0) / 2;
        } else {
            j = size;
        }
        return j;
    }

    public double getTheResult(String searchedElement){
        List<Float> accumulator = new ArrayList<Float>();
            for (int j=0; j < class_ids.size() ; j++){
                if(arrayClasses.getTheVectorClass().indexOf(searchedElement) == class_ids.get(j)){
                    accumulator.add(confidences.get(j));
                }
            }
            score = median(accumulator);
            return score;
    }
}
