package com.example.iaa.surveywizard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.Gravity;
import android.widget.Toast;

import junit.runner.Version;

import java.security.PublicKey;
import java.util.ArrayList;

/**
 * Created by IAA on 5/6/2018.
 */
public class DatabaseAdapter {

    private DatabaseHelper helper;
    public DatabaseAdapter(Context context){
        helper = new DatabaseHelper(context);
    }

    public long insertData(String questionBody, String Answer_1, String Answer_2, String Answer_3,
                           String Answer_4, String Answer_5, int Answer_1_count, int Answer_2_count,
                           int Answer_3_count, int Answer_4_count, int Answer_5_count){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues contents = new ContentValues();
        contents.put(helper.column_1, questionBody);
        contents.put(helper.Answer_1, Answer_1);
        contents.put(helper.Answer_2, Answer_2);
        contents.put(helper.Answer_3, Answer_3);
        contents.put(helper.Answer_4, Answer_4);
        contents.put(helper.Answer_5, Answer_5);
        contents.put(helper.Answer_1_count, Answer_1_count);
        contents.put(helper.Answer_2_count, Answer_2_count);
        contents.put(helper.Answer_3_count, Answer_3_count);
        contents.put(helper.Answer_4_count, Answer_4_count);
        contents.put(helper.Answer_5_count, Answer_5_count);

        long id = db.insert(helper.Table_Name, null, contents);
        db.close();
        return id;
    }



    public ArrayList<questionsWithAnswers> getAllQuestionsWithoutAnswers(){
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {helper.Uid, helper.column_1};
        Cursor cursor = db.query(helper.Table_Name, columns, null, null, null, null, null);
        ArrayList<questionsWithAnswers> buf = new ArrayList<>();
        questionsWithAnswers quest;
        while(cursor.moveToNext()){
            int cid = cursor.getInt(cursor.getColumnIndex(helper.Uid));
            String question = cursor.getString(cursor.getColumnIndex(helper.column_1));
            quest = new questionsWithAnswers(question);
            buf.add(quest);
        }
        return buf;
    }


    public int[] getAnswersCount(String questionX){
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {helper.Uid, helper.column_1, helper.Answer_1, helper.Answer_2,
                helper.Answer_3, helper.Answer_4, helper.Answer_5, helper.Answer_1_count,
                helper.Answer_2_count, helper.Answer_3_count, helper.Answer_4_count, helper.Answer_5_count};

        String[] selectioArgs={questionX};
        Cursor cursor = db.query(helper.Table_Name, columns, helper.column_1 + " =?", selectioArgs, null, null, null);

        int[] answerscount = new int[5];

        while(cursor.moveToNext()){
            answerscount[0] = cursor.getInt(cursor.getColumnIndex(helper.Answer_1_count));
            answerscount[1] = cursor.getInt(cursor.getColumnIndex(helper.Answer_2_count));
            answerscount[2] = cursor.getInt(cursor.getColumnIndex(helper.Answer_3_count));
            answerscount[3] = cursor.getInt(cursor.getColumnIndex(helper.Answer_4_count));
            answerscount[4] = cursor.getInt(cursor.getColumnIndex(helper.Answer_5_count));
        }
        return answerscount;
    }


    public int getIdOfQuestion(String questionX){
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {helper.Uid, helper.column_1, helper.Answer_1, helper.Answer_2,
                helper.Answer_3, helper.Answer_4, helper.Answer_5, helper.Answer_1_count,
                helper.Answer_2_count, helper.Answer_3_count, helper.Answer_4_count, helper.Answer_5_count};
        String[] selectioArgs={questionX};
        Cursor cursor = db.query(helper.Table_Name, columns, helper.column_1 + " =?", selectioArgs, null, null, null);

        int questId=-1;
        while(cursor.moveToNext()){
            questId = cursor.getInt(cursor.getColumnIndex(helper.Uid));
        }
        return questId;
    }


