package org.jalau.at18.searchobject.notify;
/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and property information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
import java.util.List;

import org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes.MatchInfo;
/**
 * Interface notification
 * @author Maria Hurtado
 * @version 1.0
 */
public interface Notifier {
    /**
     * Method to notify
     * @param matches list of images that matches
     * @param recipient cellphone number
     */
    void notify(List<MatchInfo> matches, String recipient);
}
