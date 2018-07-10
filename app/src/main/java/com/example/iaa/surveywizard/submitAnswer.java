package com.example.iaa.surveywizard;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
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

import java.util.ArrayList;

public class submitAnswer extends AppCompatActivity {


    public static String sessionCode;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef1 = database.getReference();


    DatabaseReference Ref1 = database.getReference(sessionCode).child("answer_1_count");
    DatabaseReference Ref2 = database.getReference(sessionCode).child("answer_2_count");
    DatabaseReference Ref3 = database.getReference(sessionCode).child("answer_3_count");
    DatabaseReference Ref4 = database.getReference(sessionCode).child("answer_4_count");
    DatabaseReference Ref5 = database.getReference(sessionCode).child("answer_5_count");

    DatabaseReference examRef1 = database.getReference(sessionCode).child("examquestions").child("0");
    DatabaseReference examRef2 = database.getReference(sessionCode).child("examquestions").child("1");
    DatabaseReference examRef3 = database.getReference(sessionCode).child("examquestions").child("2");
    DatabaseReference examRef4 = database.getReference(sessionCode).child("examquestions").child("3");
    DatabaseReference examRef5 = database.getReference(sessionCode).child("examquestions").child("4");
    DatabaseReference examRef6 = database.getReference(sessionCode).child("examquestions").child("5");
    DatabaseReference examRef7 = database.getReference(sessionCode).child("examquestions").child("6");
    DatabaseReference examRef8 = database.getReference(sessionCode).child("examquestions").child("7");
    DatabaseReference examRef9 = database.getReference(sessionCode).child("examquestions").child("8");
    DatabaseReference examRef10 = database.getReference(sessionCode).child("examquestions").child("9");



    TextView question_body;
    public static TextView unusedtext;
    TextView SessionName;
    RadioButton first_option;
    RadioButton second_option;
    RadioButton third_option;
    RadioButton forth_option;
    RadioButton fifth_option;
    RadioGroup optionsRadioGroup;
    LinearLayout questionLayout;
    ListView listView;

    static int flag =0;
    static int surveyFlag=0;
    static int examFlag=0;

    static String question;
    static String answer1;
    static String answer2;
    static String answer3;
    static String answer4;
    static String answer5;

    public static int answerquestion1=0;
    public static int answerquestion2=0;
    public static int answerquestion3=0;
    public static int answerquestion4=0;
    public static int answerquestion5=0;
    public static int answerquestion6=0;
    public static int answerquestion7=0;
    public static int answerquestion8=0;
    public static int answerquestion9=0;
    public static int answerquestion10=0;

    public static int Answerquestion1=0;
    public static int Answerquestion2=0;
    public static int Answerquestion3=0;
    public static int Answerquestion4=0;
    public static int Answerquestion5=0;
    public static int Answerquestion6=0;
    public static int Answerquestion7=0;
    public static int Answerquestion8=0;
    public static int Answerquestion9=0;
    public static int Answerquestion10=0;


    int numberofQuestions=0;


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
        questionLayout = (LinearLayout) findViewById(R.id.questionLinearLayout);
        listView = (ListView) findViewById(R.id.answerExam_list);

