/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and property information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
package org.jalau.at18.searchobject.common.exception;

/**
 *Throws exception if a field is empty, if an invalid file is sent, if an invalid token is contained or if no token.
 *
 * @author Sarai Alvarez
 * @version 1.0
 */
public class MiddlewareException extends Exception{

    public MiddlewareException(String message) {
        super(message);
    }

    public MiddlewareException(String message, Throwable ex) {
        super(message,ex);
    }

}