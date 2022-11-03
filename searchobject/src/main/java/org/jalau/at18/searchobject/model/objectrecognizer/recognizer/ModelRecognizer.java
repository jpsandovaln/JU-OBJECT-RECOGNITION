package org.jalau.at18.searchobject.model.objectrecognizer.recognizer;
/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and property information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
import org.jalau.at18.searchobject.common.exception.ObjectRecognizerException;
import org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes.MatchInfo;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
/**
 * Interface, implement the design patter factory method
 *
 * @author Maria Hurtado
 * @version 1.0
 */
public interface ModelRecognizer {
    /**
     *
     *method to process the matches result
     *@param pathFolder path zip folder
     *@param searchCriteria object to search
     *@param occurrencyPercentage score match
     */
    List<MatchInfo> matching(Path pathFolder, String searchCriteria, int occurrencyPercentage);
}


