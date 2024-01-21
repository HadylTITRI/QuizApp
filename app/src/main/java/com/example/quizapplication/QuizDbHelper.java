package com.example.quizapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "quiz.db";
    private static final int DATABASE_VERSION = 1;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE questions ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "question TEXT,"
                + "option1 TEXT,"
                + "option2 TEXT,"
                + "option3 TEXT,"
                + "option4 TEXT,"
                + "answer TEXT"
                + ")";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS questions");
        onCreate(db);
    }
    public void addQuestion(QuestionsList question) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("question", question.getQuestion());
        values.put("option1", question.getOption1());
        values.put("option2", question.getOption2());
        values.put("option3", question.getOption3());
        values.put("option4", question.getOption4());
        values.put("answer", question.getAnswer());

        db.insert("questions", null, values);
        db.close();
    }

    @SuppressLint("Range")
    public List<QuestionsList> getAllQuestionsByTopic(String topic) {
        List<QuestionsList> questionList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {
                "question",
                "option1",
                "option2",
                "option3",
                "option4",
                "answer"
        };

        String selection = "topic = ?";
        String[] selectionArgs = {topic};

        Cursor cursor = db.query("questions", columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                QuestionsList question = new QuestionsList();
                question.setQuestion(cursor.getString(cursor.getColumnIndex("question")));
                question.setOption1(cursor.getString(cursor.getColumnIndex("option1")));
                question.setOption2(cursor.getString(cursor.getColumnIndex("option2")));
                question.setOption3(cursor.getString(cursor.getColumnIndex("option3")));
                question.setOption4(cursor.getString(cursor.getColumnIndex("option4")));
                question.setAnswer(cursor.getString(cursor.getColumnIndex("answer")));
                questionList.add(question);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return questionList;
    }
}