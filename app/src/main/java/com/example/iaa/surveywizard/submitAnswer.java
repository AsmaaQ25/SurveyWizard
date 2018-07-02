package com.example.iaa.surveywizard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Ref;

public class submitAnswer extends AppCompatActivity {


    public static String sessionCode;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference(sessionCode);
    DatabaseReference myRef1 = database.getReference();


    DatabaseReference Ref1 = database.getReference(sessionCode).child("answer_1_count");
    DatabaseReference Ref2 = database.getReference(sessionCode).child("answer_2_count");
    DatabaseReference Ref3 = database.getReference(sessionCode).child("answer_3_count");
    DatabaseReference Ref4 = database.getReference(sessionCode).child("answer_4_count");
    DatabaseReference Ref5 = database.getReference(sessionCode).child("answer_5_count");



    TextView question_body;
    TextView unusedtext;
    TextView SessionName;
    RadioButton first_option;
    RadioButton second_option;
    RadioButton third_option;
    RadioButton forth_option;
    RadioButton fifth_option;
    RadioGroup optionsRadioGroup;
    static int flag =0;

    static String question;
    static String answer1;
    static String answer2;
    static String answer3;
    static String answer4;
    static String answer5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_answer);

        question_body = (TextView) findViewById(R.id.question_body);
        unusedtext = (TextView) findViewById(R.id.unusedtext);
        SessionName = (TextView) findViewById(R.id.pageName);
        first_option = (RadioButton) findViewById(R.id.first_option);
        second_option = (RadioButton) findViewById(R.id.second_option);
        third_option = (RadioButton) findViewById(R.id.third_option);
        forth_option = (RadioButton) findViewById(R.id.forth_option);
        fifth_option = (RadioButton) findViewById(R.id.fifth_option);
        optionsRadioGroup = (RadioGroup) findViewById(R.id.optionsRadioGroup);


        try {
            myRef1.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    //set the question view and show the question
                    try {
                        if (sessionCode.equals(dataSnapshot.getKey())) {
                            SessionData value = dataSnapshot.getValue(SessionData.class);

                            if (!value.getQuestionBody().equals(question)) {
                                flag = 0;
                            } else if (!value.getAnswer_1().equals(answer1)) {
                                flag = 0;
                            } else if (!value.getAnswer_2().equals(answer2)) {
                                flag = 0;
                            } else if (!value.getAnswer_3().equals(answer3)) {
                                if (!value.getAnswer_3().equals("")) {
                                    flag = 0;
                                }
                            } else if (!value.getAnswer_4().equals(answer4)) {
                                if (!value.getAnswer_4().equals("")) {
                                    flag = 0;
                                }
                            } else if (!value.getAnswer_5().equals(answer5)) {
                                if (!value.getAnswer_5().equals("")) {
                                    flag = 0;
                                }
                            }

                            SessionName.setText(value.getSessionName());

                            question_body.setText(value.getQuestionBody());
                            question=question_body.getText().toString();

                            first_option.setText(value.getAnswer_1());
                            answer1=first_option.getText().toString();

                            second_option.setText(value.getAnswer_2());
                            answer2=second_option.getText().toString();

                            if (value.getAnswer_3().equals("")) {
                                third_option.setVisibility(View.GONE);
                            } else {
                                third_option.setVisibility(View.VISIBLE);
                                third_option.setText(value.getAnswer_3());
                                answer3=third_option.getText().toString();
                            }

                            if (value.getAnswer_4().equals("")) {
                                forth_option.setVisibility(View.GONE);
                            } else {
                                forth_option.setVisibility(View.VISIBLE);
                                forth_option.setText(value.getAnswer_4());
                                answer4=forth_option.getText().toString();
                            }

                            if (value.getAnswer_5().equals("")) {
                                fifth_option.setVisibility(View.GONE);
                            } else {
                                fifth_option.setVisibility(View.VISIBLE);
                                fifth_option.setText(value.getAnswer_5());
                                answer5=fifth_option.getText().toString();
                            }

                                                    }
                    } catch (Exception e) {
                        Toast.makeText(submitAnswer.this, e.toString() + " can't read", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    if (sessionCode.equals(dataSnapshot.getKey())) {
                        Intent joinsessionintent = new Intent(submitAnswer.this, TyNote.class);
                        startActivity(joinsessionintent);
                    }

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }catch (Exception e){
            Toast.makeText(submitAnswer.this, e.toString() + " can't read", Toast.LENGTH_LONG).show();
        }
        optionsRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int choosedOption = optionsRadioGroup.getCheckedRadioButtonId();

                switch (choosedOption){
                    case R.id.first_option:
                        try {
                            unusedtext.setText(myRef.getKey().toString());
                            Ref1.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.getValue(long.class) != null) {
                                        // This method is called once with the initial value and again
                                        // whenever data at this location is updated.
                                        long value = dataSnapshot.getValue(long.class);
                                        //set the question view and show the question
                                        unusedtext.setText(Long.toString(value));
                                    }
                                }
                                @Override
                                public void onCancelled(DatabaseError error) {
                                    // Failed to read value
                                    Toast.makeText(submitAnswer.this, "Failed to read value", Toast.LENGTH_LONG).show();
                                }
                            });
                        }catch (Exception e){
                            Toast.makeText(submitAnswer.this, e.toString() + "Failed to read value", Toast.LENGTH_LONG).show();
                        }

                        break;
                    case R.id.second_option:
                        try {
                            Ref2.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.getValue(long.class) != null) {
                                        // This method is called once with the initial value and again
                                        // whenever data at this location is updated.
                                        long value = dataSnapshot.getValue(long.class);
                                        //set the question view and show the question
                                        unusedtext.setText(Long.toString(value));
                                    }
                                }
                                @Override
                                public void onCancelled(DatabaseError error) {
                                    // Failed to read value
                                    Toast.makeText(submitAnswer.this, "Failed to read value", Toast.LENGTH_LONG).show();
                                }
                            });
                        }catch (Exception e){
                            Toast.makeText(submitAnswer.this, e.toString() + "Failed to read value", Toast.LENGTH_LONG).show();
                        }

                        break;
                    case R.id.third_option:
                        try {
                            Ref3.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.getValue(long.class) != null) {
                                        // This method is called once with the initial value and again
                                        // whenever data at this location is updated.
                                        long value = dataSnapshot.getValue(long.class);
                                        //set the question view and show the question
                                        unusedtext.setText(Long.toString(value));
                                    }
                                }
                                @Override
                                public void onCancelled(DatabaseError error) {
                                    // Failed to read value
                                    Toast.makeText(submitAnswer.this, "Failed to read value", Toast.LENGTH_LONG).show();
                                }
                            });
                        }catch (Exception e){
                            Toast.makeText(submitAnswer.this, e.toString() + "Failed to read value", Toast.LENGTH_LONG).show();
                        }

                        break;
                    case R.id.forth_option:
                        try {
                            Ref4.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.getValue(long.class) != null) {
                                        // This method is called once with the initial value and again
                                        // whenever data at this location is updated.
                                        long value = dataSnapshot.getValue(long.class);
                                        //set the question view and show the question
                                        unusedtext.setText(Long.toString(value));
                                    }
                                }
                                @Override
                                public void onCancelled(DatabaseError error) {
                                    // Failed to read value
                                    Toast.makeText(submitAnswer.this, "Failed to read value", Toast.LENGTH_LONG).show();
                                }
                            });
                        }catch (Exception e){
                            Toast.makeText(submitAnswer.this, e.toString() + "Failed to read value", Toast.LENGTH_LONG).show();
                        }

                        break;
                    case R.id.fifth_option:
                        try {
                            Ref5.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.getValue(long.class) != null) {
                                        // This method is called once with the initial value and again
                                        // whenever data at this location is updated.
                                        long value = dataSnapshot.getValue(long.class);
                                        //set the question view and show the question
                                        unusedtext.setText(Long.toString(value));
                                    }
                                }
                                @Override
                                public void onCancelled(DatabaseError error) {
                                    // Failed to read value
                                    Toast.makeText(submitAnswer.this, "Failed to read value", Toast.LENGTH_LONG).show();
                                }
                            });
                        }catch (Exception e){
                            Toast.makeText(submitAnswer.this, e.toString() + "Failed to read value", Toast.LENGTH_LONG).show();
                        }

                        break;
                    default:
                        break;

                }

            }
        });

    }

    /*public void optionChanged(View view) {
        int choosedOption = optionsRadioGroup.getCheckedRadioButtonId();

        switch (choosedOption){
            case R.id.first_option:
                try {
                    Ref1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // This method is called once with the initial value and again
                            // whenever data at this location is updated.
                            long value = dataSnapshot.getValue(long.class);
                            //set the question view and show the question
                            unusedtext.setText(Long.toString(value));
                        }
                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                            Toast.makeText(submitAnswer.this, "Failed to read value", Toast.LENGTH_LONG).show();
                        }
                    });
                }catch (Exception e){
                    Toast.makeText(submitAnswer.this, e.toString() + "Failed to read value", Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.second_option:
                try {
                    Ref2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // This method is called once with the initial value and again
                            // whenever data at this location is updated.
                            long value = dataSnapshot.getValue(long.class);
                            //set the question view and show the question
                            unusedtext.setText(Long.toString(value));
                        }
                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                            Toast.makeText(submitAnswer.this, "Failed to read value", Toast.LENGTH_LONG).show();
                        }
                    });
                }catch (Exception e){
                    Toast.makeText(submitAnswer.this, e.toString() + "Failed to read value", Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.third_option:
                try {
                    Ref3.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // This method is called once with the initial value and again
                            // whenever data at this location is updated.
                            long value = dataSnapshot.getValue(long.class);
                            //set the question view and show the question
                            unusedtext.setText(Long.toString(value));
                        }
                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                            Toast.makeText(submitAnswer.this, "Failed to read value", Toast.LENGTH_LONG).show();
                        }
                    });
                }catch (Exception e){
                    Toast.makeText(submitAnswer.this, e.toString() + "Failed to read value", Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.forth_option:
                try {
                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // This method is called once with the initial value and again
                            // whenever data at this location is updated.
                            SessionData value = dataSnapshot.getValue(SessionData.class);
                            //set the question view and show the question
                            unusedtext.setText(Long.toString(value.getAnswer_4_count()));
                        }
                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                            Toast.makeText(submitAnswer.this, "Failed to read value", Toast.LENGTH_LONG).show();
                        }
                    });
                }catch (Exception e){
                    Toast.makeText(submitAnswer.this, e.toString() + "Failed to read value", Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.fifth_option:
                try {
                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // This method is called once with the initial value and again
                            // whenever data at this location is updated.
                            SessionData value = dataSnapshot.getValue(SessionData.class);
                            //set the question view and show the question
                            unusedtext.setText(Long.toString(value.getAnswer_5_count()));
                        }
                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                            Toast.makeText(submitAnswer.this, "Failed to read value", Toast.LENGTH_LONG).show();
                        }
                    });
                }catch (Exception e){
                    Toast.makeText(submitAnswer.this, e.toString() + "Failed to read value", Toast.LENGTH_LONG).show();
                }

                break;
            default:
                break;

        }

    }

*/
    public void sumbetanswer(View view) {
        if (flag == 0) {
            int choosedOption = optionsRadioGroup.getCheckedRadioButtonId();

            switch (choosedOption) {
                case R.id.first_option:

                    try {
                        Ref1.setValue(Long.parseLong(unusedtext.getText().toString()) + 1);

                        Toast.makeText(submitAnswer.this, "answer1 sended successfully", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(submitAnswer.this, e.toString() + "Failed to set value", Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.second_option:
                    try {
                        Ref2.setValue(Long.parseLong(unusedtext.getText().toString()) + 1);

                        Toast.makeText(submitAnswer.this, "answer2 sended successfully", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(submitAnswer.this, e.toString() + "Failed to set value", Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.third_option:
                    try {
                        Ref3.setValue(Long.parseLong(unusedtext.getText().toString()) + 1);

                        Toast.makeText(submitAnswer.this, "answer3 sended successfully", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(submitAnswer.this, e.toString() + "Failed to set value", Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.forth_option:
                    try {
                        Ref4.setValue(Long.parseLong(unusedtext.getText().toString()) + 1);

                        Toast.makeText(submitAnswer.this, "answer4 sended successfully", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(submitAnswer.this, e.toString() + "Failed to set value", Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.fifth_option:
                    try {
                        Ref5.setValue(Long.parseLong(unusedtext.getText().toString()) + 1);

                        Toast.makeText(submitAnswer.this, "answer5 sended successfully", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(submitAnswer.this, e.toString() + "Failed to set value", Toast.LENGTH_LONG).show();
                    }
                    break;
                default:
                    break;
            }
            flag=1;
        }


    }
    @Override
    public void onBackPressed() {
        //your code when back button pressed

    }

    public void leaveSession(View view) {
        Intent joinsessionintent = new Intent(submitAnswer.this, TyNote.class);
        startActivity(joinsessionintent);
    }
}
