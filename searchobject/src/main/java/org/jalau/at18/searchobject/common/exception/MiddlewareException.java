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