package org.jalau.at18.searchobject.model;

import java.util.List;

public abstract class Notifier {
    public abstract void notify(List<MatchInfo> matches);
}
