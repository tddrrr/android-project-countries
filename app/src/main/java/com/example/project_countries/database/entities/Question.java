package com.example.project_countries.database.entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "questions")
public class Question {
    @PrimaryKey(autoGenerate = true)
    private  int questionId;
    private String question;
    private String correctAnswer;
    private String wanswer1; // pun 4 variante, din care una e egala cu correctAnswer
    private String wanswer2;
    private String wanswer3;
    private String wanswer4;

    public Question(int questionId, String question, String correctAnswer, String wanswer1, String wanswer2, String wanswer3, String wanswer4) {
        this.questionId = questionId;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.wanswer1 = wanswer1;
        this.wanswer2 = wanswer2;
        this.wanswer3 = wanswer3;
        this.wanswer4 = wanswer4;
    }
    @Ignore
    public Question(String question, String correctAnswer, String wanswer1, String wanswer2, String wanswer3, String wanswer4) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.wanswer1 = wanswer1;
        this.wanswer2 = wanswer2;
        this.wanswer3 = wanswer3;
        this.wanswer4 = wanswer4;
    }

    public String getWanswer4() {
        return wanswer4;
    }

    public void setWanswer4(String wanswer4) {
        this.wanswer4 = wanswer4;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setWanswer1(String wanswer1) {
        this.wanswer1 = wanswer1;
    }

    public void setWanswer2(String wanswer2) {
        this.wanswer2 = wanswer2;
    }

    public void setWanswer3(String wanswer3) {
        this.wanswer3 = wanswer3;
    }


    public int getQuestionId() {
        return questionId;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getWanswer1() {
        return wanswer1;
    }

    public String getWanswer2() {
        return wanswer2;
    }

    public String getWanswer3() {
        return wanswer3;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", question='" + question + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", wanswer1='" + wanswer1 + '\'' +
                ", wanswer2='" + wanswer2 + '\'' +
                ", wanswer3='" + wanswer3 + '\'' +
                ", wanswer4='" + wanswer4 + '\'' +
                '}';
    }
}
