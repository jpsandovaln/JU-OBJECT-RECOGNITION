package org.jalau.at18.searchobject.controller.response;
/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and property information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */

/**
 * The TokenResponse is for the message to the client that we are going to use in the GenerateToken class.
 *
 * @author Sarai Alvarez
 * @version 1.0
 */
public class TokenResponse {
        private StringBuilder message;

        public TokenResponse(StringBuilder message) {
            this.message = message;
        }

        public StringBuilder getMessage() {
            return message;
        }

        public void setMessage(StringBuilder message) {
            this.message = message;
        }
}
