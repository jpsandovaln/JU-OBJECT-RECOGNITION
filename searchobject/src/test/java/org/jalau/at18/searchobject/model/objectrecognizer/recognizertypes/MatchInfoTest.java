package org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MatchInfoTest {

    @Test
    public void shouldConstructEmptyMatchInfo() {
        MatchInfo matchInfo = new MatchInfo("", 0.0);
        String expectedName = "";
        Double expectedScore = 0.0;
        assertEquals(expectedName, matchInfo.getName());
        assertEquals(expectedScore, matchInfo.getScore());
    }

    @Test
    public void shouldSetNameAndScore() {
        MatchInfo matchInfo = new MatchInfo("", 0.0);
        String expectedName = "New name";
        Double expectedScore = 10.0;
        matchInfo.setName("New name");
        matchInfo.setScore(10.0);
        assertEquals(expectedName, matchInfo.getName());
        assertEquals(expectedScore, matchInfo.getScore());
    }

}