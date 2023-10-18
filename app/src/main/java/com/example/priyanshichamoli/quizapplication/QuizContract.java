package com.example.priyanshichamoli.quizapplication;

import android.provider.BaseColumns;



public final class QuizContract {

    private QuizContract(){

    }

    public static class QuestionTable implements BaseColumns{
        public static final String TABLE_NAME = "quiz_questions";
        public static final String COL_QUESTION = "question";
        public static final String COL_OPTION1 = "option1";
        public static final String COL_OPTION2 = "option2";
        public static final String COL_OPTION3 = "option3";
        public static final String COL_ANSWER_NR = "answer_nr";
    }
}
