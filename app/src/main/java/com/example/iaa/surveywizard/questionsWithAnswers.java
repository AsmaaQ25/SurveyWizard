package com.example.iaa.surveywizard;

/**
 * Created by IAA on 5/9/2018.
 */
public class questionsWithAnswers {
    private  String questionBody;
    private  String answer_1;
    private  String answer_2;
    private  String answer_3;
    private  String answer_4;
    private  String answer_5;


    private int id;


    private  questionsWithAnswers(){}

    public questionsWithAnswers(int id, String questionBody, String answer_1, String answer_2,
                                String answer_3, String answer_4, String answer_5) {
        this.questionBody = questionBody;
        this.id=id;
        this.answer_1 = answer_1;
        this.answer_2 = answer_2;
        this.answer_3 = answer_3;
        this.answer_4 = answer_4;
        this.answer_5 = answer_5;
    }
    public questionsWithAnswers(String questionBody)
    {
        this.questionBody = questionBody;
    }
    public String getQuestionBody() {
        return questionBody;
    }
    public int getId() {
        return id;
    }
    public String getAnswer_1() {
        return answer_1;
    }
    public String getAnswer_2() {
        return answer_2;
    }
    public String getAnswer_3() {
        return answer_3;
    }
    public String getAnswer_4() {
        return answer_4;
    }
    public String getAnswer_5() { return answer_5; }

    public void setQuestionBody(String  questionBody)
    {this.questionBody = questionBody;}
    public  void setAnswer_1(String answer_1)
    {this.answer_1 = answer_1;}
    public  void setAnswer_2(String answer_2)
    {this.answer_1 = answer_2;}
    public  void setAnswer_3(String answer_3)
    {this.answer_1 = answer_3;}
    public  void setAnswer_4(String answer_4)
    {this.answer_1 = answer_4;}
    public  void setAnswer_5(String answer_5)
    {this.answer_1 = answer_5;}

}
