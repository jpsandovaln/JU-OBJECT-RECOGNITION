/*
  Copyright (c) 2022 Jala University.
  This software is the confidential and property information of Jalasoft
  ("Confidential Information"). You shall not disclose such Confidential
  Information and shall use it only in accordance with the terms of the
  Licence agreement you entered into with Jalasoft
*/
package org.jalau.at18.searchobject.notify;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TypeNotifierTest {

    @Test
    public void shouldGetEnumTypeWhatsapp() {
        String expected = "whatsapp";
        assertEquals(expected, TypeNotifier.WHATSAPP.getNotifier());
    }

    @Test
    public void shouldGetEnumTypeEmail() {
        String expected = "email";
        assertEquals(expected, TypeNotifier.EMAIL.getNotifier());
    }

    @Test
    public void shouldGetEnumTypeMessage() {
        String expected = "message";
        assertEquals(expected, TypeNotifier.MESSAGE.getNotifier());
    }
}