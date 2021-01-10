package com.example.project_countries.database.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.project_countries.database.entities.ResultQuestion;
import com.example.project_countries.database.entities.User;

import java.util.List;

import javax.xml.transform.Result;

@Dao
public interface ResultQuestionDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertResultQuestion(ResultQuestion resultQuestion); //long e val id-ului
    @Delete
    int deleteResultQuestion(ResultQuestion resultQuestion);
    @Update
    int updateResultQuestion(ResultQuestion resultQuestion);
    @Query("SELECT * FROM Result")
    List<ResultQuestion> getAllResults();
    @Query("SELECT count(resultId) from Result where isCorrect LIKE 'true' AND userId=:id")
    int getScore(int id);
}
