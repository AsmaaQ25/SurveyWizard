package com.example.iaa.surveywizard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.concurrent.TimeUnit;

public class TyNote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ty_note);



    }
    @Override
    public void onBackPressed() {
        //your code when back button pressed
        Intent intent = new Intent(TyNote.this, HomePage.class);
        startActivity(intent);
    }
}
