/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and property information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
package org.jalau.at18.searchobject.common.exception;

import java.io.IOException;
/**
 *Throws exception if the unzip process get wrong.
 *
 * @author Alvaro Sivila
 * @version 1.0
 */
public class UnzipFileException extends IOException {
    public UnzipFileException(String message) {
        super(message);
    }
    public UnzipFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
