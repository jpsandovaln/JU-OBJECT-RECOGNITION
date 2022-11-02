package org.jalau.at18.searchobject.notify;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.util.List;

import org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes.MatchInfo;
// codigo   =>              join special-slip
// numero de whatsaap al cual enviar el codigo =>      14155238886

//https://api.whatsapp.com/send?phone=14155238886&text=join%20special-slip
public class NotifierWhatsApp implements Notifier {
    @Override
    public void notify(List<MatchInfo> matches, String recipient) {
        final String ACCOUNT_SID = "AC4b8788d8384f76211ab4ec6b971995eb";
        final String AUTH_TOKEN = "90f649205b5c5289186ad57ff2171283";

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
