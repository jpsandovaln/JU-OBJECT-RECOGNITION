package org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes.ssd;
/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and property information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */


/**
 * Represents the Detected objects found with the SSD model with their respective labels and scores
 *
 * @author Libertad Tolaba
 * @version 1.0
 */
public class DetectedObj {
    private String label;
    private float score;

    public DetectedObj(String label, float score) {
        this.label = label;
        this.score = score;
    }
    @Override
    public String toString() {
        return "{ label: " + label + ", score: " + score + " }";
    }

    public String getLabel() {
        return label;
    }

    public float getScore() {
        return score;
    }
}
