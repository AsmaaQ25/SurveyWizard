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

import java.util.ArrayList;

/**
 * Created by IAA on 7/5/2018.
 */
public class create_examAdapter extends ArrayAdapter<questionsWithAnswers> {

    public create_examAdapter(Context context, ArrayList<questionsWithAnswers> questionses) {
        super(context, 0, questionses);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.examlistview, parent, false);
        }


        final questionsWithAnswers currentQuestion;
        currentQuestion = getItem(position);

        final CheckedTextView textView = (CheckedTextView) listItemView.findViewById(R.id.questText);

        textView.setText(currentQuestion.getQuestionBody());

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Create_Exam.selectedQuestioncount < 10) {
                    if (!Create_Exam.questionslist.contains(textView.getText().toString())) {
                        Create_Exam.questionslist.add(textView.getText().toString());
                        Create_Exam.selectedQuestioncount++;
                        textView.setChecked(true);
                    } else {
                        Create_Exam.questionslist.remove(Create_Exam.questionslist.indexOf(textView.getText().toString()));
                        Create_Exam.selectedQuestioncount--;
                        textView.setChecked(false);

                    }
                } else {
                    if (!Create_Exam.questionslist.contains(textView.getText().toString())) {
                        Toast.makeText(Create_Exam.context, "You picked 10 questions", Toast.LENGTH_LONG).show();
                    } else {
                        Create_Exam.questionslist.remove(Create_Exam.questionslist.indexOf(textView.getText().toString()));
                        Create_Exam.selectedQuestioncount--;
                        textView.setChecked(false);

                    }

                }

                Create_Exam.questionNumber.setText(Integer.toString(Create_Exam.selectedQuestioncount));

            }
        });


        return listItemView;
    }
}


