package com.example.iaa.surveywizard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CreateSession extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_session);
    }

    public void My_Question_activity(View view){
        // Create a new intent to open the {@link MyQuestions}
        Intent myQuestionsIntent = new Intent(CreateSession.this, MyQuestions.class);

        // Start the new activity
        startActivity(myQuestionsIntent);
    }
    public void create_survay_activity(View view){
        // Create a new intent to open the {@link CreateSurvey}
        Intent creatSurvayIntent = new Intent(CreateSession.this, CreateSurvey.class);

        // Start the new activity
        startActivity(creatSurvayIntent);
    }
    @Override
    public void onBackPressed() {
        //your code when back button pressed
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);

    }
}
