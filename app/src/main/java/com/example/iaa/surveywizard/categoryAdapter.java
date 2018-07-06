package com.example.iaa.surveywizard;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IAA on 7/6/2018.
 */
public class categoryAdapter extends ArrayAdapter<String> {

    public categoryAdapter(Context context, ArrayList<String> objects) {
        super(context, 0, objects);
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.examlistview, parent, false);
        }


        final String currentQuestion;
        currentQuestion = getItem(position);

        TextView textView = (TextView) listItemView.findViewById(R.id.questText);

        textView.setText(currentQuestion);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent joinsessionintent = new Intent(MyQuestions.context, questionsOfCategory.class);
                questionsOfCategory.category = currentQuestion;
                MyQuestions.context.startActivity(joinsessionintent);
            }
        });



        return listItemView;
    }
}
