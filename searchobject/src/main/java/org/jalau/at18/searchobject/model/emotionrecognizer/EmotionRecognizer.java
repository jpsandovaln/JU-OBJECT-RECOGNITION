/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and property information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
package org.jalau.at18.searchobject.model.emotionrecognizer;

import java.io.File;

import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.jalau.at18.searchobject.common.exception.EmotionRecognizerException;
/**
 *
 * @author Sergio Lema
 * @version 1.0
 */


public class EmotionRecognizer {
    private static final int JOY_POSITION = 12;// take the position of the information of the json code of the API
    private static final int SORROW_POSITION = 11;// take the position of the information of the json code of the API
    private static final int ANGER_POSITION = 10;// take the position of the information of the json code of the API
    private static final int SURPRISE_POSITION = 9; // take the position of the information of the json code of the API
    private  String[] result; //Save the result in an array
    /**
     * Contructor, call the three methods to convert the image in base 64, input stream it's the communication with the API and
     * take the result only the information that it's needed
     * @param path image path
     * @param token access to the service
     * @throws EmotionRecognizerException if there is an error reading the file or with the http request
     */
    public EmotionRecognizer(String path, String token) throws EmotionRecognizerException {
        String imageBase64 = convertImage(path);
        InputStream inputStream = httpRequest(token, imageBase64);
        result = processStream(inputStream);

    }

    /**
     * method to decode the image
     * @param filePath
     * @throws EmotionRecognizerException if there is an error reading the file or with the http request
     */
    private String convertImage(String filePath) throws EmotionRecognizerException {
        try {
            byte[] fileContent = FileUtils.readFileToByteArray(new File(filePath));
            String encodedString = Base64.getEncoder().encodeToString(fileContent);
            String firstPart = "{\n\"requests\":[\n{\n\"image\":{\n\"content\":\""; //decode the image and transform in ajson code
            String secondPart = "\"\n},\n\"features\":[\n{\n\"maxResults\":10,\n\"type\":\"FACE_DETECTION\"\n}\n]\n}\n]\n}"; //json code
            String imageBase64 = firstPart + encodedString + secondPart;  // unify the decode of the image and facedetection
        return imageBase64;
        } catch (Exception e) {
            throw new EmotionRecognizerException("Error reading the file",e);
        }
    }
    /**
     *Method to get the result of the emotion detection
     * @
     */
    public String[] getResult() {
        return result;
    }
    /**
     * take the json of the API and take the information that it's going to be needed
     * @param responStream information
     *
     */
    private String[] processStream(InputStream responStream) {
        Scanner scanner = new Scanner(responStream).useDelimiter("\\A");
        String response = scanner.hasNext() ? scanner.next() : "";
        String[] splitResponseWithoutLineBreak = response.split("\n"); //take out the line break
        String[] emotionsArray = { splitResponseWithoutLineBreak[splitResponseWithoutLineBreak.length - JOY_POSITION],
                splitResponseWithoutLineBreak[splitResponseWithoutLineBreak.length - SORROW_POSITION],
                splitResponseWithoutLineBreak[splitResponseWithoutLineBreak.length - ANGER_POSITION],
                splitResponseWithoutLineBreak[splitResponseWithoutLineBreak.length - SURPRISE_POSITION] };
        for (int i = 0; i < emotionsArray.length; i++) {
            emotionsArray[i] = emotionsArray[i].replaceAll(" ", "");
            System.out.println(emotionsArray[i]);

        }
        return emotionsArray;
    }
    /**
     * Communication with the API
     * @param token access to the service
     * @param encodedString the image encoded
     * @throws EmotionRecognizerException if there is an error reading the file or with the http request
     */
    private InputStream httpRequest(String token, String encodedString) throws EmotionRecognizerException  {
        try {
            URL url = new URL("https://vision.googleapis.com/v1/images:annotate");
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("POST");

            httpConn.setRequestProperty("Authorization", "Bearer " + token);
            httpConn.setRequestProperty("Content-Type", "application/json");

            httpConn.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(httpConn.getOutputStream());
            writer.write(encodedString);
            writer.flush();
            writer.close();
            httpConn.getOutputStream().close();

            InputStream responseStream = httpConn.getResponseCode() / 100 == 2
                    ? httpConn.getInputStream()
                    : httpConn.getErrorStream();
            return responseStream;
        } catch (Exception e) {
            throw new EmotionRecognizerException("Error with the httpRequest",e);
        }

    }
}
