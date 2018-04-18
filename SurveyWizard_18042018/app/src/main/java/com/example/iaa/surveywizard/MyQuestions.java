package com.example.iaa.surveywizard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MyQuestions extends AppCompatActivity {
     public static ArrayList<questions> questionsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_questions);


        questionsList.add(new questions("How are u today?"));
        questionsList.add(new questions("What is this number 1?"));
        questionsList.add(new questions("What is this number 2?"));
        questionsList.add(new questions("What is this number 3?"));
        questionsList.add(new questions("What is this number 4?"));
        questionsList.add(new questions("What is this number 5?"));
        questionsList.add(new questions("What is this number 6?"));
        questionsList.add(new questions("What is this number 7?"));
        questionsList.add(new questions("What is this number 8?"));
        questionsList.add(new questions("What is this number 9?"));
        questionsList.add(new questions("What is this number 10?"));
        questionsList.add(new questions("What is this number 11?"));
        questionsList.add(new questions("What is this number 12?"));
        questionsList.add(new questions("What is this number 13?"));
        questionsList.add(new questions("What is this number 14?"));
        questionsList.add(new questions("What is this number 16?"));
        questionsList.add(new questions("What is this number 17?"));
        questionsList.add(new questions("What is this number 18?"));
        questionsList.add(new questions("What is this number 19?"));
        questionsList.add(new questions("What is this number 20?"));


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
