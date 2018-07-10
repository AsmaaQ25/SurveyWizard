package com.example.iaa.surveywizard;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ExamResults extends AppCompatActivity {

    public static ArrayList<String> questionslist = new ArrayList<>();
    public static Context context;

    public static String MysessionName;
    public static String MysessionCode;

    TextView SessionName;
    TextView SessionCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_results);

        context = this;

        ExamResultsAdapter adapter = new ExamResultsAdapter(this, questionslist);

        ListView listView = (ListView) findViewById(R.id.results_list);

        listView.setAdapter(adapter);

        TextView text = (TextView) findViewById(R.id.textView16);


        SessionName = (TextView) findViewById(R.id.sessionname);
        SessionCode = (TextView) findViewById(R.id.sessioncode);

        SessionName.setText(MysessionName);
        SessionCode.setText(MysessionCode);

        if (questionslist.size() == 0)
        {
            text.setVisibility(View.VISIBLE);
        }else {
            text.setVisibility(View.GONE);
        }


        startService(new Intent(this, ClosingService.class));

        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef= database.getReference();

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                if (dataSnapshot != null) {
                    if (dataSnapshot.getKey().equals(MysessionCode)) {
                        if (!dataSnapshot.getValue().equals(MysessionCode)) {
                            SessionExamData value = dataSnapshot.getValue(SessionExamData.class);
                            DatabaseAdapter Dbadapter = new DatabaseAdapter(ExamResults.context);

                            ArrayList<examquestion> exams = value.getExamquestions();


                            for (int i = 0; i < exams.size(); i++) {

                                int id = Dbadapter.getIdOfQuestion(exams.get(i).getQuestionBody());
                                int updateId = Dbadapter.update(id, exams.get(i).getQuestionBody(),
                                        exams.get(i).getAnswer_1(), exams.get(i).getAnswer_2(),
                                        exams.get(i).getAnswer_3(), exams.get(i).getAnswer_4(),
                                        exams.get(i).getAnswer_5(), exams.get(i).getAnswer_1_count(),
                                        exams.get(i).getAnswer_2_count(), exams.get(i).getAnswer_3_count(),
                                        exams.get(i).getAnswer_4_count(), exams.get(i).getAnswer_5_count());


                                if (updateId == 1) {
                                    Toast.makeText(ExamResults.this, "DataBase updated successfully", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(ExamResults.this, "DataBase didn't update successfully", Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    }
                }

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });






    }
}
