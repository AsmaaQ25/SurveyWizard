package com.example.iaa.surveywizard;

/**
 * Created by IAA on 5/9/2018.
 */
public class questionsWithAnswers {
    private  String mquestion;
    private  String mAnswer_1;
    private  String mAnswer_2;
    private  String mAnswer_3;
    private  String mAnswer_4;
    private  String mAnswer_5;


    private int id;
    /**
     * Create a new Word object.
     *
     * @param question is the string resource Id for the question
     */
    public questionsWithAnswers(int Id, String question, String Answer_1, String Answer_2,
                                String Answer_3, String Answer_4, String Answer_5) {
        mquestion = question;
        id=Id;
        mAnswer_1 = Answer_1;
        mAnswer_2 = Answer_2;
        mAnswer_3 = Answer_3;
        mAnswer_4 = Answer_4;
        mAnswer_5 = Answer_5;
    }

    public String getMquestion() {
        return mquestion;
    }
    public int getQuestionId() {
        return id;
    }
    public String getmAnswer_1() {
        return mAnswer_1;
    }
    public String getmAnswer_2() {
        return mAnswer_2;
    }
    public String getmAnswer_3() {
        return mAnswer_3;
    }
    public String getmAnswer_4() {
        return mAnswer_4;
    }
    public String getmAnswer_5() {
        return mAnswer_5;
    }
}
