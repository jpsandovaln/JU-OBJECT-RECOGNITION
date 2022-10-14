package org.jalau.at18.searchobject.notify;

public enum TypeNotifier {
    WHATSAPP("whatsapp"),
    EMAIL("email"),
    MESSAGE("message");
    private String notifier;

    TypeNotifier(String model) {
        this.notifier = model;
    }

    public String getNotifier() {
        return notifier;
    }
}