    public questionsWithAnswers getQuestionWithAnswers(int id){
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = new String[]{helper.Uid, helper.column_1, helper.Answer_1, helper.Answer_2,
                helper.Answer_3, helper.Answer_4, helper.Answer_5, helper.Answer_1_count,
                helper.Answer_2_count, helper.Answer_3_count, helper.Answer_4_count, helper.Answer_5_count};

        Cursor cursor = db.query(helper.Table_Name, columns, helper.Uid+" = '"+id+"'", null, null, null, null);

        questionsWithAnswers quest;
        int cid=id;
        String question="";
        String answer1="";
        String answer2="";
        String answer3="";
        String answer4="";
        String answer5="";

        while(cursor.moveToNext()){
            cid = cursor.getInt(cursor.getColumnIndex(helper.Uid));
            question = cursor.getString(cursor.getColumnIndex(helper.column_1));
            answer1 = cursor.getString(cursor.getColumnIndex(helper.Answer_1));
            answer2 = cursor.getString(cursor.getColumnIndex(helper.Answer_2));
            answer3 = cursor.getString(cursor.getColumnIndex(helper.Answer_3));
            answer4 = cursor.getString(cursor.getColumnIndex(helper.Answer_4));
            answer5 = cursor.getString(cursor.getColumnIndex(helper.Answer_5));
        }
        quest = new questionsWithAnswers(cid,question,answer1,answer2,answer3,answer4,answer5);
        return quest;
    }

    public int update(int oldQuestionId, String newQuestion, String newAnswer_1, String newAnswer_2,
                      String newAnswer_3, String newAnswer_4, String newAnswer_5,
                      int Answer_1_count, int Answer_2_count,
                      int Answer_3_count, int Answer_4_count, int Answer_5_count){

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contents = new ContentValues();
        contents.put(helper.column_1, newQuestion);
        contents.put(helper.Answer_1, newAnswer_1);
        contents.put(helper.Answer_2, newAnswer_2);
        contents.put(helper.Answer_3, newAnswer_3);
        contents.put(helper.Answer_4, newAnswer_4);
        contents.put(helper.Answer_5, newAnswer_5);
        contents.put(helper.Answer_1_count, Answer_1_count);
        contents.put(helper.Answer_2_count, Answer_2_count);
        contents.put(helper.Answer_3_count, Answer_3_count);
        contents.put(helper.Answer_4_count, Answer_4_count);
        contents.put(helper.Answer_5_count, Answer_5_count);

        String[] whereArg = {"" +oldQuestionId};

        int count = db.update(helper.Table_Name, contents, helper.Uid + " =?", whereArg);
        db.close();
        return count;
    }

    public int delete_raw(String Question){
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] whereArg = {Question};
        int count = db.delete(helper.Table_Name, helper.column_1 + " =?", whereArg);
        db.close();
        return count;
    }



    static class DatabaseHelper extends SQLiteOpenHelper{

        public static final String Database_Name = "MyQuestions";
        public static final String Table_Name = "Questions";
        public static final int version= 2;
        public static final String Uid = "_id";
        public static final String column_1 = "question";
        public static final String Answer_1 = "answer_1";
        public static final String Answer_2 = "answer_2";
        public static final String Answer_3 = "answer_3";
        public static final String Answer_4 = "answer_4";
        public static final String Answer_5 = "answer_5";
        public static final String Answer_1_count = "answer_1_count";
        public static final String Answer_2_count = "answer_2_count";
        public static final String Answer_3_count = "answer_3_count";
        public static final String Answer_4_count = "answer_4_count";
        public static final String Answer_5_count = "answer_5_count";

        public static final String create_table = "CREATE TABLE " + Table_Name +
                " (" + Uid +" INTEGER PRIMARY KEY AUTOINCREMENT, " + column_1 + " TEXT NOT NULL, " +
                Answer_1 + " TEXT, " + Answer_2 + " TEXT, " + Answer_3 + " TEXT, " + Answer_4 + " TEXT, "
                + Answer_5 + " TEXT, " + Answer_1_count + " INTEGER, " + Answer_2_count + " INTEGER, "
                + Answer_3_count + " INTEGER, " + Answer_4_count + " INTEGER, " + Answer_5_count + " INTEGER);";
        public static final String drop_table = "DROP TABLE IF EXISTS" + Table_Name ;

        Context context;

        public DatabaseHelper(Context context){
            super(context, Database_Name, null, version);
            this.context = context;
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL(create_table);
            }
            catch (SQLException e){
                Toast toast = Toast.makeText(this.context, e.toString(), Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,10,10);
                toast.show();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try{
                db.execSQL(drop_table);
                onCreate(db);
            }
            catch (SQLException e){
                Toast toast = Toast.makeText(this.context, e.toString(), Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,10,10);
                toast.show();
            }
        }
    }
}
