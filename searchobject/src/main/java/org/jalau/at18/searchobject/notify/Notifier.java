package org.jalau.at18.searchobject.notify;

import org.jalau.at18.searchobject.model.MatchInfo;

import java.util.List;

public interface Notifier {
    void notify(List<MatchInfo> matches, String recipient);
}
