package org.jalau.at18.searchobject.notify;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.util.List;

import org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes.MatchInfo;

public class NotifierWhatsApp implements Notifier {
    @Override
    public void notify(List<MatchInfo> matches, String recipient) {
        final String ACCOUNT_SID = "AC59ac8b807b4d14fc0983682b5b784a04";
        final String AUTH_TOKEN = "2508a77a4584fe7119a03c936c9d1201";

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(new PhoneNumber("whatsapp:" + recipient),
                new PhoneNumber("whatsapp:+14155238886"),
                prepareMessage(matches)).create();

        System.out.println(message.getSid());
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
