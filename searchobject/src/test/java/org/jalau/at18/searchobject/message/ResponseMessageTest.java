package org.jalau.at18.searchobject.message;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ResponseMessageTest {

    @Test
    void shouldGetMessage() {
        ResponseMessage responseMessage = new ResponseMessage("Random message");
        String expectedMessage = "Random message";
        assertEquals(expectedMessage, responseMessage.getMessage());
    }

    @Test
    void shouldSetMessage() {
        ResponseMessage responseMessage = new ResponseMessage("message");
        String newMessage = "New message";
        responseMessage.setMessage(newMessage);
        assertEquals(newMessage, responseMessage.getMessage());
    }

    @Test
    void shouldSetNullMessage() {
        ResponseMessage responseMessage = new ResponseMessage("message");
        responseMessage.setMessage(null);
        assertNull(responseMessage.getMessage());
    }
}