package org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes;
/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and property information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
/**
 * It is responsible for match the information with the object and the score.
 *
 * @author Maria Hurtado
 * @version 1.0
 */
public class MatchInfo {
    private String name;
    private Double score;
    /**
     * Constructor, initialize the variables
     * @param name object name
     * @param score score of the match
     * @author Maria Hurtado
     * @version 1.0
     */
    public MatchInfo(String name, Double score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
