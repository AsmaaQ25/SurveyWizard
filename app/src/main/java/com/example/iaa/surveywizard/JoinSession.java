package com.example.iaa.surveywizard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class JoinSession extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_session);
    }

    public void joinSession(View view){
        // Create a new intent to open the {@link submitAnswer}
        Intent joinsessionintent = new Intent(JoinSession.this, submitAnswer.class);

        // Start the new activity
        startActivity(joinsessionintent);
    }
}
