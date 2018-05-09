package com.example.iaa.surveywizard;

/**
 * Created by IAA on 2/20/2018.
 */
public class questions {

    private  String mquestion;

    //private int id;
    /**
     * Create a new Word object.
     *
     * @param question is the string resource Id for the question
     */
    public questions(/*int Id,*/ String question) {
        mquestion = question;
        //id=Id;
    }

    public String getMquestion() {
        return mquestion;
    }
    /*public int getQuestionId() {
        return id;
    }*/

}
