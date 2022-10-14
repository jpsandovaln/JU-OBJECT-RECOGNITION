package org.jalau.at18.searchobject.notify;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.jalau.at18.searchobject.model.MatchInfo;

import java.util.List;

public class NotifierWhatsApp implements Notifier {
    @Override
    public void notify(List<MatchInfo> matches, String recipient) {
        final String ACCOUNT_SID = "AC2d0e1623bc4152f97539e97ae3ba8ee2";
        final String AUTH_TOKEN = "12d0cbaf1d67aa1809f8aeab6acf3ce6";

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
