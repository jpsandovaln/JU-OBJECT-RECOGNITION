/*
  Copyright (c) 2022 Jala University.
  This software is the confidential and property information of Jalasoft
  ("Confidential Information"). You shall not disclose such Confidential
  Information and shall use it only in accordance with the terms of the
  Licence agreement you entered into with Jalasoft
*/
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