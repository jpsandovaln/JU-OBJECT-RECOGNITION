package org.jalau.at18.searchobject.notify;
/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and property information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.List;
import org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes.MatchInfo;
/**
 * Send notifications of the result according to the request to phone numbers.
 * @author Maria Hurtado
 * @version 1.0
 */
public class NotifierWhatsApp implements Notifier {
    @Override
    public void notify(List<MatchInfo> matches, String recipient) {
        final String ACCOUNT_SID = "AC4b8788d8384f76211ab4ec6b971995eb";
        final String AUTH_TOKEN = "90f649205b5c5289186ad57ff2171283";
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        // the string of phone numbers is we separate with split to add them to the array
        String[] numbers = recipient.split(",");
        //traverse the array phone numbers to send a notification to each one
        for(int index = 0; index < numbers.length ; index++) {
            Message message = Message.creator(new PhoneNumber("whatsapp:" + numbers[index]),
                    new PhoneNumber("whatsapp:+14155238886"),
                    prepareMessage(matches)).create();
            System.out.println(message.getSid());
        }
    }

    private String prepareMessage(List<MatchInfo> matches) {
        StringBuilder message = new StringBuilder();
        message.append("This is the result according you request:\n");
        for (MatchInfo match : matches) {
            message.append(match.getName()).append(" ").append(match.getScore()).append("\n");
        }
        return message.toString();
    }
}
