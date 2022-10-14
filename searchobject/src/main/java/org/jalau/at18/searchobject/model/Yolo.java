package org.jalau.at18.searchobject.model;

import org.example.YOLOmodel;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

public class Yolo implements ModelRecognizer {
    @Override
    public List<MatchInfo> matching(Path pathFolder, String searchCriteria, int occurrencyPercentage) {
        YOLOmodel yolo = new YOLOmodel(pathFolder, searchCriteria, occurrencyPercentage);
        return null;

    }
}
