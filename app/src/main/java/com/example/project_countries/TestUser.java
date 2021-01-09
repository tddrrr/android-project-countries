package com.example.project_countries;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.project_countries.database.entities.ResultTest;
import com.example.project_countries.database.entities.Test;
import com.example.project_countries.database.entities.User;

import java.util.List;

public class TestUser {
    @Embedded public Test test;
    @Relation(
            parentColumn = "testId",
            entityColumn = "userId",
            associateBy = @Junction(ResultTest.class)
    )
    public List<User> users;
}
