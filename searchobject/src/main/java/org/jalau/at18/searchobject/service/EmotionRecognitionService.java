package org.jalau.at18.searchobject.service;


import org.jalau.at18.searchobject.model.MatchInfo;
import org.springframework.stereotype.Service;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmotionRecognitionService {
    public MatchInfo processImage(Path pathImage, String token) {

        MatchInfo test = new MatchInfo(token, 0.151331);
        return test;
    }
}
