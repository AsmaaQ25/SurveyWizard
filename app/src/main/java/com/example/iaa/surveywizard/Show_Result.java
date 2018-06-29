package com.example.iaa.surveywizard;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Show_Result extends AppCompatActivity {

    private float answer_1_res;
    private float answer_2_res;
    private float answer_3_res;
    private float answer_4_res;
    private float answer_5_res;

    private String answer_1;
    private String answer_2;
    private String answer_3;
    private String answer_4;
    private String answer_5;

    public static String sessionCode;
    public static String question;

    TextView questionTextView;
    TextView answer1TextView;
    TextView answer2TextView;
    TextView answer3TextView;
    TextView answer4TextView;
    TextView answer5TextView;
    TextView answer1countTextView;
    TextView answer2countTextView;
    TextView answer3countTextView;
    TextView answer4countTextView;
    TextView answer5countTextView;


    private float[] yData;

    private String[] xData;

    PieChart pieChart;

    DatabaseAdapter Dbadapter;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference(sessionCode);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__result);

        questionTextView = (TextView) findViewById(R.id.textView10);
        answer1TextView = (TextView) findViewById(R.id.answer1textView);
        answer2TextView = (TextView) findViewById(R.id.answer2textView);
        answer3TextView = (TextView) findViewById(R.id.answer3textView);
        answer4TextView = (TextView) findViewById(R.id.answer4textView);
        answer5TextView = (TextView) findViewById(R.id.answer5textView);
        answer1countTextView = (TextView) findViewById(R.id.answer1counttextView);
        answer2countTextView = (TextView) findViewById(R.id.answer2counttextView);
        answer3countTextView = (TextView) findViewById(R.id.answer3counttextView);
        answer4countTextView = (TextView) findViewById(R.id.answer4counttextView);
        answer5countTextView = (TextView) findViewById(R.id.answer5counttextView);


        pieChart = (PieChart) findViewById(R.id.pieChart);
        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(30f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Answers Chart");
        pieChart.setCenterTextSize(10);


        Dbadapter = new DatabaseAdapter(this);
        final int id = Dbadapter.getIdOfQuestion(question);

        xData = new String[5];
        yData = new float[5];

        questionsWithAnswers Quest;
        Quest = Dbadapter.getQuestionWithAnswers(id);

        answer_1 = Quest.getAnswer_1();
        answer_2 = Quest.getAnswer_2();
        answer_3 = Quest.getAnswer_3();
        answer_4 = Quest.getAnswer_4();
        answer_5 = Quest.getAnswer_5();


        questionTextView.setText(question);
        answer1TextView.setText(answer_1);
        answer2TextView.setText(answer_2);
        if (answer_3.equals("")){
            answer3TextView.setVisibility(View.GONE);
        }
        else{
            answer3TextView.setText(answer_3);
            answer3TextView.setVisibility(View.VISIBLE);
        }

        if (answer_4.equals("")){
            answer4TextView.setVisibility(View.GONE);
        }
        else{
            answer4TextView.setText(answer_4);
            answer4TextView.setVisibility(View.VISIBLE);
        }

        if (answer_5.equals("")){
            answer5TextView.setVisibility(View.GONE);
        }
        else{
            answer5TextView.setText(answer_5);
            answer5TextView.setVisibility(View.VISIBLE);
        }


        int[] answerscount;

        answerscount = Dbadapter.getAnswersCount(Quest.getQuestionBody());

        int sum = answerscount[0] + answerscount[1] + answerscount[2] + answerscount[3] + answerscount[4];

        if (sum != 0) {
            answer_1_res = (float) answerscount[0] / sum;
            answer_2_res = (float) answerscount[1] / sum;
            answer_3_res = (float) answerscount[2] / sum;
            answer_4_res = (float) answerscount[3] / sum;
            answer_5_res = (float) answerscount[4] / sum;
        }

        answer1countTextView.setText(Integer.toString(answerscount[0]));
        answer2countTextView.setText(Integer.toString(answerscount[1]));

        if (answer3TextView.getVisibility() == View.GONE){
            answer3countTextView.setVisibility(View.GONE);
        }else {
            answer3countTextView.setText(Integer.toString(answerscount[2]));
            answer3countTextView.setVisibility(View.VISIBLE);
        }

        if (answer4TextView.getVisibility() == View.GONE){
            answer4countTextView.setVisibility(View.GONE);
        }else {
            answer4countTextView.setText(Integer.toString(answerscount[3]));
            answer4countTextView.setVisibility(View.VISIBLE);
        }

        if (answer5TextView.getVisibility() == View.GONE){
            answer5countTextView.setVisibility(View.GONE);
        }else {
            answer5countTextView.setText(Integer.toString(answerscount[4]));
            answer5countTextView.setVisibility(View.VISIBLE);
        }




        xData[0] = answer_1;
        xData[1] = answer_2;
        xData[2] = answer_3;
        xData[3] = answer_4;
        xData[4] = answer_5;

        yData[0] = answer_1_res;
        yData[1] = answer_2_res;
        yData[2] = answer_3_res;
        yData[3] = answer_4_res;
        yData[4] = answer_5_res;

        addDataSet();

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Log.d("Show_Result", "onValueSelected: Value select from chart.");
                Log.d("Show_Result", "onValueSelected: " + e.toString());
                Log.d("Show_Result", "onValueSelected: " + h.toString());


                int pos1 = 0;
                String peresentage = e.toString();
                peresentage = peresentage.substring(17);
                Log.d("Show_Result", "onValueSelected: " + peresentage);
                for(int i = 0; i < 5; i++){
                    if(yData[i] == Float.parseFloat(peresentage)){
                        pos1 = i;
                        break;
                    }
                }
                String AnswerNum = xData[pos1];
                Toast.makeText(Show_Result.this, AnswerNum + "\n"  + Float.toString(Float.parseFloat(peresentage)*100) + "%", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }



    private void addDataSet() {
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for(int i = 0; i < yData.length; i++){
            yEntrys.add(new PieEntry(yData[i] , i));
        }

        for(int i = 1; i < xData.length; i++){
            xEntrys.add(xData[i]);
        }

        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Answers");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);
        colors.add(Color.YELLOW);
        colors.add(Color.MAGENTA);

        pieDataSet.setColors(colors);

        //add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();

    }
}
