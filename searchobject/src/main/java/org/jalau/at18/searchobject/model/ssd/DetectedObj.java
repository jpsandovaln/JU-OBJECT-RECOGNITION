package org.jalau.at18.searchobject.model.ssd;

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
