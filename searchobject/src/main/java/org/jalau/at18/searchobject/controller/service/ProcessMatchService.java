package org.jalau.at18.searchobject.controller.service;

import org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes.MatchInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessMatchService {
    @Autowired
    NotifyService notifyService;

    public void processMatches(List<MatchInfo> matches, String notifierType, String recipient) {
        notifyService.notifyService(matches, notifierType, recipient);
    }
}
