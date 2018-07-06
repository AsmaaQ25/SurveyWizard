package com.example.iaa.surveywizard;

import android.content.Intent;
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
    }
}
