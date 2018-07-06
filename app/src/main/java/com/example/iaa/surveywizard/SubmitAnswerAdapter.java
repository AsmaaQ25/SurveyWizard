package com.example.iaa.surveywizard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IAA on 7/6/2018.
 */
public class SubmitAnswerAdapter extends ArrayAdapter<examquestion> {

    public SubmitAnswerAdapter(Context context, ArrayList<examquestion> questionses) {
        super(context, 0, questionses);
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.answerexamlistview, parent, false);
        }


        final examquestion currentQuestion;
        currentQuestion = getItem(position);

        TextView question = (TextView) listItemView.findViewById(R.id.question_Body);
        final RadioGroup radioGroup = (RadioGroup) listItemView.findViewById(R.id.optionsradioGroup);
        RadioButton firstanswer = (RadioButton) listItemView.findViewById(R.id.First_option);
        RadioButton secondanswer = (RadioButton) listItemView.findViewById(R.id.Second_option);
        RadioButton thirdanswer = (RadioButton) listItemView.findViewById(R.id.Third_option);
        RadioButton forthanswer = (RadioButton) listItemView.findViewById(R.id.Forth_option);
        RadioButton fifthanswer = (RadioButton) listItemView.findViewById(R.id.Fifth_option);

        question.setText(currentQuestion.getQuestionBody());
        firstanswer.setText(currentQuestion.getAnswer_1());
        secondanswer.setText(currentQuestion.getAnswer_2());
        if (currentQuestion.getAnswer_3().equals("")) {
            thirdanswer.setVisibility(View.GONE);
        }else {
            thirdanswer.setVisibility(View.VISIBLE);
            thirdanswer.setText(currentQuestion.getAnswer_3());
        }
        if (currentQuestion.getAnswer_4().equals("")) {
            forthanswer.setVisibility(View.GONE);
        }else {
            forthanswer.setVisibility(View.VISIBLE);
            forthanswer.setText(currentQuestion.getAnswer_4());
        }
        if (currentQuestion.getAnswer_5().equals("")) {
            fifthanswer.setVisibility(View.GONE);
        }else {
            fifthanswer.setVisibility(View.VISIBLE);
            fifthanswer.setText(currentQuestion.getAnswer_5());
        }

        firstanswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {
                    case 0:
                        submitAnswer.answerquestion1 = currentQuestion.getAnswer_1_count() + 1;
                        submitAnswer.Answerquestion1 = 1;
                        break;
                    case 1:
                        submitAnswer.answerquestion2 = currentQuestion.getAnswer_1_count() + 1;
                        submitAnswer.Answerquestion2 = 1;
                        break;
                    case 2:
                        submitAnswer.answerquestion3 = currentQuestion.getAnswer_1_count() + 1;
                        submitAnswer.Answerquestion3 = 1;
                        break;
                    case 3:
                        submitAnswer.answerquestion4 = currentQuestion.getAnswer_1_count() + 1;
                        submitAnswer.Answerquestion4 = 1;
                        break;
                    case 4:
                        submitAnswer.answerquestion5 = currentQuestion.getAnswer_1_count() + 1;
                        submitAnswer.Answerquestion5 = 1;
                        break;
                    case 5:
                        submitAnswer.answerquestion6 = currentQuestion.getAnswer_1_count() + 1;
                        submitAnswer.Answerquestion6 = 1;
                        break;
                    case 6:
                        submitAnswer.answerquestion7 = currentQuestion.getAnswer_1_count() + 1;
                        submitAnswer.Answerquestion7 = 1;
                        break;
                    case 7:
                        submitAnswer.answerquestion8 = currentQuestion.getAnswer_1_count() + 1;
                        submitAnswer.Answerquestion8 = 1;
                        break;
                    case 8:
                        submitAnswer.answerquestion9 = currentQuestion.getAnswer_1_count() + 1;
                        submitAnswer.Answerquestion9 = 1;
                        break;
                    case 9:
                        submitAnswer.answerquestion10 = currentQuestion.getAnswer_1_count() + 1;
                        submitAnswer.Answerquestion10 = 1;
                        break;
                    default:
                        break;
                }

            }
        });

        secondanswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {
                    case 0:
                        submitAnswer.answerquestion1 = currentQuestion.getAnswer_2_count() + 1;
                        submitAnswer.Answerquestion1 = 2;
                        break;
                    case 1:
                        submitAnswer.answerquestion2 = currentQuestion.getAnswer_2_count() + 1;
                        submitAnswer.Answerquestion2 = 2;
                        break;
                    case 2:
                        submitAnswer.answerquestion3 = currentQuestion.getAnswer_2_count() + 1;
                        submitAnswer.Answerquestion3 = 2;
                        break;
                    case 3:
                        submitAnswer.answerquestion4 = currentQuestion.getAnswer_2_count() + 1;
                        submitAnswer.Answerquestion4 = 2;
                        break;
                    case 4:
                        submitAnswer.answerquestion5 = currentQuestion.getAnswer_2_count() + 1;
                        submitAnswer.Answerquestion5 = 2;
                        break;
                    case 5:
                        submitAnswer.answerquestion6 = currentQuestion.getAnswer_2_count() + 1;
                        submitAnswer.Answerquestion6 = 2;
                        break;
                    case 6:
                        submitAnswer.answerquestion7 = currentQuestion.getAnswer_2_count() + 1;
                        submitAnswer.Answerquestion7 = 2;
                        break;
                    case 7:
                        submitAnswer.answerquestion8 = currentQuestion.getAnswer_2_count() + 1;
                        submitAnswer.Answerquestion8 = 2;
                        break;
                    case 8:
                        submitAnswer.answerquestion9 = currentQuestion.getAnswer_2_count() + 1;
                        submitAnswer.Answerquestion9 = 2;
                        break;
                    case 9:
                        submitAnswer.answerquestion10 = currentQuestion.getAnswer_2_count() + 1;
                        submitAnswer.Answerquestion10 = 2;
                        break;
                    default:
                        break;
                }
            }
        });

        thirdanswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {
                    case 0:
                        submitAnswer.answerquestion1 = currentQuestion.getAnswer_3_count() + 1;
                        submitAnswer.Answerquestion1 = 3;
                        break;
                    case 1:
                        submitAnswer.answerquestion2 = currentQuestion.getAnswer_3_count() + 1;
                        submitAnswer.Answerquestion2 = 3;
                        break;
                    case 2:
                        submitAnswer.answerquestion3 = currentQuestion.getAnswer_3_count() + 1;
                        submitAnswer.Answerquestion3 = 3;
                        break;
                    case 3:
                        submitAnswer.answerquestion4 = currentQuestion.getAnswer_3_count() + 1;
                        submitAnswer.Answerquestion4 = 3;
                        break;
                    case 4:
                        submitAnswer.answerquestion5 = currentQuestion.getAnswer_3_count() + 1;
                        submitAnswer.Answerquestion5 = 3;
                        break;
                    case 5:
                        submitAnswer.answerquestion6 = currentQuestion.getAnswer_3_count() + 1;
                        submitAnswer.Answerquestion6 = 3;
                        break;
                    case 6:
                        submitAnswer.answerquestion7 = currentQuestion.getAnswer_3_count() + 1;
                        submitAnswer.Answerquestion7 = 3;
                        break;
                    case 7:
                        submitAnswer.answerquestion8 = currentQuestion.getAnswer_3_count() + 1;
                        submitAnswer.Answerquestion8 = 3;
                        break;
                    case 8:
                        submitAnswer.answerquestion9 = currentQuestion.getAnswer_3_count() + 1;
                        submitAnswer.Answerquestion9 = 3;
                        break;
                    case 9:
                        submitAnswer.answerquestion10 = currentQuestion.getAnswer_3_count() + 1;
                        submitAnswer.Answerquestion10 = 3;
                        break;
                    default:
                        break;
                }
            }
        });


        forthanswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {
                    case 0:
                        submitAnswer.answerquestion1 = currentQuestion.getAnswer_4_count() + 1;
                        submitAnswer.Answerquestion1 = 4;
                        break;
                    case 1:
                        submitAnswer.answerquestion2 = currentQuestion.getAnswer_4_count() + 1;
                        submitAnswer.Answerquestion2 = 4;
                        break;
                    case 2:
                        submitAnswer.answerquestion3 = currentQuestion.getAnswer_4_count() + 1;
                        submitAnswer.Answerquestion3 = 4;
                        break;
                    case 3:
                        submitAnswer.answerquestion4 = currentQuestion.getAnswer_4_count() + 1;
                        submitAnswer.Answerquestion4 = 4;
                        break;
                    case 4:
                        submitAnswer.answerquestion5 = currentQuestion.getAnswer_4_count() + 1;
                        submitAnswer.Answerquestion5 = 4;
                        break;
                    case 5:
                        submitAnswer.answerquestion6 = currentQuestion.getAnswer_4_count() + 1;
                        submitAnswer.Answerquestion6 = 4;
                        break;
                    case 6:
                        submitAnswer.answerquestion7 = currentQuestion.getAnswer_4_count() + 1;
                        submitAnswer.Answerquestion7 = 4;
                        break;
                    case 7:
                        submitAnswer.answerquestion8 = currentQuestion.getAnswer_4_count() + 1;
                        submitAnswer.Answerquestion8 = 4;
                        break;
                    case 8:
                        submitAnswer.answerquestion9 = currentQuestion.getAnswer_4_count() + 1;
                        submitAnswer.Answerquestion9 = 4;
                        break;
                    case 9:
                        submitAnswer.answerquestion10 = currentQuestion.getAnswer_4_count() + 1;
                        submitAnswer.Answerquestion10 = 4;
                        break;
                    default:
                        break;
                }
            }
        });

        fifthanswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {
                    case 0:
                        submitAnswer.answerquestion1 = currentQuestion.getAnswer_5_count() + 1;
                        submitAnswer.Answerquestion1 = 5;
                        break;
                    case 1:
                        submitAnswer.answerquestion2 = currentQuestion.getAnswer_5_count() + 1;
                        submitAnswer.Answerquestion2 = 5;
                        break;
                    case 2:
                        submitAnswer.answerquestion3 = currentQuestion.getAnswer_5_count() + 1;
                        submitAnswer.Answerquestion3 = 5;
                        break;
                    case 3:
                        submitAnswer.answerquestion4 = currentQuestion.getAnswer_5_count() + 1;
                        submitAnswer.Answerquestion4 = 5;
                        break;
                    case 4:
                        submitAnswer.answerquestion5 = currentQuestion.getAnswer_5_count() + 1;
                        submitAnswer.Answerquestion5 = 5;
                        break;
                    case 5:
                        submitAnswer.answerquestion6 = currentQuestion.getAnswer_5_count() + 1;
                        submitAnswer.Answerquestion6 = 5;
                        break;
                    case 6:
                        submitAnswer.answerquestion7 = currentQuestion.getAnswer_5_count() + 1;
                        submitAnswer.Answerquestion7 = 5;
                        break;
                    case 7:
                        submitAnswer.answerquestion8 = currentQuestion.getAnswer_5_count() + 1;
                        submitAnswer.Answerquestion8 = 5;
                        break;
                    case 8:
                        submitAnswer.answerquestion9 = currentQuestion.getAnswer_5_count() + 1;
                        submitAnswer.Answerquestion9 = 5;
                        break;
                    case 9:
                        submitAnswer.answerquestion10 = currentQuestion.getAnswer_5_count() + 1;
                        submitAnswer.Answerquestion10 = 5;
                        break;
                    default:
                        break;
                }
            }
        });

        return listItemView;
    }

}
