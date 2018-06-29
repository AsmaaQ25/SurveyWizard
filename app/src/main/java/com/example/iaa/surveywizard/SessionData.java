package com.example.iaa.surveywizard;

/**
 * Created by IAA on 6/28/2018.
 */
public class SessionData {


    private String questionBody;
    private String answer_1;
    private String answer_2;
    private String answer_3;
    private String answer_4;
    private String answer_5;
    private int answer_1_count;
    private int answer_2_count;
    private int answer_3_count;
    private int answer_4_count;
    private int answer_5_count;
    private String sessionName;


    private  SessionData(){}

    public SessionData(String SessionName, String QuestionBody, String Answer_1, String Answer_2,
                       String Answer_3, String Answer_4, String Answer_5,
                       int Answer1count, int Answer2count, int Answer3count,
                       int Answer4count, int Answer5count) {
        sessionName = SessionName;
        questionBody = QuestionBody;
        answer_1 = Answer_1;
        answer_2 = Answer_2;
        answer_3 = Answer_3;
        answer_4 = Answer_4;
        answer_5 = Answer_5;
        answer_1_count = Answer1count;
        answer_2_count = Answer2count;
        answer_3_count = Answer3count;
        answer_4_count = Answer4count;
        answer_5_count = Answer5count;
    }


    public String getQuestionBody() {
            return questionBody;
        }
    public String getSessionName() {
            return sessionName;
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
    public String getAnswer_5() {
        return answer_5;
    }
    public int getAnswer_1_count() {
        return answer_1_count;
    }
    public int getAnswer_2_count() {
        return answer_2_count;
    }
    public int getAnswer_3_count() {
        return answer_3_count;
    }
    public int getAnswer_4_count() {
        return answer_4_count;
    }
    public int getAnswer_5_count() {
        return answer_5_count;
    }


}
