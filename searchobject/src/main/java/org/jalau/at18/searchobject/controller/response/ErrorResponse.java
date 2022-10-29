/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and proprieraty information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
package org.jalau.at18.searchobject.controller.response;

/**
 *
 *
 * @author Rodrigo Bernal
 * @version 1.0
 */
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
