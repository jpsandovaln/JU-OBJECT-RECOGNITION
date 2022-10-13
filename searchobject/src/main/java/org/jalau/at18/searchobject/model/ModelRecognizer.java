package org.jalau.at18.searchobject.model;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

public interface ModelRecognizer {
    List<MatchInfo> matching(Path pathFolder, String searchCriteria, int occurrencyPercentage);
}


