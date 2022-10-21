package org.jalau.at18.searchobject.model.emotionrecognizer;

import com.google.gson.annotations.SerializedName;

public class ConvertJson {
    @SerializedName("joyLikelihood")
    String joyLikelihood;
    @SerializedName("sorrowLikelihood")
    String sorrowLikelihood;
    @SerializedName("angerLikelihood")
    String angerLikelihood;
    @SerializedName("surpriseLikelihood")
    String surpriseLikelihood;

    public ConvertJson(String joyLikelihood, String sorrowLikelihood, String angerLikelihood,
            String surpriseLikelihood) {
        this.joyLikelihood = joyLikelihood;
        this.sorrowLikelihood = sorrowLikelihood;
        this.angerLikelihood = angerLikelihood;
        this.surpriseLikelihood = surpriseLikelihood;
    }

    public void showStrings() {
        System.out.println(joyLikelihood);
        System.out.println(sorrowLikelihood);
        System.out.println(angerLikelihood);
        System.out.println(surpriseLikelihood);
    }

}