        if (haveNetworkConnection() == true) {
            try {

                myRef1.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        //set the question view and show the question
                        try {
                            if (sessionCode.equals(dataSnapshot.getKey())) {

                                try {
                                    SessionData value = dataSnapshot.getValue(SessionData.class);
                                    questionLayout.setVisibility(View.VISIBLE);
                                    listView.setVisibility(View.GONE);
                                    if (!value.getQuestionBody().equals(question)) {
                                        surveyFlag = 1;
                                        examFlag = 0;
                                        flag = 0;
                                    } else if (!value.getAnswer_1().equals(answer1)) {
                                        surveyFlag = 1;
                                        examFlag = 0;
                                        flag = 0;
                                    } else if (!value.getAnswer_2().equals(answer2)) {
                                        surveyFlag = 1;
                                        examFlag = 0;
                                        flag = 0;
                                    } else if (!value.getAnswer_3().equals(answer3)) {
                                        if (!value.getAnswer_3().equals("")) {
                                            surveyFlag = 1;
                                            examFlag = 0;
                                            flag = 0;
                                        }
                                    } else if (!value.getAnswer_4().equals(answer4)) {
                                        if (!value.getAnswer_4().equals("")) {
                                            surveyFlag = 1;
                                            examFlag = 0;
                                            flag = 0;
                                        }
                                    } else if (!value.getAnswer_5().equals(answer5)) {
                                        if (!value.getAnswer_5().equals("")) {
                                            surveyFlag = 1;
                                            examFlag = 0;
                                            flag = 0;
                                        }
                                    }

                                    SessionName.setText(value.getSessionName());

                                    question_body.setText(value.getQuestionBody());
                                    question = question_body.getText().toString();

                                    first_option.setText(value.getAnswer_1());
                                    answer1 = first_option.getText().toString();

                                    second_option.setText(value.getAnswer_2());
                                    answer2 = second_option.getText().toString();

                                    if (value.getAnswer_3().equals("")) {
                                        third_option.setVisibility(View.GONE);
                                    } else {
                                        third_option.setVisibility(View.VISIBLE);
                                        third_option.setText(value.getAnswer_3());
                                        answer3 = third_option.getText().toString();
                                    }

                                    if (value.getAnswer_4().equals("")) {
                                        forth_option.setVisibility(View.GONE);
                                    } else {
                                        forth_option.setVisibility(View.VISIBLE);
                                        forth_option.setText(value.getAnswer_4());
                                        answer4 = forth_option.getText().toString();
                                    }

                                    if (value.getAnswer_5().equals("")) {
                                        fifth_option.setVisibility(View.GONE);
                                    } else {
                                        fifth_option.setVisibility(View.VISIBLE);
                                        fifth_option.setText(value.getAnswer_5());
                                        answer5 = fifth_option.getText().toString();
                                    }

                                } catch (Exception e) {
                                    try {
                                        SessionExamData Value = dataSnapshot.getValue(SessionExamData.class);

                                        questionLayout.setVisibility(View.GONE);
                                        listView.setVisibility(View.VISIBLE);
                                        SessionName.setText(Value.getSessionName());
                                        ArrayList<examquestion> examquestions = Value.getExamquestions();

                                        SubmitAnswerAdapter adapter = new SubmitAnswerAdapter(submitAnswer.this, examquestions);

                                        listView.setAdapter(adapter);
                                        surveyFlag = 0;
                                        examFlag = 1;
                                        numberofQuestions = examquestions.size();


                                    } catch (Exception E) {

                                    }

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


            } catch (Exception e) {
                Toast.makeText(submitAnswer.this, e.toString() + " can't read", Toast.LENGTH_LONG).show();
            }
            optionsRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    int choosedOption = optionsRadioGroup.getCheckedRadioButtonId();


                    switch (choosedOption) {
                        case R.id.first_option:
                            try {

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
                            } catch (Exception e) {
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
                            } catch (Exception e) {
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
                            } catch (Exception e) {
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
                            } catch (Exception e) {
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
                            } catch (Exception e) {
                                Toast.makeText(submitAnswer.this, e.toString() + "Failed to read value", Toast.LENGTH_LONG).show();
                            }

                            break;
                        default:
                            break;

                    }

                }
            });
        }else {
            Toast.makeText(submitAnswer.this, "You don't have Internet connection", Toast.LENGTH_LONG).show();
        }

    }

    public void sumbetanswer(View view) {
          if (haveNetworkConnection() == true) {
              if (flag == 0) {
                  if (surveyFlag == 1) {
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
                  } else if (examFlag == 1) {
                      int size = 0;
                      if (answerquestion1 != 0) {
                          size++;
                      }

                      if (answerquestion2 != 0) {
                          size++;
                      }

                      if (answerquestion3 != 0) {
                          size++;
                      }

                      if (answerquestion4 != 0) {
                          size++;
                      }

                      if (answerquestion5 != 0) {
                          size++;
                      }

                      if (answerquestion6 != 0) {
                          size++;
                      }

                      if (answerquestion7 != 0) {
                          size++;
                      }

                      if (answerquestion8 != 0) {
                          size++;
                      }

                      if (answerquestion9 != 0) {
                          size++;
                      }

                      if (answerquestion10 != 0) {
                          size++;
                      }

                      if (size == numberofQuestions) {

                          if (examRef1 != null && (answerquestion1 != 0)) {
                              switch (Answerquestion1) {
                                  case 1:
                                      examRef1.child("answer_1_count").setValue(answerquestion1);
                                      break;
                                  case 2:
                                      examRef1.child("answer_2_count").setValue(answerquestion1);
                                      break;
                                  case 3:
                                      examRef1.child("answer_3_count").setValue(answerquestion1);
                                      break;
                                  case 4:
                                      examRef1.child("answer_4_count").setValue(answerquestion1);
                                      break;
                                  case 5:
                                      examRef1.child("answer_5_count").setValue(answerquestion1);
                                      break;
                                  default:
                                      Toast.makeText(this, "question 1 not answered", Toast.LENGTH_LONG).show();
                                      break;
                              }
                          }

                          if (examRef2 != null && (answerquestion2 != 0)) {
                              switch (Answerquestion2) {
                                  case 1:
                                      examRef2.child("answer_1_count").setValue(answerquestion2);
                                      break;
                                  case 2:
                                      examRef2.child("answer_2_count").setValue(answerquestion2);
                                      break;
                                  case 3:
                                      examRef2.child("answer_3_count").setValue(answerquestion2);
                                      break;
                                  case 4:
                                      examRef2.child("answer_4_count").setValue(answerquestion2);
                                      break;
                                  case 5:
                                      examRef2.child("answer_5_count").setValue(answerquestion2);
                                      break;
                                  default:
                                      Toast.makeText(this, "question 2 not answered", Toast.LENGTH_LONG).show();
                                      break;
                              }
                          }

                          if (examRef3 != null && (answerquestion3 != 0)) {
                              switch (Answerquestion3) {
                                  case 1:
                                      examRef3.child("answer_1_count").setValue(answerquestion3);
                                      break;
                                  case 2:
                                      examRef3.child("answer_2_count").setValue(answerquestion3);
                                      break;
                                  case 3:
                                      examRef3.child("answer_3_count").setValue(answerquestion3);
                                      break;
                                  case 4:
                                      examRef3.child("answer_4_count").setValue(answerquestion3);
                                      break;
                                  case 5:
                                      examRef3.child("answer_5_count").setValue(answerquestion3);
                                      break;
                                  default:
                                      Toast.makeText(this, "question 3 not answered", Toast.LENGTH_LONG).show();
                                      break;
                              }
                          }

                          if (examRef4 != null && (answerquestion4 != 0)) {
                              switch (Answerquestion4) {
                                  case 1:
                                      examRef4.child("answer_1_count").setValue(answerquestion4);
                                      break;
                                  case 2:
                                      examRef4.child("answer_2_count").setValue(answerquestion4);
                                      break;
                                  case 3:
                                      examRef4.child("answer_3_count").setValue(answerquestion4);
                                      break;
                                  case 4:
                                      examRef4.child("answer_4_count").setValue(answerquestion4);
                                      break;
                                  case 5:
                                      examRef4.child("answer_5_count").setValue(answerquestion4);
                                      break;
                                  default:
                                      Toast.makeText(this, "question 4 not answered", Toast.LENGTH_LONG).show();
                                      break;
                              }
                          }

                          if (examRef5 != null && (answerquestion5 != 0)) {
                              switch (Answerquestion5) {
                                  case 1:
                                      examRef5.child("answer_1_count").setValue(answerquestion5);
                                      break;
                                  case 2:
                                      examRef5.child("answer_2_count").setValue(answerquestion5);
                                      break;
                                  case 3:
                                      examRef5.child("answer_3_count").setValue(answerquestion5);
                                      break;
                                  case 4:
                                      examRef5.child("answer_4_count").setValue(answerquestion5);
                                      break;
                                  case 5:
                                      examRef5.child("answer_5_count").setValue(answerquestion5);
                                      break;
                                  default:
                                      Toast.makeText(this, "question 5 not answered", Toast.LENGTH_LONG).show();
                                      break;
                              }
                          }

                          if (examRef6 != null && (answerquestion6 != 0)) {
                              switch (Answerquestion6) {
                                  case 1:
                                      examRef6.child("answer_1_count").setValue(answerquestion6);
                                      break;
                                  case 2:
                                      examRef6.child("answer_2_count").setValue(answerquestion6);
                                      break;
                                  case 3:
                                      examRef6.child("answer_3_count").setValue(answerquestion6);
                                      break;
                                  case 4:
                                      examRef6.child("answer_4_count").setValue(answerquestion6);
                                      break;
                                  case 5:
                                      examRef6.child("answer_5_count").setValue(answerquestion6);
                                      break;
                                  default:
                                      Toast.makeText(this, "question 6 not answered", Toast.LENGTH_LONG).show();
                                      break;
                              }
                          }

                          if (examRef7 != null && (answerquestion7 != 0)) {
                              switch (Answerquestion7) {
                                  case 1:
                                      examRef7.child("answer_1_count").setValue(answerquestion7);
                                      break;
                                  case 2:
                                      examRef7.child("answer_2_count").setValue(answerquestion7);
                                      break;
                                  case 3:
                                      examRef7.child("answer_3_count").setValue(answerquestion7);
                                      break;
                                  case 4:
                                      examRef7.child("answer_4_count").setValue(answerquestion7);
                                      break;
                                  case 5:
                                      examRef7.child("answer_5_count").setValue(answerquestion7);
                                      break;
                                  default:
                                      Toast.makeText(this, "question 7 not answered", Toast.LENGTH_LONG).show();
                                      break;
                              }
                          }

                          if (examRef8 != null && (answerquestion8 != 0)) {
                              switch (Answerquestion8) {
                                  case 1:
                                      examRef8.child("answer_1_count").setValue(answerquestion8);
                                      break;
                                  case 2:
                                      examRef8.child("answer_2_count").setValue(answerquestion8);
                                      break;
                                  case 3:
                                      examRef8.child("answer_3_count").setValue(answerquestion8);
                                      break;
                                  case 4:
                                      examRef8.child("answer_4_count").setValue(answerquestion8);
                                      break;
                                  case 5:
                                      examRef8.child("answer_5_count").setValue(answerquestion8);
                                      break;
                                  default:
                                      Toast.makeText(this, "question 8 not answered", Toast.LENGTH_LONG).show();
                                      break;
                              }
                          }

                          if (examRef9 != null && (answerquestion9 != 0)) {
                              switch (Answerquestion9) {
                                  case 1:
                                      examRef9.child("answer_1_count").setValue(answerquestion9);
                                      break;
                                  case 2:
                                      examRef9.child("answer_2_count").setValue(answerquestion9);
                                      break;
                                  case 3:
                                      examRef9.child("answer_3_count").setValue(answerquestion9);
                                      break;
                                  case 4:
                                      examRef9.child("answer_4_count").setValue(answerquestion9);
                                      break;
                                  case 5:
                                      examRef9.child("answer_5_count").setValue(answerquestion9);
                                      break;
                                  default:
                                      Toast.makeText(this, "question 9 not answered", Toast.LENGTH_LONG).show();
                                      break;
                              }
                          }

                          if (examRef10 != null && (answerquestion10 != 0)) {
                              switch (Answerquestion10) {
                                  case 1:
                                      examRef10.child("answer_1_count").setValue(answerquestion10);
                                      break;
                                  case 2:
                                      examRef10.child("answer_2_count").setValue(answerquestion10);
                                      break;
                                  case 3:
                                      examRef10.child("answer_3_count").setValue(answerquestion10);
                                      break;
                                  case 4:
                                      examRef10.child("answer_4_count").setValue(answerquestion10);
                                      break;
                                  case 5:
                                      examRef10.child("answer_5_count").setValue(answerquestion10);
                                      break;
                                  default:
                                      Toast.makeText(this, "question 10 not answered", Toast.LENGTH_LONG).show();
                                      break;
                              }
                          }
                          Toast.makeText(this, "Exam answers added successfully", Toast.LENGTH_LONG).show();


                          flag = 1;
                      } else {
                          flag = 0;
                          Toast.makeText(this, "answer all questions", Toast.LENGTH_LONG).show();
                      }
                  }
              }
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
