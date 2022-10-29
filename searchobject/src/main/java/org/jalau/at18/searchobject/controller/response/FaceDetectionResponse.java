package org.jalau.at18.searchobject.controller.response;

public class FaceDetectionResponse {
    private String result;

    public FaceDetectionResponse(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
