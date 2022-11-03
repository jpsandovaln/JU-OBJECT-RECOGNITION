package org.jalau.at18.searchobject.model.objectrecognizer.recognizer;
/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and property information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
import org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes.MatchInfo;

import java.nio.file.Path;
import java.util.List;

public interface ModelRecognizer {
    List<MatchInfo> matching(Path pathFolder, String searchCriteria, int occurrencyPercentage);
}


