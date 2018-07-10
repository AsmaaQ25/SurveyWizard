package com.example.iaa.surveywizard;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

/**
 * Created by Anas on 25-Feb-18.
 */

public class
CreateSurvey extends AppCompatActivity {

    TextView sessionCode;
    EditText sessionName;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_survey);

        startService(new Intent(this, ClosingService.class));

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

        if(haveNetworkConnection() == true) {
            Intent startSessionIntent = new Intent(CreateSurvey.this, Survey.class);

            Survey.sessionName = sessionName.getText().toString();
            Survey.sessionCode = sessionCode.getText().toString();

            myRef.setValue(sessionCode.getText().toString());

            // Start the new activity
            startActivity(startSessionIntent);
        }else{
            Toast.makeText(CreateSurvey.this, "You don't have Internet connection", Toast.LENGTH_LONG).show();
        }
    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }


}

