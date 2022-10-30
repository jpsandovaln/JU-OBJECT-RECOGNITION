package org.jalau.at18.searchobject.notify;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class VerifyNotifierTest {

    @Test
    public void shouldGetWhatsappVerifyNotifier() {
        VerifyNotifier verifyNotifier = new VerifyNotifier();
        String whatsapp= "whatsapp";
        assertEquals(NotifierWhatsApp.class, verifyNotifier.getNotifier(whatsapp).getClass());
    }

    @Test
    public void shouldReturnNullWhenGettingVerifyNotifier() {
        VerifyNotifier verifyNotifier = new VerifyNotifier();
        String nullValue = "null";
        assertNull(verifyNotifier.getNotifier(nullValue));
    }
}