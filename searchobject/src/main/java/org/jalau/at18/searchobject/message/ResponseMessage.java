package org.jalau.at18.searchobject.message;

// The ResponseMessage is for message to client that we’re gonna use in Rest Controller and Exception Handler.
public class ResponseMessage {
    private String message;

    public ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
