package com.example.iaa.surveywizard;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by IAA on 7/5/2018.
 */
public class ExamResultsAdapter extends ArrayAdapter<String> {

    public ExamResultsAdapter(Context context, ArrayList<String> questionses) {
        super(context, 0, questionses);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.results_list, parent, false);
        }


        final String currentQuestion;
        currentQuestion = getItem(position);

        TextView textView = (TextView) listItemView.findViewById(R.id.textView14);

        textView.setText(currentQuestion);


        Button showResultButton = (Button) listItemView.findViewById(R.id.showresultButton);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        final DatabaseReference examRef1 = database.getReference(ExamResults.MysessionCode).child("examquestions").child("0");
        final DatabaseReference examRef2 = database.getReference(ExamResults.MysessionCode).child("examquestions").child("1");
        final DatabaseReference examRef3 = database.getReference(ExamResults.MysessionCode).child("examquestions").child("2");
        final DatabaseReference examRef4 = database.getReference(ExamResults.MysessionCode).child("examquestions").child("3");
        final DatabaseReference examRef5 = database.getReference(ExamResults.MysessionCode).child("examquestions").child("4");
        final DatabaseReference examRef6 = database.getReference(ExamResults.MysessionCode).child("examquestions").child("5");
        final DatabaseReference examRef7 = database.getReference(ExamResults.MysessionCode).child("examquestions").child("6");
        final DatabaseReference examRef8 = database.getReference(ExamResults.MysessionCode).child("examquestions").child("7");
        final DatabaseReference examRef9 = database.getReference(ExamResults.MysessionCode).child("examquestions").child("8");
        final DatabaseReference examRef10 = database.getReference(ExamResults.MysessionCode).child("examquestions").child("9");




        showResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (position) {
                    case 0:
                        examRef1.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.getKey().equals("examquestions")) {
                                    examquestion value = dataSnapshot.getValue(examquestion.class);
                                    DatabaseAdapter Dbadapter = new DatabaseAdapter(ExamResults.context);

                                    int id = Dbadapter.getIdOfQuestion(value.getQuestionBody());
                                    int updateId = Dbadapter.update(id, value.getQuestionBody(), value.getAnswer_1(), value.getAnswer_2(),
                                            value.getAnswer_3(), value.getAnswer_4(), value.getAnswer_5(), value.getAnswer_1_count(),
                                            value.getAnswer_2_count(), value.getAnswer_3_count(), value.getAnswer_4_count(),
                                            value.getAnswer_5_count());

                                    if (updateId == 1) {
                                        Intent intent = new Intent(ExamResults.context, Show_Result.class);
                                        ExamResults.context.startActivity(intent);
                                        Toast.makeText(ExamResults.context, "DataBase updated successfully", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(ExamResults.context, "DataBase didn't update successfully", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                        break;
                    case 1:
                        examRef2.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.getKey().equals("examquestions")) {
                                    examquestion value = dataSnapshot.getValue(examquestion.class);
                                    DatabaseAdapter Dbadapter = new DatabaseAdapter(ExamResults.context);
                                    if (currentQuestion.equals(value.getQuestionBody())) {

                                        int id = Dbadapter.getIdOfQuestion(value.getQuestionBody());
                                        int updateId = Dbadapter.update(id, value.getQuestionBody(), value.getAnswer_1(), value.getAnswer_2(),
                                                value.getAnswer_3(), value.getAnswer_4(), value.getAnswer_5(), value.getAnswer_1_count(),
                                                value.getAnswer_2_count(), value.getAnswer_3_count(), value.getAnswer_4_count(),
                                                value.getAnswer_5_count());

                                        if (updateId == 1) {
                                            Intent intent = new Intent(ExamResults.context, Show_Result.class);
                                            ExamResults.context.startActivity(intent);
                                            Toast.makeText(ExamResults.context, "DataBase updated successfully", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(ExamResults.context, "DataBase didn't update successfully", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                        break;
                    case 2:
                        examRef3.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.getKey().equals("examquestions")) {
                                    examquestion value = dataSnapshot.getValue(examquestion.class);
                                    DatabaseAdapter Dbadapter = new DatabaseAdapter(ExamResults.context);
                                    if (currentQuestion.equals(value.getQuestionBody())) {

                                        int id = Dbadapter.getIdOfQuestion(value.getQuestionBody());
                                        int updateId = Dbadapter.update(id, value.getQuestionBody(), value.getAnswer_1(), value.getAnswer_2(),
                                                value.getAnswer_3(), value.getAnswer_4(), value.getAnswer_5(), value.getAnswer_1_count(),
                                                value.getAnswer_2_count(), value.getAnswer_3_count(), value.getAnswer_4_count(),
                                                value.getAnswer_5_count());

                                        if (updateId == 1) {
                                            Intent intent = new Intent(ExamResults.context, Show_Result.class);
                                            ExamResults.context.startActivity(intent);
                                            Toast.makeText(ExamResults.context, "DataBase updated successfully", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(ExamResults.context, "DataBase didn't update successfully", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                        break;
                    case 3:
                        examRef4.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.getKey().equals("examquestions")) {
                                    examquestion value = dataSnapshot.getValue(examquestion.class);
                                    DatabaseAdapter Dbadapter = new DatabaseAdapter(ExamResults.context);

                                    if (currentQuestion.equals(value.getQuestionBody())) {
                                        int id = Dbadapter.getIdOfQuestion(value.getQuestionBody());
                                        int updateId = Dbadapter.update(id, value.getQuestionBody(), value.getAnswer_1(), value.getAnswer_2(),
                                                value.getAnswer_3(), value.getAnswer_4(), value.getAnswer_5(), value.getAnswer_1_count(),
                                                value.getAnswer_2_count(), value.getAnswer_3_count(), value.getAnswer_4_count(),
                                                value.getAnswer_5_count());

                                        if (updateId == 1) {
                                            Intent intent = new Intent(ExamResults.context, Show_Result.class);
                                            ExamResults.context.startActivity(intent);
                                            Toast.makeText(ExamResults.context, "DataBase updated successfully", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(ExamResults.context, "DataBase didn't update successfully", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                        break;
                    case 4:
                        examRef5.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.getKey().equals("examquestions")) {
                                    examquestion value = dataSnapshot.getValue(examquestion.class);
                                    DatabaseAdapter Dbadapter = new DatabaseAdapter(ExamResults.context);

                                    if (currentQuestion.equals(value.getQuestionBody())) {
                                        int id = Dbadapter.getIdOfQuestion(value.getQuestionBody());
                                        int updateId = Dbadapter.update(id, value.getQuestionBody(), value.getAnswer_1(), value.getAnswer_2(),
                                                value.getAnswer_3(), value.getAnswer_4(), value.getAnswer_5(), value.getAnswer_1_count(),
                                                value.getAnswer_2_count(), value.getAnswer_3_count(), value.getAnswer_4_count(),
                                                value.getAnswer_5_count());

                                        if (updateId == 1) {
                                            Intent intent = new Intent(ExamResults.context, Show_Result.class);
                                            ExamResults.context.startActivity(intent);
                                            Toast.makeText(ExamResults.context, "DataBase updated successfully", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(ExamResults.context, "DataBase didn't update successfully", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                        break;
                    case 5:
                        examRef6.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.getKey().equals("examquestions")) {
                                    examquestion value = dataSnapshot.getValue(examquestion.class);
                                    DatabaseAdapter Dbadapter = new DatabaseAdapter(ExamResults.context);

                                    if (currentQuestion.equals(value.getQuestionBody())) {
                                        int id = Dbadapter.getIdOfQuestion(value.getQuestionBody());
                                        int updateId = Dbadapter.update(id, value.getQuestionBody(), value.getAnswer_1(), value.getAnswer_2(),
                                                value.getAnswer_3(), value.getAnswer_4(), value.getAnswer_5(), value.getAnswer_1_count(),
                                                value.getAnswer_2_count(), value.getAnswer_3_count(), value.getAnswer_4_count(),
                                                value.getAnswer_5_count());

                                        if (updateId == 1) {
                                            Intent intent = new Intent(ExamResults.context, Show_Result.class);
                                            ExamResults.context.startActivity(intent);
                                            Toast.makeText(ExamResults.context, "DataBase updated successfully", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(ExamResults.context, "DataBase didn't update successfully", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                        break;
                    case 6:
                        examRef7.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.getKey().equals("examquestions")) {
                                    examquestion value = dataSnapshot.getValue(examquestion.class);
                                    DatabaseAdapter Dbadapter = new DatabaseAdapter(ExamResults.context);

                                    if (currentQuestion.equals(value.getQuestionBody())) {
                                        int id = Dbadapter.getIdOfQuestion(value.getQuestionBody());
                                        int updateId = Dbadapter.update(id, value.getQuestionBody(), value.getAnswer_1(), value.getAnswer_2(),
                                                value.getAnswer_3(), value.getAnswer_4(), value.getAnswer_5(), value.getAnswer_1_count(),
                                                value.getAnswer_2_count(), value.getAnswer_3_count(), value.getAnswer_4_count(),
                                                value.getAnswer_5_count());

                                        if (updateId == 1) {
                                            Intent intent = new Intent(ExamResults.context, Show_Result.class);
                                            ExamResults.context.startActivity(intent);
                                            Toast.makeText(ExamResults.context, "DataBase updated successfully", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(ExamResults.context, "DataBase didn't update successfully", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                        break;
                    case 7:
                        examRef8.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.getKey().equals("examquestions")) {
                                    examquestion value = dataSnapshot.getValue(examquestion.class);
                                    DatabaseAdapter Dbadapter = new DatabaseAdapter(ExamResults.context);

                                    if (currentQuestion.equals(value.getQuestionBody())) {
                                        int id = Dbadapter.getIdOfQuestion(value.getQuestionBody());
                                        int updateId = Dbadapter.update(id, value.getQuestionBody(), value.getAnswer_1(), value.getAnswer_2(),
                                                value.getAnswer_3(), value.getAnswer_4(), value.getAnswer_5(), value.getAnswer_1_count(),
                                                value.getAnswer_2_count(), value.getAnswer_3_count(), value.getAnswer_4_count(),
                                                value.getAnswer_5_count());

                                        if (updateId == 1) {
                                            Intent intent = new Intent(ExamResults.context, Show_Result.class);
                                            ExamResults.context.startActivity(intent);
                                            Toast.makeText(ExamResults.context, "DataBase updated successfully", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(ExamResults.context, "DataBase didn't update successfully", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                        break;
                    case 8:
                        examRef9.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.getKey().equals("examquestions")) {
                                    examquestion value = dataSnapshot.getValue(examquestion.class);
                                    DatabaseAdapter Dbadapter = new DatabaseAdapter(ExamResults.context);

                                    if (currentQuestion.equals(value.getQuestionBody())) {
                                        int id = Dbadapter.getIdOfQuestion(value.getQuestionBody());
                                        int updateId = Dbadapter.update(id, value.getQuestionBody(), value.getAnswer_1(), value.getAnswer_2(),
                                                value.getAnswer_3(), value.getAnswer_4(), value.getAnswer_5(), value.getAnswer_1_count(),
                                                value.getAnswer_2_count(), value.getAnswer_3_count(), value.getAnswer_4_count(),
                                                value.getAnswer_5_count());

                                        if (updateId == 1) {
                                            Intent intent = new Intent(ExamResults.context, Show_Result.class);
                                            ExamResults.context.startActivity(intent);
                                            Toast.makeText(ExamResults.context, "DataBase updated successfully", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(ExamResults.context, "DataBase didn't update successfully", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                        break;
                    case 9:
                        examRef10.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.getKey().equals("examquestions")) {
                                    examquestion value = dataSnapshot.getValue(examquestion.class);
                                    DatabaseAdapter Dbadapter = new DatabaseAdapter(ExamResults.context);

                                    if (currentQuestion.equals(value.getQuestionBody())) {
                                        int id = Dbadapter.getIdOfQuestion(value.getQuestionBody());
                                        int updateId = Dbadapter.update(id, value.getQuestionBody(), value.getAnswer_1(), value.getAnswer_2(),
                                                value.getAnswer_3(), value.getAnswer_4(), value.getAnswer_5(), value.getAnswer_1_count(),
                                                value.getAnswer_2_count(), value.getAnswer_3_count(), value.getAnswer_4_count(),
                                                value.getAnswer_5_count());

                                        if (updateId == 1) {
                                            Intent intent = new Intent(ExamResults.context, Show_Result.class);
                                            ExamResults.context.startActivity(intent);
                                            Toast.makeText(ExamResults.context, "DataBase updated successfully", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(ExamResults.context, "DataBase didn't update successfully", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                        break;

                    default:
                        break;
                }


                Show_Result.question = currentQuestion;
                Show_Result.sessionCode = ExamResults.MysessionCode;

                Intent intent = new Intent(ExamResults.context, Show_Result.class);
                ExamResults.context.startActivity(intent);
            }
        });






        return listItemView;
    }
}
