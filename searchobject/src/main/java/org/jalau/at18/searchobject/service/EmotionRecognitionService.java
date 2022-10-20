package org.jalau.at18.searchobject.service;


import org.springframework.stereotype.Service;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmotionRecognitionService {
    public List<String> processImage(Path pathImage) {
        List<String> test = new ArrayList<>();
        test.add(String.valueOf(pathImage));
        test.add("Test Emotion");
        test.add("Angry = 100%");
        return test;
    }
}
