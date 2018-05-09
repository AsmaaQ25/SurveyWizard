package com.example.iaa.surveywizard;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import java.util.ArrayList;

public class Show_Result extends AppCompatActivity {

    private float answer_1_res = 25.0f;
    private float answer_2_res = 15.0f;
    private float answer_3_res = 30.0f;
    private float answer_4_res = 20.0f;
    private float answer_5_res = 10.0f;

    private String answer_1;
    private String answer_2;
    private String answer_3;
    private String answer_4;
    private String answer_5;

    public static String question;
    public static int questionId;


    private float[] yData = {answer_1_res, answer_2_res, answer_3_res, answer_4_res, answer_5_res};

    private String[] xData;

    PieChart pieChart;

    public static ArrayList<questions> questionsList = new ArrayList<>();
    DatabaseAdapter Dbadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__result);
        pieChart = (PieChart) findViewById(R.id.pieChart);
        pieChart.setRotationEnabled(true);
        //pieChart.setUsePercentValues(true);
        //pieChart.setHoleColor(Color.BLUE);
        //pieChart.setCenterTextColor(Color.BLACK);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Results Chart");
        pieChart.setCenterTextSize(10);
        //pieChart.setDrawEntryLabels(true);
        //pieChart.setEntryLabelTextSize(20);
        //More options just check out the documentation!
        Dbadapter = new DatabaseAdapter(this);
        int id = Dbadapter.getIdOfQuestion(question);

        xData = new String[5];
        questionsWithAnswers Quest;
        Quest = Dbadapter.getQuestionWithAnswers(id);
        answer_1 = Quest.getmAnswer_1();
        answer_2 = Quest.getmAnswer_2();
        answer_3 = Quest.getmAnswer_3();
        answer_4 = Quest.getmAnswer_4();
        answer_5 = Quest.getmAnswer_5();

        TextView textView = (TextView) findViewById(R.id.textView10);
        textView.setText(question);

        xData[0] = answer_1;
        xData[1] = answer_2;
        xData[2] = answer_3;
        xData[3] = answer_4;
        xData[4] = answer_5;


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
                Toast.makeText(Show_Result.this, AnswerNum + "\n"  + peresentage + "%", Toast.LENGTH_LONG).show();
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
