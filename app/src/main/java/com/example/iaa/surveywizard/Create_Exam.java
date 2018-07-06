package com.example.iaa.surveywizard;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Create_Exam extends AppCompatActivity {

    public static ArrayList<questionsWithAnswers> questionsList = new ArrayList<>();
    public static ArrayList<String> questionslist = new ArrayList<>();
    public static Context context;

    public static String MysessionName;
    public static String MysessionCode;

    TextView SessionName;
    TextView SessionCode;
    TextView text;
    public static TextView questionNumber;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef= database.getReference(MysessionCode);


    public static int selectedQuestioncount = 0;
    private int sendFlag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__exam);

        context = this;

        startService(new Intent(this, ClosingService.class));

        ClosingService.flag=2;

        DatabaseAdapter Dbadapter = new DatabaseAdapter(this);
        questionsList = Dbadapter.getAllQuestionsWithoutAnswers();

        questionNumber = (TextView) findViewById(R.id.questionPicked);

        create_examAdapter adapter = new create_examAdapter(this, questionsList);

        ListView listView = (ListView) findViewById(R.id.question_exam_list);

        listView.setAdapter(adapter);


        text = (TextView) findViewById(R.id.text3);

        if (questionsList.size() == 0)
        {
            text.setVisibility(View.VISIBLE);
        }else {
            text.setVisibility(View.GONE);
        }

        SessionName = (TextView) findViewById(R.id.sessionnametext);
        SessionCode = (TextView) findViewById(R.id.sessioncodetext);

        SessionName.setText(MysessionName);
        SessionCode.setText(MysessionCode);




    }

    public void sendExam(View view) {

        if (questionslist.size() != 0) {


            ArrayList<examquestion> questionsList2 = new ArrayList<>();
            DatabaseAdapter Dbadapter = new DatabaseAdapter(this);




            for (int i=0; i<questionslist.size(); i++)
            {
                questionsWithAnswers quest = Dbadapter.getQuestionWithAnswers(Dbadapter.getIdOfQuestion(questionslist.get(i)));
                int[] answersCount = new int[5];
                answersCount = Dbadapter.getAnswersCount(quest.getQuestionBody());

                examquestion Examquestion = new examquestion(quest.getQuestionBody(), quest.getAnswer_1(),
                        quest.getAnswer_2(), quest.getAnswer_3(), quest.getAnswer_4(), quest.getAnswer_5(),
                        answersCount[0], answersCount[1], answersCount[2], answersCount[3], answersCount[4]);

                questionsList2.add(Examquestion);

            }

            SessionExamData sessionExamData = new SessionExamData(MysessionName, questionsList2);

            try {
                myRef.setValue(sessionExamData);
                sendFlag = 1;
                Toast.makeText(Create_Exam.context, "exam uploaded successfully", Toast.LENGTH_LONG).show();

            }
            catch (Exception e)
            {
                Toast.makeText(Create_Exam.context, e.toString(), Toast.LENGTH_LONG).show();
            }

        }
    }

    public void reset(View view) {

        questionslist.clear();
        questionNumber.setText("0");
        selectedQuestioncount=0;

        Intent intent = new Intent(this, Create_Exam.class);
        startActivity(intent);


    }

    @Override
    public void onBackPressed() {
        //your code when back button pressed

    }

    protected void ondestroy() {
        myRef.removeValue();
    }


    public void endSession(View view) {
        myRef.removeValue();

        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }

    public void results(View view) {
        if (sendFlag != 0) {

            Intent intent = new Intent(this, ExamResults.class);

            ExamResults.MysessionCode = MysessionCode;
            ExamResults.MysessionName = MysessionName;
            ExamResults.questionslist = questionslist;

            startActivity(intent);
        }

    }
}
