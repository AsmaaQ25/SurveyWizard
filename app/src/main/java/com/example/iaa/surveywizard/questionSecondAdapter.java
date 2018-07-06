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
public class questionSecondAdapter extends ArrayAdapter<questionsWithAnswers> {

    public questionSecondAdapter(Context context, ArrayList<questionsWithAnswers> questionses) {
        super(context, 0, questionses);
    }

    public static Context context;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.question_list2, parent, false);
        }


        final questionsWithAnswers currentQuestion;
        currentQuestion = getItem(position);

        TextView textView = (TextView) listItemView.findViewById(R.id.textView3);
        textView.setText(currentQuestion.getQuestionBody());

        //show result button on the question_list2.xml
        Button showResultButton = (Button) listItemView.findViewById(R.id.showResultButton);
        showResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Show_Result.question = currentQuestion.getQuestionBody();
                Show_Result.sessionCode = MyQuestionsSecond.MysessionCode;

                    Intent intent = new Intent(MyQuestionsSecond.context, Show_Result.class);
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
                DatabaseReference myRef= database.getReference(MyQuestionsSecond.MysessionCode);

                DatabaseAdapter Dbadapter;
                Dbadapter = new DatabaseAdapter(MyQuestionsSecond.context);

                int id = Dbadapter.getIdOfQuestion(currentQuestion.getQuestionBody());
                questionsWithAnswers ques = Dbadapter.getQuestionWithAnswers(id);

                int[] answersCount;
                answersCount = Dbadapter.getAnswersCount(ques.getQuestionBody());

                SessionData session = new SessionData(MyQuestionsSecond.MysessionName,
                        ques.getQuestionBody(), ques.getAnswer_1(), ques.getAnswer_2(),
                        ques.getAnswer_3(), ques.getAnswer_4(), ques.getAnswer_5(),
                        answersCount[0], answersCount[1], answersCount[2], answersCount[3], answersCount[4]);

                try {
                    myRef.setValue(session);
                    Toast.makeText(MyQuestionsSecond.context, "Question uploaded successfully", Toast.LENGTH_LONG).show();

                }
                catch (Exception e)
                {
                    Toast.makeText(MyQuestionsSecond.context, e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }) ;


        return listItemView;
    }

}
