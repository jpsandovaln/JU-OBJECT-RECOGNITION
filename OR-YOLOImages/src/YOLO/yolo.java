package YOLO;


import org.opencv.core.*;
import org.opencv.dnn.*;
import org.opencv.utils.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

//import com.streambase.com.gs.collections.impl.Counter;

import java.util.ArrayList;
import java.util.List;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;




public class yolo {
	
	private static List<String> getOutputNames(Net net) {
		List<String> names = new ArrayList<>();

        List<Integer> outLayers = net.getUnconnectedOutLayers().toList();
        List<String> layersNames = net.getLayerNames();

        outLayers.forEach((item) -> names.add(layersNames.get(item - 1)));//unfold and create R-CNN layers from the loaded app.src.main.java.ju.object.recognition.YOLO model//
        return names;
	}
	public static void main(String[] args) throws InterruptedException {
		System.load("C:\\Program Files\\Java\\jdk-18.0.2\\lib\\opencv\\build\\java\\x64\\opencv_java454.dll"); // Load the openCV 4.0 dll //
		 String modelWeights = "C:\\Users\\TUF GAMER\\Desktop\\yoloV3\\yolov3.weights"; //Download and load only wights for app.src.main.java.ju.object.recognition.YOLO , this is obtained from official app.src.main.java.ju.object.recognition.YOLO site//
	        String modelConfiguration = "C:\\Users\\TUF GAMER\\Downloads\\yolov3.cfg";//Download and load cfg file for app.src.main.java.ju.object.recognition.YOLO , can be obtained from official site//
	        String filePath = "C:\\Users\\TUF GAMER\\Pictures\\production ID_3987730.mp4"; //My video  file to be analysed//
	        VideoCapture cap = new VideoCapture(filePath);// Load video using the videocapture method//
	         Mat frame = new Mat(); // define a matrix to extract and store pixel info from video//
	        Mat dst = new Mat ();
	         //cap.read(frame);
	         JFrame jframe = new JFrame("Video"); // the lines below create a frame to display the resultant video with object detection and localization//
	         JLabel vidpanel = new JLabel();
	         jframe.setContentPane(vidpanel);
	         jframe.setSize(1200, 1200);
	         jframe.setVisible(true);// we instantiate the frame here//

	        Net net = Dnn.readNetFromDarknet(modelConfiguration, modelWeights); //OpenCV DNN supports models trained from various frameworks like Caffe and TensorFlow. It also supports various networks architectures based on app.src.main.java.ju.object.recognition.YOLO//
	        //Thread.sleep(5000);

	        //Mat image = Imgcodecs.imread("D:\\yolo-object-detection\\yolo-object-detection\\images\\soccer.jpg");
	        Size sz = new Size(288,288);
	        
	        List<Mat> result = new ArrayList<>();
	        List<String> outBlobNames = getOutputNames(net);
	       
	        while (true) {
	        	
	            if (cap.read(frame)) {





	        Mat blob = Dnn.blobFromImage(frame, 0.00392, sz, new Scalar(0), true, false); // We feed one frame of video into the network at a time, we have to convert the image to a blob. A blob is a pre-processed image that serves as the input.//
	        net.setInput(blob);



	        net.forward(result, outBlobNames); //Feed forward the model to get output //




	       // outBlobNames.forEach(System.out::println);
	       // result.forEach(System.out::println);

	        float confThreshold = 0.6f; //Insert thresholding beyond which the model will detect objects//
	        List<Integer> clsIds = new ArrayList<>();
	        List<Float> confs = new ArrayList<>();
	        List<Rect2d> rects = new ArrayList<>();
	        for (int i = 0; i < result.size(); ++i)
	        {
	            // each row is a candidate detection, the 1st 4 numbers are
	            // [center_x, center_y, width, height], followed by (N-4) class probabilities
	            Mat level = result.get(i);
	            for (int j = 0; j < level.rows(); ++j)
	            {
	                Mat row = level.row(j);
	                Mat scores = row.colRange(5, level.cols());
	                Core.MinMaxLocResult mm = Core.minMaxLoc(scores);
	                float confidence = (float)mm.maxVal;
	                Point classIdPoint = mm.maxLoc;
	                if (confidence > confThreshold)
	                {
	                    int centerX = (int)(row.get(0,0)[0] * frame.cols()); //scaling for drawing the bounding boxes//
	                    int centerY = (int)(row.get(0,1)[0] * frame.rows());
	                    int width   = (int)(row.get(0,2)[0] * frame.cols());
	                    int height  = (int)(row.get(0,3)[0] * frame.rows());
	                    int left    = centerX - width  / 2;
	                    int top     = centerY - height / 2;

	                    clsIds.add((int)classIdPoint.x);
	                    confs.add((float)confidence);
	                   rects.add(new Rect2d(left, top, width, height));
	                }
	            }
	        }
	        float nmsThresh = 0.5f;
	        MatOfFloat confidences = new MatOfFloat(Converters.vector_float_to_Mat(confs));
	        Rect2d[] boxesArray = rects.toArray(new Rect2d[0]);
	        MatOfRect2d boxes = new MatOfRect2d(boxesArray);
	        MatOfInt indices = new MatOfInt();
	        Dnn.NMSBoxes(boxes, confidences, confThreshold, nmsThresh, indices); //We draw the bounding boxes for objects here//

	        int [] ind = indices.toArray();
	        int j=0;
	        for (int i = 0; i < ind.length; ++i)
	        {
	            int idx = ind[i];
	            Rect2d box = boxesArray[idx];
	            Imgproc.rectangle(frame, box.tl(), box.br(), new Scalar(0,0,255), 2);
	            //i=j;

	            System.out.println(idx);
	        }
			System.out.println("Image Loaded");
	        ImageIcon image = new ImageIcon(Mat2bufferedImage(frame)); //setting the results into a frame and initializing it //
       	 	vidpanel.setIcon(image);
				vidpanel.repaint();
//				Imgcodecs.imwrite("C:\\out.png", image);
	        // System.out.println(j);
	        // System.out.println("Done");

	        }
	            }      
	}     
	private static BufferedImage Mat2bufferedImage(Mat image) {   // The class described here  takes in matrix and renders the video to the frame  //
		MatOfByte bytemat = new MatOfByte();
		Imgcodecs.imencode(".jpg", image, bytemat);
		byte[] bytes = bytemat.toArray();
		InputStream in = new ByteArrayInputStream(bytes);
		BufferedImage img = null;
		try {
			img = ImageIO.read(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
	}
}