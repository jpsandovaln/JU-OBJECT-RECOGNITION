package org.jalau.at18.searchobject.notify;

public class VerifyNotifier {
    public Notifier getNotifier(String notifierType) {
        if(notifierType.equals(TypeNotifier.WHATSAPP.getNotifier())) {
            return new NotifierWhatsApp();
        }
        return null;
    }
}
