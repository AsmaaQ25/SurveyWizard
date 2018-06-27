package com.example.iaa.surveywizard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class submitAnswer extends AppCompatActivity {

    private static final String TAG = "Submit Your Answers";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("sessionCode");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_answer);
        final TextView question_body = (TextView) findViewById(R.id.question_body);
        final RadioButton first_option = (RadioButton) findViewById(R.id.first_option);
        final RadioButton second_option = (RadioButton) findViewById(R.id.second_option);
        final RadioButton third_option = (RadioButton) findViewById(R.id.third_option);
        final RadioButton forth_option = (RadioButton) findViewById(R.id.fourth_option);
        final RadioButton fifth_option = (RadioButton) findViewById(R.id.fifth_option);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                questionsWithAnswers value = dataSnapshot.getValue(questionsWithAnswers.class);
                Log.d(TAG, "Value is: " + value);
                //set the Question view and show the Question
                try {
                    question_body.setText(value.getQuestionBody());
                    first_option.setText(value.getAnswer_1());
                    second_option.setText(value.getAnswer_2());
                    third_option.setText(value.getAnswer_3());
                    forth_option.setText(value.getAnswer_4());
                    fifth_option.setText(value.getAnswer_5());

                }catch (Exception e){
                    Toast.makeText(submitAnswer.this, e.toString() + " can't read", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }



}
