package com.example.iaa.surveywizard;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class questionsOfCategory extends AppCompatActivity {
    public static ArrayList<questionsWithAnswers> questionsList = new ArrayList<>();

    public static Context context;
    public static String category;

    TextView categoryText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_of_category);

        context = this;
        DatabaseAdapter Dbadapter = new DatabaseAdapter(this);
        questionsList = Dbadapter.getAllQuestionsbycategory(category);

        categoryText = (TextView) findViewById(R.id.categoryBody);

        questionAdapter adapter = new questionAdapter(this, questionsList);

        ListView listView = (ListView) findViewById(R.id.questionofcategorylist);

        listView.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        //your code when back button pressed
        Intent joinsessionintent = new Intent(this, MyQuestions.class);
        startActivity(joinsessionintent);

    }
}
