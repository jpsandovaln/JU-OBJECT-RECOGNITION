package org.jalau.at18.searchobject.controller.response;

public class ErrorResponse {
    private String errorMessage;
    private String status;

    public ErrorResponse(String status, String errorMessage) {
        this.errorMessage = errorMessage;
        this.status = status;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
