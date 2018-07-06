package com.example.iaa.surveywizard;

import java.util.ArrayList;

/**
 * Created by IAA on 7/5/2018.
 */
public class SessionExamData {

    private String sessionName;
    private ArrayList<examquestion> examquestions;

    public SessionExamData() {}


    public SessionExamData(String sessionName, ArrayList<examquestion> examquestions) {
        this.sessionName = sessionName;

        this.examquestions = examquestions;
    }



    public String getSessionName() {
        return sessionName;
    }

    public ArrayList<examquestion> getExamquestions() {
        return examquestions;
    }

}
