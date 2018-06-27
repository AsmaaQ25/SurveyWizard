package com.example.iaa.surveywizard;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MyQuestions extends AppCompatActivity {
    public static ArrayList<questionsWithAnswers> questionsList = new ArrayList<>();
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_questions);

        context = this;
        DatabaseAdapter Dbadapter = new DatabaseAdapter(this);
        questionsList = Dbadapter.getAllQuestionsWithoutAnswers();

        questionAdapter adapter = new questionAdapter(this, questionsList);

        ListView listView = (ListView) findViewById(R.id.question_list);

        listView.setAdapter(adapter);
    }


    public void refresh(View view){
        //super.recreate();
    }

    public void add_new_question(View view){
        // Create a new intent to open the {@link addNewQuestion}
        Intent AddNewQuestionIntent = new Intent(MyQuestions.this, addNewQuestion.class);

        // Start the new activity
        startActivity(AddNewQuestionIntent);
    }


}
