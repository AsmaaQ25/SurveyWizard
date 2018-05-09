package com.example.iaa.surveywizard;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Anas on 25-Feb-18.
 */

public class MyQuestionsSecond extends AppCompatActivity {
    public static ArrayList<questions> questionsList = new ArrayList<>();

    public static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_questions_second);
        context = this;

        DatabaseAdapter Dbadapter = new DatabaseAdapter(this);
        questionsList = Dbadapter.getAllQuestionsWithoutAnswers();


        questionSecondAdapter adapter = new questionSecondAdapter(this, questionsList);

        ListView listView = (ListView) findViewById(R.id.listView2);

        listView.setAdapter(adapter);

    }

}
