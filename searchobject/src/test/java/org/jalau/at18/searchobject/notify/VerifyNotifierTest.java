/*
  Copyright (c) 2022 Jala University.
  This software is the confidential and property information of Jalasoft
  ("Confidential Information"). You shall not disclose such Confidential
  Information and shall use it only in accordance with the terms of the
  Licence agreement you entered into with Jalasoft
*/
package org.jalau.at18.searchobject.notify;

import org.jalau.at18.searchobject.common.exception.NotifierTypeException;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class VerifyNotifierTest {

    @Test
    public void shouldGetWhatsappVerifyNotifier() throws NotifierTypeException {
        VerifyNotifier verifyNotifier = new VerifyNotifier();
        String whatsapp= "whatsapp";
        assertEquals(NotifierWhatsApp.class, verifyNotifier.getNotifier(whatsapp).getClass());
    }

    @Test (expected = NotifierTypeException.class)
    public void shouldReturnNullWhenGettingVerifyNotifier() throws NotifierTypeException {
        VerifyNotifier verifyNotifier = new VerifyNotifier();
        String nullValue = "null";
        verifyNotifier.getNotifier(nullValue);
    }
}