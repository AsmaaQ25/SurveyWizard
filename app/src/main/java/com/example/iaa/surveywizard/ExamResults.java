package com.example.iaa.surveywizard;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

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



    }
}
