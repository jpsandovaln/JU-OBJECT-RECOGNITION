package org.jalau.at18.searchobject.controller.response;

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
