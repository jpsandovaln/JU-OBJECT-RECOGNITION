/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and proprieraty information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
package org.jalau.at18.searchobject.controller.service;

import org.jalau.at18.searchobject.common.exception.NotifierTypeException;
import org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes.MatchInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * It is responsable of process the match and notify
 *
 * @author Maria Hurtado
 * @version 1.0
 */

@Service
public class ProcessMatchService {
    @Autowired
    NotifyService notifyService; //call the class notifyservice to send the result
    /**
     * method to process the matches result saved in the list
     * @param matches all the files that match with the object to search
     * @param notifierType the type of notyfier in this case Whatsapp
     * @param recipient the cellphone number
     * @throws NotifierTypeException if the notifier type is not one of the available notifiers
     */
    public void processMatches(List<MatchInfo> matches, String notifierType, String recipient) throws NotifierTypeException {
        notifyService.notifyService(matches, notifierType, recipient);
    }
}
