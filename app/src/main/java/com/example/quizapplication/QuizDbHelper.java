package com.example.quizapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "questions";
    private static final int DATABASE_VERSION = 2;
    private SQLiteDatabase dbase;

    public QuizDbHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase = db;
        String query = "CREATE TABLE IF NOT EXISTS Questions (question TEXT ,option1 TEXT,option2 TEXT,"
                +"option3 TEXT, option4 TEXT,answer TEXT,userSelectedAnswer TEXT,topic TEXT)";

        dbase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Questions");
        onCreate(db);
    }
    public void addQuestion(String question, String option1, String option2, String option3, String option4, String answer,String userSelectedAnswer, String topic) {
        dbase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("question", question);
        values.put("option1", option1);
        values.put("option2", option2);
        values.put("option3", option3);
        values.put("option4", option4);
        values.put("answer", answer);
        values.put("userSelectedAnswer", userSelectedAnswer);
        values.put("topic", topic);

        dbase.insert("questions", null, values);
        dbase.close();

    }

    @SuppressLint("Range")
    public List<QuestionsList> getQuestions(String topic) {

        List<QuestionsList> questionsList = new ArrayList<>();
        dbase = this.getReadableDatabase();

        Cursor cursor = dbase.query("questions", new String[]{"question", "option1", "option2", "option3", "option4", "answer", "userSelectedAnswer", "topic"},
                "topic=?", new String[]{topic}, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                // Lisez les données de la ligne du curseur et ajoutez-les à la liste
                QuestionsList question = new QuestionsList();
                question.setQuestion(cursor.getString(cursor.getColumnIndex("question")));
                question.setOption1(cursor.getString(cursor.getColumnIndex("option1")));
                question.setOption2(cursor.getString(cursor.getColumnIndex("option2")));
                question.setOption3(cursor.getString(cursor.getColumnIndex("option3")));
                question.setOption4(cursor.getString(cursor.getColumnIndex("option4")));
                question.setAnswer(cursor.getString(cursor.getColumnIndex("answer")));
                question.setUserSelectedAnswer(cursor.getString(cursor.getColumnIndex("userSelectedAnswer")));
                question.setTopic(cursor.getString(cursor.getColumnIndex("topic")));

                questionsList.add(question);
            } while (cursor.moveToNext());
            cursor.close();
        }
        if(cursor != null){
            cursor.close();
        }
        return questionsList;
    }
}