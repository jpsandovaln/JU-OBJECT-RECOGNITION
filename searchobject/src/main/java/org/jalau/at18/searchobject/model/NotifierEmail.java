package org.jalau.at18.searchobject.model;

import java.util.List;

public class NotifierEmail extends Notifier {

    @Override
    public void notify(List<MatchInfo> matches) {
        // springboot send email research
        System.out.println("Send email");
        //twillio
    }
}
