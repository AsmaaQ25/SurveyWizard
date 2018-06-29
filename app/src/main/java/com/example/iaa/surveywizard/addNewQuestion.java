package com.example.iaa.surveywizard;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by Anas on 25-Feb-18.
 */

public class addNewQuestion extends AppCompatActivity {
    EditText question;
    EditText answer_1;
    EditText answer_2;
    EditText answer_3;
    EditText answer_4;
    EditText answer_5;
    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_question);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.check(R.id.three_choices);
        question = (EditText) findViewById(R.id.add_question);
        answer_1 = (EditText) findViewById(R.id.first_choice);
        answer_2 = (EditText) findViewById(R.id.second_choice);
        answer_3 = (EditText) findViewById(R.id.third_choice);
        answer_4 = (EditText) findViewById(R.id.fourth_choice);
        answer_5 = (EditText) findViewById(R.id.fifth_choice);
    }
    RadioButton radioButton2;
    public void noOfAnswers(View view){
        RadioButton radioButton1 = (RadioButton) findViewById(R.id.two_choices);
        radioButton2 = (RadioButton) findViewById(R.id.three_choices);
        RadioButton radioButton3 = (RadioButton) findViewById(R.id.four_choices);
        RadioButton radioButton4 = (RadioButton) findViewById(R.id.five_choices);
        int id = -1;

        id = radioGroup.getCheckedRadioButtonId();

        switch(id){
            case R.id.two_choices:
                answer_1.setVisibility(View.VISIBLE);
                answer_2.setVisibility(View.VISIBLE);
                answer_3.setVisibility(View.GONE);
                answer_4.setVisibility(View.GONE);
                answer_5.setVisibility(View.GONE);
                break;

            case R.id.three_choices:
                answer_1.setVisibility(View.VISIBLE);
                answer_2.setVisibility(View.VISIBLE);
                answer_3.setVisibility(View.VISIBLE);
                answer_4.setVisibility(View.GONE);
                answer_5.setVisibility(View.GONE);
                break;

            case R.id.four_choices:
                answer_1.setVisibility(View.VISIBLE);
                answer_2.setVisibility(View.VISIBLE);
                answer_3.setVisibility(View.VISIBLE);
                answer_4.setVisibility(View.VISIBLE);
                answer_5.setVisibility(View.GONE);
                break;

            case R.id.five_choices:
                answer_1.setVisibility(View.VISIBLE);
                answer_2.setVisibility(View.VISIBLE);
                answer_3.setVisibility(View.VISIBLE);
                answer_4.setVisibility(View.VISIBLE);
                answer_5.setVisibility(View.VISIBLE);
                break;
        }
    }

    DatabaseAdapter DBadapter;

    public void saveQuestion(View view){

        if ((question.getText().toString().isEmpty() != true) && (answer_1.getText().toString().isEmpty() != true) && (answer_2.getText().toString().isEmpty() != true)) {
            DBadapter = new DatabaseAdapter(this);

            long Id = DBadapter.insertData(question.getText().toString(), answer_1.getText().toString(),
                    answer_2.getText().toString(), answer_3.getText().toString(),
                    answer_4.getText().toString(), answer_5.getText().toString(), 0, 0, 0, 0, 0);

            if (Id < 0) {
                Toast toast = Toast.makeText(this, "try again", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 10, 10);
                toast.show();
            } else {

                Toast toast = Toast.makeText(this, "question added successfully", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 10, 10);
                toast.show();
                question.setText("");
                answer_1.setText("");
                answer_2.setText("");
                answer_3.setText("");
                answer_4.setText("");
                answer_4.setText("");
                answer_5.setText("");
                radioGroup.check(R.id.three_choices);

                Intent intent = new Intent(MyQuestions.context,MyQuestions.class);
                MyQuestions.context.startActivity(intent);
            }
        }else {
            Toast toast = Toast.makeText(this, "add the question and its answers!?", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 10, 10);
            toast.show();
        }
    }

}
