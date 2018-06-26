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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import android.widget.Toast;

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

//show result button on the question_list2.xml
        Button showResultBurtton = (Button) listItemView.findViewById(R.id.showResultButton);
        showResultBurtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Show_Result.question = currentQuestion.getMquestion();

                Intent intent = new Intent(MyQuestionsSecond.context,Show_Result.class);
                MyQuestionsSecond.context.startActivity(intent);

            }
        });
//upload button on the question_list2.xml
//upload Ques to the Firebase server
        Button uploadQuestionButton = (Button) listItemView.findViewById(R.id.uploadQuestionButton);
        uploadQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef= database.getReference("asmaa");
                try {
                    myRef.setValue("plowwwwww");

                }
                catch (Exception e)
                {
                    //Toast.makeText(questionSecondAdapter.this, e.toString(), Toast.LENGTH_LONG).show();
                    //Toast.makeText(, e.toString(), Toast.LENGTH_LONG).show();

                }


            }
        }) ;


        return listItemView;
    }

}
