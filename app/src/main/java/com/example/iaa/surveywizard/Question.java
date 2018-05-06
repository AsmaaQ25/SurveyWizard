package com.example.iaa.surveywizard;

/**
 * Created by IAA on 2/20/2018.
 */
public class Question {

    private String mQuestionBody;
    private String[] mAnswers= new String[3];

    /**
     * Create a new Word object.
     *
     * @param question is the string resource Id for the question
     */
    public Question(){}
    public Question(String question)
    {   mQuestionBody = question;
        mAnswers = null;
    }

    public Question(String question, String[] answers)
    {
        mQuestionBody = question;
        mAnswers = answers;
    }

    public String getmQuestionBody() {
        return mQuestionBody;
    }
    public void setmQuestionBody(String questionBody) { this.mQuestionBody = questionBody;}

    public String[] getmAnswers() {return  mAnswers;}
    public void setmAnswers(String[] answers) {this.mAnswers = answers;}
}
