package org.jalau.at18.searchobject.common.exception;

import java.io.IOException;

public class UnzipFileException extends IOException {
    public UnzipFileException(String message) {
        super(message);
    }
    public UnzipFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
