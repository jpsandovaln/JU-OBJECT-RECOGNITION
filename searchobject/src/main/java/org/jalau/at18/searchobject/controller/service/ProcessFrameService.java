/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and property information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
package org.jalau.at18.searchobject.controller.service;

import org.jalau.at18.searchobject.common.exception.ObjectRecognizerException;
import org.jalau.at18.searchobject.model.objectrecognizer.recognizer.ModelRecognizer;
import org.jalau.at18.searchobject.model.objectrecognizer.recognizer.VerifyModelRecognizer;
import org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes.MatchInfo;
import org.springframework.stereotype.Service;
import java.nio.file.Path;
import java.util.List;
/**
 * It is responsable of process the service
 *
 * @author Maria Hurtado
 * @version 1.0
 */

@Service
public class ProcessFrameService {
    /**
     *
     *method to process the matches result saved in the list
     *@param zipFilePath path zip folder
     *@param searchCriteria object to search
     *@param occurrencyPercentage score match
     * @throws ObjectRecognizerException if the model recognizer type is not one of the available recognizers
     */
    public List<MatchInfo> processFrameAccordingCriteria(Path zipFilePath,
                                                         String searchCriteria,
                                                         int occurrencyPercentage,
                                                         String modelObjectRecognizer) throws ObjectRecognizerException {
        // Verifying the model
        VerifyModelRecognizer verifyModelRecognizer = new VerifyModelRecognizer();
        //recognize the model and start the process
        ModelRecognizer modelRecognizer = verifyModelRecognizer.getModelRecognizer(modelObjectRecognizer);
        //saved info result
        List<MatchInfo> matchInfos = modelRecognizer.matching(zipFilePath, searchCriteria, occurrencyPercentage);
        return matchInfos;
    }
}
