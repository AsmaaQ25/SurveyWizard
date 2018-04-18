package com.example.iaa.surveywizard;

/**
 * Created by IAA on 2/20/2018.
 */
public class questions {

    private  String mquestion;

    /**
     * Create a new Word object.
     *
     * @param question is the string resource Id for the question
     */
    public questions(String question) {
        mquestion = question;
    }

    public String getMquestion() {
        return mquestion;
    }

}
