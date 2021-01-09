package com.example.project_countries;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.project_countries.database.entities.ResultTest;
import com.example.project_countries.database.entities.Test;
import com.example.project_countries.database.entities.User;

import java.util.List;

public class UserTest {
    @Embedded public User user;
    @Relation(
            parentColumn = "userId",
            entityColumn = "testId",
            associateBy = @Junction(ResultTest.class)
    )
    public List<Test> tests;
}
