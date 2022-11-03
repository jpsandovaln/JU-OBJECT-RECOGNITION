package org.jalau.at18.searchobject.notify;
/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and property information of Jala University
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jala University
 */
/**
 * Enum class of type of notification
 * @author Maria Hurtado
 * @version 1.0
 */
public enum TypeNotifier {
    WHATSAPP("whatsapp"),
    EMAIL("email"),
    MESSAGE("message");
    private String notifier;
    /**
     * Constructor of type of notification
     * @param model type of notification
     */
    TypeNotifier(String model) {
        this.notifier = model;
    }
    /**
     * Method of get type of notification
     */
    public String getNotifier() {
        return notifier;
    }
}
