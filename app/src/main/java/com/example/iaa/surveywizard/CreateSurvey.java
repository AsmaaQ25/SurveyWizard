package com.example.iaa.surveywizard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Anas on 25-Feb-18.
 */

public class CreateSurvey extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_survey);

    }
    public void startSession(View view){
        // Create a new intent to open the {@link MyQuestionsSecond}
        Intent startSessionIntent = new Intent(CreateSurvey.this, MyQuestionsSecond.class);

        // Start the new activity
        startActivity(startSessionIntent);
    }

}

