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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by IAA on 7/5/2018.
 */
public class ExamResultsAdapter extends ArrayAdapter<String> {

    public ExamResultsAdapter(Context context, ArrayList<String> questionses) {
        super(context, 0, questionses);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.results_list, parent, false);
        }


        final String currentQuestion;
        currentQuestion = getItem(position);

        TextView textView = (TextView) listItemView.findViewById(R.id.textView14);

        textView.setText(currentQuestion);


        Button showResultButton = (Button) listItemView.findViewById(R.id.showresultButton);

        showResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Show_Result.question = currentQuestion;
                Show_Result.sessionCode = ExamResults.MysessionCode;

                Intent intent = new Intent(ExamResults.context, Show_Result.class);
                ExamResults.context.startActivity(intent);
            }
        });






        return listItemView;
    }
}
