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

    public long insertData(String question, String Answer_1, String Answer_2, String Answer_3, String Answer_4, String Answer_5){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues contents = new ContentValues();
        contents.put(helper.column_1, question);
        contents.put(helper.Answer_1, Answer_1);
        contents.put(helper.Answer_2, Answer_2);
        contents.put(helper.Answer_3, Answer_3);
        contents.put(helper.Answer_4, Answer_4);
        contents.put(helper.Answer_5, Answer_5);

        long id = db.insert(helper.Table_Name, null, contents);
        db.close();
        return id;
    }
    questions quest;
    public ArrayList<questions> getAllQuestionsWithoutAnswers(){
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {helper.Uid, helper.column_1};
        Cursor cursor = db.query(helper.Table_Name, columns, null, null, null, null, null);
        ArrayList<questions> buf = new ArrayList<>();

        while(cursor.moveToNext()){
            int cid = cursor.getInt(cursor.getColumnIndex(helper.Uid));
            String question = cursor.getString(cursor.getColumnIndex(helper.column_1));
            quest = new questions(cid,question);
            buf.add(quest);
        }
        return buf;
    }


    public String getAllQuestionsWithAnswers(){
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {helper.Uid, helper.column_1};
        Cursor cursor = db.query(helper.Table_Name, columns, null, null, null, null, null);
        StringBuffer buf = new StringBuffer();
        while(cursor.moveToNext()){
            int cid = cursor.getInt(cursor.getColumnIndex(helper.Uid));
            String question = cursor.getString(cursor.getColumnIndex(helper.column_1));
            String answer_1 = cursor.getString(cursor.getColumnIndex(helper.Answer_1));
            String answer_2 = cursor.getString(cursor.getColumnIndex(helper.Answer_2));
            String answer_3 = cursor.getString(cursor.getColumnIndex(helper.Answer_3));
            String answer_4 = cursor.getString(cursor.getColumnIndex(helper.Answer_4));
            String answer_5 = cursor.getString(cursor.getColumnIndex(helper.Answer_5));

            buf.append(cid+" "+question+" "+answer_1+" "+answer_2+" "+answer_3+" "+answer_4+" "+answer_5+" ");
        }
        db.close();
        return buf.toString();
    }

    public int update(int oldQuestionId, String newQuestion, String newAnswer_1, String newAnswer_2, String newAnswer_3, String newAnswer_4, String newAnswer_5){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contents = new ContentValues();
        contents.put(helper.column_1, newQuestion);
        contents.put(helper.Answer_1, newAnswer_1);
        contents.put(helper.Answer_2, newAnswer_2);
        contents.put(helper.Answer_3, newAnswer_3);
        contents.put(helper.Answer_4, newAnswer_4);
        contents.put(helper.Answer_5, newAnswer_5);
        String[] whereArg = {"" +oldQuestionId};

        int count = db.update(helper.Table_Name, contents, helper.Uid + " =?", whereArg);
        db.close();
        return count;
    }

    public int delete_raw(int QuestionId){
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] whereArg = {""+QuestionId};
        int count = db.delete(helper.Table_Name, helper.Uid + " =?", whereArg);
        db.close();
        return count;
    }



    static class DatabaseHelper extends SQLiteOpenHelper{

        public static final String Database_Name = "MyQuestions";
        public static final String Table_Name = "Questions";
        public static final int version= 1;
        public static final String Uid = "_id";
        public static final String column_1 = "question";
        public static final String Answer_1 = "answer_1";
        public static final String Answer_2 = "answer_2";
        public static final String Answer_3 = "answer_3";
        public static final String Answer_4 = "answer_4";
        public static final String Answer_5 = "answer_5";

        public static final String create_table = "CREATE TABLE " + Table_Name +
                " (" + Uid +" INTEGER PRIMARY KEY AUTOINCREMENT, " + column_1 + " TEXT NOT NULL, " +
                Answer_1 + " TEXT, " + Answer_2 + " TEXT, " + Answer_3 + " TEXT, " + Answer_4 + " TEXT, "+ Answer_5 + " TEXT);";
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
