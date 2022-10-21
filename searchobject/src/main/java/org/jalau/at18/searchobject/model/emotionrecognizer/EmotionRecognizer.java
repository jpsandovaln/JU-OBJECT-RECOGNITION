package org.jalau.at18.searchobject.model.emotionrecognizer;

import com.ctc.wstx.shaded.msv_core.util.Uri;
import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.FaceAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.protobuf.ByteString;

import net.lingala.zip4j.util.Raw;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.commons.io.FileUtils;

import java.net.URI;
import java.net.http.HttpRequest;

public class EmotionRecognizer {
    private static final String URL_API = "https://vision.googleapis.com/v1/images:annotate";
    private static String tokenApi;
    private static String path;
    private static String filePath = "C:\\Users\\Sergio-Depa\\Desktop\\AT18\\Coding AT18\\practice_git\\dev102\\emotionrecognizer\\src\\main\\resources\\joy.jpg";

    public EmotionRecognizer(String path, String tokenApi) throws IOException {
        this.path = path;
        this.tokenApi = tokenApi;
        convertImage();
    }

    public String convertImage() throws IOException {
        byte[] fileContent = FileUtils.readFileToByteArray(new File(filePath));
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        return encodedString;
    }

    // Detects faces in the specified local image.
    public static void detectFaces(String filePath) throws IOException {
        List<AnnotateImageRequest> requests = new ArrayList<>();

        ByteString imgBytes = ByteString.readFrom(new FileInputStream(filePath));

        Image img = Image.newBuilder().setContent(imgBytes).build();
        Feature feat = Feature.newBuilder().setType(Feature.Type.FACE_DETECTION).build();
        AnnotateImageRequest request = AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
        requests.add(request);

        // Initialize client that will be used to send requests. This client only needs
        // to be created
        // once, and can be reused for multiple requests. After completing all of your
        // requests, call
        // the "close" method on the client to safely clean up any remaining background
        // resources.
        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
            BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
            List<AnnotateImageResponse> responses = response.getResponsesList();

            for (AnnotateImageResponse res : responses) {
                if (res.hasError()) {
                    System.out.format("Error: %s%n", res.getError().getMessage());
                    return;
                }

                // For full list of available annotations, see http://g.co/cloud/vision/docs
                for (FaceAnnotation annotation : res.getFaceAnnotationsList()) {
                    System.out.format(
                            "anger: %s%njoy: %s%nsurprise: %s%nsorrow %s",
                            annotation.getAngerLikelihoodValue(),
                            annotation.getJoyLikelihoodValue(),
                            annotation.getSurpriseLikelihoodValue(),
                            annotation.getSorrowLikelihoodValue());
                }
            }
        }
    }
}
