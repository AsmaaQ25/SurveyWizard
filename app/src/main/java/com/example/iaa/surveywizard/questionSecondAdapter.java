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

import java.util.ArrayList;

/**
 * Created by IAA on 3/7/2018.
 */
public class questionSecondAdapter extends ArrayAdapter<questions> {
    public questionSecondAdapter(Context context, ArrayList<questions> questionses) {
        super(context, 0, questionses);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.question_list2, parent, false);
        }


        final questions currentQuestion;
        currentQuestion = getItem(position);

        CheckedTextView checkedTextView = (CheckedTextView) listItemView.findViewById(R.id.textView3);

        checkedTextView.setText(currentQuestion.getMquestion());

        Button button2 = (Button) listItemView.findViewById(R.id.button2);


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Show_Result.question = currentQuestion.getMquestion();


                Intent intent = new Intent(MyQuestionsSecond.context,Show_Result.class);
                MyQuestionsSecond.context.startActivity(intent);

            }
        });



        return listItemView;
    }

}
