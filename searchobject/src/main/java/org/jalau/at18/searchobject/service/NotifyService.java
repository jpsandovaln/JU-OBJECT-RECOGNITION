package org.jalau.at18.searchobject.service;

import org.jalau.at18.searchobject.model.MatchInfo;
import org.jalau.at18.searchobject.notify.Notifier;
import org.jalau.at18.searchobject.notify.VerifyNotifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotifyService {
    public void notifyService(List<MatchInfo> matches, String notifierType, String recipient) {
        VerifyNotifier verifyNotifier = new VerifyNotifier();
        Notifier notifier = verifyNotifier.getNotifier(notifierType);
        notifier.notify(matches, recipient);
    }
}
