package com.example.iaa.surveywizard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

    }

    public void create_session_activity(View view){
        // Create a new intent to open the {@link CreateSession}
        Intent createSessionIntent = new Intent(HomePage.this, CreateSession.class);

        // Start the new activity
        startActivity(createSessionIntent);
    }

    public void join_session_activity(View view){
        // Create a new intent to open the {@link JoinSession}
        Intent joinSessionIntent = new Intent(HomePage.this, JoinSession.class);

        // Start the new activity
        startActivity(joinSessionIntent);
    }

}
