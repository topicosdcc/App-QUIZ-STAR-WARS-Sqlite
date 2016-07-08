package com.ufrr.quizvestibularufrr.questionsClass;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionDateBase {

    private SQLiteDatabase conn;
    private static final String TABLE_QUESTION = "RECEBER_POR_";
    private static final String TABLE_QUESTION1 = "StarWars_BasicoI";
    private Context context;

    public QuestionDateBase(Context context, SQLiteDatabase connSqLiteDatabase) {
        this.context = context;
        this.conn = connSqLiteDatabase;
    }


    // Get ten random questions
    public List<Question> getTenQuestoesBancoDados(String TABLE_QUESTOES) {
        List<Question> questionList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_QUESTOES
                + " ORDER BY Random() LIMIT 10";
        //Cursor cursor = conn.rawQuery(selectQuery, null);
        //Cursor cursor = conn.query(TABLE_QUESTION1, null, null, null, null, null, null);
        Cursor cursor = conn.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Question q = new Question();
                q.setID(cursor.getInt(0));
                q.setText(cursor.getString(1));

                List<String> choiceList = new ArrayList<>();
                choiceList.add(cursor.getString(2));
                choiceList.add(cursor.getString(3));
                choiceList.add(cursor.getString(4));
                choiceList.add(cursor.getString(5));

                // Shuffle the choices
                Collections.shuffle(choiceList);
                q.setChoiceList(choiceList);

                q.setAnswer(cursor.getString(6));

                questionList.add(q);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return questionList;
    }


    public void close() {
        conn.close();
    }

    // Get ten random questions
    public List<Question> getTenQuestions() {
        List<Question> questionList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_QUESTION1
                + " ORDER BY Random() LIMIT 10";
        //Cursor cursor = conn.rawQuery(selectQuery, null);
        //Cursor cursor = conn.query(TABLE_QUESTION1, null, null, null, null, null, null);
        Cursor cursor = conn.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Question q = new Question();
                q.setID(cursor.getInt(0));
                q.setText(cursor.getString(1));

                List<String> choiceList = new ArrayList<>();
                choiceList.add(cursor.getString(2));
                choiceList.add(cursor.getString(3));
                choiceList.add(cursor.getString(4));
                choiceList.add(cursor.getString(5));

                // Shuffle the choices
                Collections.shuffle(choiceList);
                q.setChoiceList(choiceList);

                q.setAnswer(cursor.getString(6));

                questionList.add(q);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return questionList;
    }
}
