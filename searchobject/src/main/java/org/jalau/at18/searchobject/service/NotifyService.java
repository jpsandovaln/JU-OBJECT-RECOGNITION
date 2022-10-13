package org.jalau.at18.searchobject.service;

import org.jalau.at18.searchobject.model.MatchInfo;
import org.jalau.at18.searchobject.model.Notifier;
import org.jalau.at18.searchobject.model.NotifierEmail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotifyService {
    public void notify(List<MatchInfo> matches) {
        Notifier notifier = new NotifierEmail();
        notifier.notify(matches);

    }
}
