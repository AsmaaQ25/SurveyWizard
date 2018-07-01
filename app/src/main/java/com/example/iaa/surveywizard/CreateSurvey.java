package com.example.iaa.surveywizard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

/**
 * Created by Anas on 25-Feb-18.
 */

public class CreateSurvey extends AppCompatActivity {

    TextView sessionCode;
    EditText sessionName;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_survey);

        sessionCode = (TextView) findViewById(R.id.SessionCode);
        sessionName = (EditText) findViewById(R.id.sessionName);


        Random r = new Random();
        int myRandomNumber = 0;
        myRandomNumber = r.nextInt(99999-10000+1)+10000;
        sessionCode.setText(Integer.toString(myRandomNumber));

        myRef = database.getReference(sessionCode.getText().toString());

    }

    @Override
    public void onBackPressed() {
        //your code when back button pressed
        Intent intent = new Intent(this, CreateSession.class);
        startActivity(intent);

    }

    public void startSession(View view){
        // Create a new intent to open the {@link MyQuestionsSecond}

        Intent startSessionIntent = new Intent(CreateSurvey.this, MyQuestionsSecond.class);

        MyQuestionsSecond.MysessionName = sessionName.getText().toString();
        MyQuestionsSecond.MysessionCode = sessionCode.getText().toString();

        myRef.setValue(MyQuestionsSecond.MysessionCode);

        // Start the new activity
        startActivity(startSessionIntent);
    }

}

