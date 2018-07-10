package com.example.iaa.surveywizard;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewDebug;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Anas on 25-Feb-18.
 */

public class MyQuestionsSecond extends AppCompatActivity {
    public static ArrayList<questionsWithAnswers> questionsList = new ArrayList<>();

    public static Context context;
    public static String MysessionName;
    public static String MysessionCode;

    TextView newSessionName;
    TextView newSessionCode;

    FirebaseDatabase database = FirebaseDatabase.getInstance();


    DatabaseReference myRef1 = database.getReference();
    DatabaseReference myRef = database.getReference(MysessionCode);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_questions_second);
        context = this;


        startService(new Intent(this, ClosingService.class));

        ClosingService.flag=1;

        newSessionName = (TextView) findViewById(R.id.sessionNametextView);
        newSessionCode = (TextView) findViewById(R.id.sessionCodetextView);

        newSessionName.setText(MysessionName);
        newSessionCode.setText(MysessionCode);




        final DatabaseAdapter Dbadapter = new DatabaseAdapter(this);
        questionsList = Dbadapter.getAllQuestionsWithoutAnswers();

        try {
            if(haveNetworkConnection() == true) {
                myRef1.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                        //set the question view and show the question
                        try {
                            if (dataSnapshot != null) {
                                if (MysessionCode.equals(dataSnapshot.getKey())) {
                                    if (!dataSnapshot.getValue().equals(MysessionCode)) {
                                        SessionData value = dataSnapshot.getValue(SessionData.class);
                                        int id = Dbadapter.getIdOfQuestion(value.getQuestionBody());
                                        int updateId = Dbadapter.update(id, value.getQuestionBody(), value.getAnswer_1(), value.getAnswer_2(),
                                                value.getAnswer_3(), value.getAnswer_4(), value.getAnswer_5(), value.getAnswer_1_count(),
                                                value.getAnswer_2_count(), value.getAnswer_3_count(), value.getAnswer_4_count(),
                                                value.getAnswer_5_count());

                                        if (updateId == 1) {
                                            Toast.makeText(MyQuestionsSecond.this, "DataBase updated successfully", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(MyQuestionsSecond.this, "DataBase didn't update successfully", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                            Toast.makeText(MyQuestionsSecond.this, e.toString() + " can't read", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }else{
                Toast.makeText(MyQuestionsSecond.this, "You don't have Internet connection", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e)
        {
            Toast.makeText(MyQuestionsSecond.this, e.toString() + " can't read", Toast.LENGTH_LONG).show();
        }


        questionSecondAdapter adapter = new questionSecondAdapter(this, questionsList);

        ListView listView = (ListView) findViewById(R.id.listView2);

        try {
            listView.setAdapter(adapter);
        }catch (Exception e){
            Toast.makeText(MyQuestionsSecond.this, e.toString(), Toast.LENGTH_LONG).show();
        }

        TextView text = (TextView) findViewById(R.id.textView12);

        if (questionsList.size() == 0)
        {
            text.setVisibility(View.VISIBLE);
        }else {
            text.setVisibility(View.GONE);
        }

    }

    @Override
    public void onBackPressed() {
        //your code when back button pressed


    }


    public void endSession(View view) {

        if(haveNetworkConnection() == true) {
            myRef.onDisconnect().removeValue();

            myRef.removeValue();

            Intent intent = new Intent(this, HomePage.class);
            startActivity(intent);
        }else{
            Toast.makeText(MyQuestionsSecond.this, "You don't have Internet connection", Toast.LENGTH_LONG).show();
        }
    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
}
