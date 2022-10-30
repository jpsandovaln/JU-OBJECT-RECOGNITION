/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and property information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
package org.jalau.at18.searchobject.controller.response;

/**
 * It is to return a json in the postman when there is an Exception
 *
 * @author Rodrigo Bernal
 * @version 1.0
 */
public class ErrorResponse {
    private String status;
    private String errorMessage;

    /**
     * Constructor to the Error response
     * @param status the code of the exception
     * @param errorMessage the message of the exception
     *
     */

    public ErrorResponse(String status, String errorMessage) {
        this.errorMessage = errorMessage;
        this.status = status;
    }
    /**
     * Method that returns the errorMessage
     * @return the erroeMessage
     */
    public String getErrorMessage() {
        return this.errorMessage;
    }
    /**
     * Method that sets the value of the errorMessage
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Method that returns the status
     * @return the status
     */
    public String getStatus() {
        return this.status;
    }
    /**
     * Method that sets the value of the status
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
