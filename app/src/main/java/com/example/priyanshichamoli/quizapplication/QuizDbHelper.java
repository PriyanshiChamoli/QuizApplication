package com.example.priyanshichamoli.quizapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import com.example.priyanshichamoli.quizapplication.QuizContract.*;




public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "QuizApplication1.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;


    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQ_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionTable.TABLE_NAME + " ( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COL_QUESTION + " TEXT, " +
                QuestionTable.COL_OPTION1 + " TEXT, " +
                QuestionTable.COL_OPTION2 + " TEXT, " +
                QuestionTable.COL_OPTION3 + " TEXT, " +
                QuestionTable.COL_ANSWER_NR + " INTEGER" +
                ")";
        db.execSQL(SQ_QUESTIONS_TABLE);
        fillsQuestionTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionTable.TABLE_NAME);
        onCreate(db);
    }
    private void fillsQuestionTable()
    {
        Question q1 = new Question("Which crop is sown on the largest area in India?","Rice","Wheat","Maize",1);
        addQuestion(q1);
        Question q2 = new Question("The capital of Uttarakhand","Mussorie","Dehradun","Nainital",2);
        addQuestion(q2);
        Question q3 = new Question("Which state has the largest population","Uttar Pradesh","Bihar","Maharashtra",1);
        addQuestion(q3);
        Question q4 = new Question("This river is also called Ganga of South","Godavari","Krishna","Kaveri",3);
        addQuestion(q4);
        Question q5 = new Question("Which of the following was the auother of Economist?","Kalhan","Visakhadatta","Chanakya",3);
        addQuestion(q5);
        Question q6 = new Question("Which state is the main language Khasi?","Mizoram","Nagaland","Meghalaya",3);
        addQuestion(q6);
    }

    private void addQuestion(Question question)
    {
        ContentValues cv = new ContentValues();
        cv.put(QuestionTable.COL_QUESTION, question.getQuestion());
        cv.put(QuestionTable.COL_OPTION1, question.getOption1());
        cv.put(QuestionTable.COL_OPTION2, question.getOption2());
        cv.put(QuestionTable.COL_OPTION3, question.getOption3());
        cv.put(QuestionTable.COL_ANSWER_NR, question.getAnswerNr());
        db.insert(QuestionTable.TABLE_NAME,null, cv);
    }

    public ArrayList<Question> getAllQuestion()
    {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionTable.TABLE_NAME,null);
        if(c.moveToFirst())
        {
            do{
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COL_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COL_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COL_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COL_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionTable.COL_ANSWER_NR)));
                questionList.add(question);
            }while (c.moveToNext());
        }
        c.close();
        return questionList;
    }

}
