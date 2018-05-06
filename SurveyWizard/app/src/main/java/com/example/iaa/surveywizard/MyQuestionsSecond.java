package com.example.iaa.surveywizard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Anas on 25-Feb-18.
 */

public class MyQuestionsSecond extends AppCompatActivity {
    public static ArrayList<Question> questionsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_questions_second);
        questionsList.add(new Question("How are u today?"));
        questionsList.add(new Question("What is this number 1?"));
        questionsList.add(new Question("What is this number 2?"));
        questionsList.add(new Question("What is this number 3?"));
        questionsList.add(new Question("What is this number 4?"));
        questionsList.add(new Question("What is this number 5?"));
        questionsList.add(new Question("What is this number 6?"));
        questionsList.add(new Question("What is this number 7?"));
        questionsList.add(new Question("What is this number 8?"));
        questionsList.add(new Question("What is this number 9?"));
        questionsList.add(new Question("What is this number 10?"));


        questionSecondAdapter adapter = new questionSecondAdapter(this, questionsList);

        ListView listView = (ListView) findViewById(R.id.listView2);

        listView.setAdapter(adapter);

    }

}
