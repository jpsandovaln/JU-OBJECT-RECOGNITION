package org.jalau.at18.searchobject.notify;

import java.util.List;

import org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes.MatchInfo;

public interface Notifier {
    void notify(List<MatchInfo> matches, String recipient);
}
