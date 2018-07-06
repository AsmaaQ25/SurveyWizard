package com.example.iaa.surveywizard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class answersofQuestion extends AppCompatActivity {

    public static String question;

    TextView questionbody;
    TextView answer1;
    TextView answer2;
    TextView answer3;
    TextView answer4;
    TextView answer5;

    EditText Question;
    EditText answer1edit;
    EditText answer2edit;
    EditText answer3edit;
    EditText answer4edit;
    EditText answer5edit;

    Button Edit;
    Button Update;

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answersof_question);

        questionbody = (TextView) findViewById(R.id.question_bdy);
        answer1 = (TextView) findViewById(R.id.first_Answer);
        answer2 = (TextView) findViewById(R.id.second_Answer);
        answer3 = (TextView) findViewById(R.id.third_Answer);
        answer4 = (TextView) findViewById(R.id.forth_Answer);
        answer5 = (TextView) findViewById(R.id.fifth_Answer);

        Question = (EditText) findViewById(R.id.questionEdit);
        answer1edit = (EditText) findViewById(R.id.answer1Edit);
        answer2edit = (EditText) findViewById(R.id.answer2Edit);
        answer3edit = (EditText) findViewById(R.id.answer3Edit);
        answer4edit = (EditText) findViewById(R.id.answer4Edit);
        answer5edit = (EditText) findViewById(R.id.answer5Edit);

        Edit = (Button) findViewById(R.id.edit_button);
        Update = (Button) findViewById(R.id.update_button);

        DatabaseAdapter Dbadapter = new DatabaseAdapter(this);

        questionsWithAnswers quest = Dbadapter.getQuestionWithAnswers(Dbadapter.getIdOfQuestion(question));

        questionbody.setText(question);
        answer1.setText(quest.getAnswer_1());
        answer2.setText(quest.getAnswer_2());
        if (quest.getAnswer_3().equals(""))
        {
            answer3.setVisibility(View.GONE);
        }else {
            answer3.setVisibility(View.VISIBLE);
            answer3.setText(quest.getAnswer_3());
        }
        if (quest.getAnswer_4().equals(""))
        {
            answer4.setVisibility(View.GONE);
        }else {
            answer4.setVisibility(View.VISIBLE);
            answer4.setText(quest.getAnswer_4());
        }

        if (quest.getAnswer_5().equals(""))
        {
            answer5.setVisibility(View.GONE);
        }else {
            answer5.setVisibility(View.VISIBLE);
            answer5.setText(quest.getAnswer_5());
        }


    }


    public void editquestion(View view) {
        linearLayout = (LinearLayout) findViewById(R.id.editquetionlinear);

        linearLayout.setVisibility(View.VISIBLE);
        Update.setVisibility(View.VISIBLE);

        Question.setText(question);
        answer1edit.setText(answer1.getText().toString());
        answer2edit.setText(answer2.getText().toString());
        answer3edit.setText(answer3.getText().toString());
        answer4edit.setText(answer4.getText().toString());
        answer5edit.setText(answer5.getText().toString());

        Edit.setVisibility(View.GONE);

    }

    public void updatequestion(View view) {


        DatabaseAdapter Dbadapter = new DatabaseAdapter(this);
        int count = Dbadapter.update(Dbadapter.getIdOfQuestion(question), Question.getText().toString(),
                answer1edit.getText().toString(), answer2edit.getText().toString(), answer3edit.getText().toString(),
                answer4edit.getText().toString(), answer5edit.getText().toString(), Dbadapter.getAnswersCount(question)[0],
                Dbadapter.getAnswersCount(question)[1], Dbadapter.getAnswersCount(question)[2], Dbadapter.getAnswersCount(question)[3],
                Dbadapter.getAnswersCount(question)[4]);

        if (count == 1)
        {
            Edit.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.GONE);
            Update.setVisibility(View.GONE);
            Toast.makeText(this, "Question updated successfully", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, answersofQuestion.class);
            startActivity(intent);
        }else {
            Edit.setVisibility(View.GONE);
            linearLayout.setVisibility(View.VISIBLE);
            Update.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Update failed, retry", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onBackPressed() {
        //your code when back button pressed
        Intent intent = new Intent(this, questionsOfCategory.class);
        startActivity(intent);

    }

}
