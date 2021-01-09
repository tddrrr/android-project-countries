package com.example.project_countries.database.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.project_countries.database.entities.ResultTest;
import com.example.project_countries.database.entities.User;

import javax.xml.transform.Result;

@Dao
public interface ResultTestDAO {
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    public long resultInsertion(Result result);

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    public long userInsertion(User user);
}
