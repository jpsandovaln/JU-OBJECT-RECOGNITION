package org.jalau.at18.searchobject.message;

import org.jalau.at18.searchobject.model.response.ResponseMessage;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ResponseMessageTest {

    @Test
    public void shouldGetMessage() {
        ResponseMessage responseMessage = new ResponseMessage("Random message");
        String expectedMessage = "Random message";
        assertEquals(expectedMessage, responseMessage.getMessage());
    }

    @Test
    public void shouldSetMessage() {
        ResponseMessage responseMessage = new ResponseMessage("message");
        String newMessage = "New message";
        responseMessage.setMessage(newMessage);
        assertEquals(newMessage, responseMessage.getMessage());
    }

    @Test
    public void shouldSetNullMessage() {
        ResponseMessage responseMessage = new ResponseMessage("message");
        responseMessage.setMessage(null);
        assertNull(responseMessage.getMessage());
    }
}