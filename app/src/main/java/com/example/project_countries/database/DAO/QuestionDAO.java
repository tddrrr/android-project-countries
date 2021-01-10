package com.example.project_countries.database.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.project_countries.database.entities.Question;

import java.util.List;

@Dao
public interface QuestionDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertQuestion(Question question); //long e val id-ului
    @Delete
    int deleteQuestion(Question question);
    @Update
    int updateQuestion(Question question);
    @Query("SELECT * FROM questions")
    List<Question> getAllQuestions();
}
