/*
  Copyright (c) 2022 Jala University.
  This software is the confidential and property information of Jalasoft
  ("Confidential Information"). You shall not disclose such Confidential
  Information and shall use it only in accordance with the terms of the
  Licence agreement you entered into with Jalasoft
*/
package org.jalau.at18.searchobject.message;

import org.jalau.at18.searchobject.controller.response.ResponseMessage;
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