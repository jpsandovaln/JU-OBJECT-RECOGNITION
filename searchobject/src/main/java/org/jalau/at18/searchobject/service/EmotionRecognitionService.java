package org.jalau.at18.searchobject.service;


import org.jalau.at18.searchobject.model.MatchInfo;
import org.jalau.at18.searchobject.model.emotionrecognizer.EmotionRecognizer;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmotionRecognitionService {
    public String[] processImage(Path pathImage, String token) throws IOException {
        EmotionRecognizer emotionRecognizer = new EmotionRecognizer(pathImage.toString(), token);
        return emotionRecognizer.getResult();
    }
}
