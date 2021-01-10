package com.example.project_countries.database.entities;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Junction;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import com.example.project_countries.database.DAO.ResultQuestionDAO;

import java.io.Serializable;

@Entity(tableName = "Result", foreignKeys = {@ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "userId", onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Question.class, parentColumns = "questionId", childColumns = "questionId", onDelete = ForeignKey.CASCADE)})
public class ResultQuestion {
    @PrimaryKey(autoGenerate = true)
    private int resultId;
    private long userId;
    private long questionId;
    private boolean isCorrect;

    public ResultQuestion(int resultId, long userId, long questionId, boolean isCorrect) {
        this.resultId = resultId;
        this.userId = userId;
        this.questionId = questionId;
        this.isCorrect = isCorrect;
    }
    @Ignore //pt creare ob
    public ResultQuestion(long userId, long questionId, boolean isCorrect) {
        this.userId = userId;
        this.questionId = questionId;
        this.isCorrect = isCorrect;
    }

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    @Override
    public String toString() {
        return "ResultQuestion{" +
                "userId=" + userId +
                ", questionId=" + questionId +
                ", isCorrect=" + isCorrect +
                '}';
    }

}
