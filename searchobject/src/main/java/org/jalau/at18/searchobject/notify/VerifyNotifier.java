/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and proprieraty information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
package org.jalau.at18.searchobject.notify;

import org.jalau.at18.searchobject.common.exception.NotifierTypeException;

/**
 *
 * @throws NotifierTypeException if the notifier type is not one of the available notifiers
 */
public class VerifyNotifier {
    public Notifier getNotifier(String notifierType) throws NotifierTypeException {
        if(notifierType.equals(TypeNotifier.WHATSAPP.getNotifier())) {
            return new NotifierWhatsApp();
        }
        throw new NotifierTypeException("The notifier type is not available");
    }
}
