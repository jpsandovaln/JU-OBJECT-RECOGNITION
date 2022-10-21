package org.jalau.at18.searchobject.model.emotionrecognizer;

import java.io.File;
import java.io.IOException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.apache.tomcat.util.log.SystemLogHandler;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParser;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.protobuf.Message;

class Main {
    private static String filePath = "C:\\Users\\Sergio-Depa\\Desktop\\AT18\\Coding AT18\\practice_git\\dev102\\emotionrecognizer\\src\\main\\resources\\joy.jpg";
    private static Gson gson;

    public static String convertImage() throws IOException {
        byte[] fileContent = FileUtils.readFileToByteArray(new File(filePath));
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        return encodedString;
    }

    public static void main(String[] args) throws IOException, ParseException {
        String firstPart = "{\n\"requests\":[\n{\n\"image\":{\n\"content\":\"";
        String secondPart = "\"\n},\n\"features\":[\n{\n\"maxResults\":10,\n\"type\":\"FACE_DETECTION\"\n}\n]\n}\n]\n}";
        String imageBase64 = convertImage();
        String encodedString = firstPart + imageBase64 + secondPart;
        URL url = new URL("https://vision.googleapis.com/v1/images:annotate");
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("POST");

        httpConn.setRequestProperty("Authorization",
                "Bearer ya29.c.b0AUFJQsH66hiD54hFkAHvqms6LPQzEsUgoPhDUougczNQ4c73226ulnNvFMUEGW5akLhpXmdQNF2Uodr6Op9svMBEFQ95NnOcjDNxObQWisBbt_L1rAv9xMzbBWJTscYrq9d6vSauczaq6Z8p2dN-l_oxmY1ovG4Na_vDyvLJT2ToqjDgJx0q4-tSPz_Vd8jy0Bp6uMBI-eHIBUeX758MSkO73FvKaJS7UYfb1Rct0N8");
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
        Scanner s = new Scanner(responseStream).useDelimiter("\\A");
        String response = s.hasNext() ? s.next() : "";
        System.out.println(response);
        // System.out.println();
        // String[] splitResponse = response.split("");
        // System.out.println(Arrays.asList(splitResponse));
    }
}
