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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class JoinSession extends AppCompatActivity {

    EditText code;

    FirebaseDatabase database;
    DatabaseReference myRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_session);

        code = (EditText) findViewById(R.id.code);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        submitAnswer.sessionCode = "";

    }

    @Override
    public void onBackPressed() {
        //your code when back button pressed
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);

    }

    public void joinSession(View view){

        if (haveNetworkConnection() == true) {
            try {
                if (!code.getText().toString().equals("")) {
                    final long codenumber = Long.parseLong(code.getText().toString());

                    myRef.addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot value : dataSnapshot.getChildren()) {

                                if (value.getKey().equals(Long.toString(codenumber))) {
                                    Intent joinsessionintent = new Intent(JoinSession.this, submitAnswer.class);
                                    submitAnswer.sessionCode = value.getKey();
                                    // Start the new activity
                                    startActivity(joinsessionintent);
                                }
                            }
                            if (!(submitAnswer.sessionCode.equals(Long.toString(codenumber)))) {
                                Toast.makeText(JoinSession.this, "enter a correct code", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
            } catch (Exception e) {
                Toast.makeText(JoinSession.this, e.toString() + " can't read", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(JoinSession.this, "You don't have Internet connection", Toast.LENGTH_LONG).show();
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
