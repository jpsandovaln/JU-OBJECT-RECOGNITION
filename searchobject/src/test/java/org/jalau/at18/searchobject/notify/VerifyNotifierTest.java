package org.jalau.at18.searchobject.notify;

import org.jalau.at18.searchobject.common.exception.NotifierTypeException;
import org.jalau.at18.searchobject.common.exception.UnzipFileException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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