package com.example.project_countries.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "questions")
public class Question {
    @PrimaryKey(autoGenerate = true)
    private  int questionId;
    private String question;
    private String answer;
    private String wanswer1; // wrong answer
    private String wanswer2;
    private String wanswer3;

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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

    public String getAnswer() {
        return answer;
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


}
