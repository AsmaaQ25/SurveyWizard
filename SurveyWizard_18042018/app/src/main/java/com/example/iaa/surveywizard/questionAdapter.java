package com.example.iaa.surveywizard;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by IAA on 2/20/2018.
 */
public class questionAdapter extends ArrayAdapter<questions> {

    public questionAdapter(Context context, ArrayList<questions> questionses) {
        super(context, 0, questionses);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.question_list, parent, false);
        }


        questions currentQuestion;
        currentQuestion = getItem(position);

        TextView textView = (TextView) listItemView.findViewById(R.id.textView);

        textView.setText(currentQuestion.getMquestion());

        Button button = (Button) listItemView.findViewById(R.id.delete_button);

        /*button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyQuestions.questionsList.remove(position);
            }
        });*/



        return listItemView;
    }
}