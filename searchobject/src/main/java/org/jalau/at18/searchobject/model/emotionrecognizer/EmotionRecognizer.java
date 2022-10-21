package org.jalau.at18.searchobject.model.emotionrecognizer;

import java.io.File;
import java.io.IOException;

import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import com.google.gson.Gson;

public class EmotionRecognizer {
    private static final int JOY_POSITION = 12;
    private static final int SORROW_POSITION = 11;
    private static final int ANGER_POSITION = 10;
    private static final int SURPRISE_POSITION = 9;
    private  String[] result;
    public EmotionRecognizer(String path, String token) throws IOException {
        String imageBase64 = convertImage(path);
        InputStream inputStream = httpRequest(token, imageBase64);
        result = processStream(inputStream);

    }

    private String convertImage(String filePath) throws IOException {
        byte[] fileContent = FileUtils.readFileToByteArray(new File(filePath));
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        String firstPart = "{\n\"requests\":[\n{\n\"image\":{\n\"content\":\"";
        String secondPart = "\"\n},\n\"features\":[\n{\n\"maxResults\":10,\n\"type\":\"FACE_DETECTION\"\n}\n]\n}\n]\n}";
        String imageBase64 = firstPart + encodedString + secondPart;
        return imageBase64;
    }
    public String[] getResult() {
        return result;
    }

    private String[] processStream(InputStream responStream) {
        Scanner scanner = new Scanner(responStream).useDelimiter("\\A");
        String response = scanner.hasNext() ? scanner.next() : "";
        String[] splitResponseWithoutLineBreak = response.split("\n");
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

    private InputStream httpRequest(String token, String encodedString) throws IOException {
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
    }
}
