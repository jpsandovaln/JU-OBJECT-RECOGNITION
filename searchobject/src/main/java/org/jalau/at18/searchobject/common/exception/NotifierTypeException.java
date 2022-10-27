/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and proprieraty information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
package org.jalau.at18.searchobject.common.exception;

/**
 * Throws this NotifierException if the notifier type is not one of the available notifiers
 *
 * @author Rodrigo Bernal
 * @version 1.0
 */

public class NotifierTypeException extends Exception {

    public NotifierTypeException(String message) {
        super(message);
    }

    public NotifierTypeException(String message, Throwable ex) {
        super(message,ex);
    }

}
