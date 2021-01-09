package com.example.project_countries.database.entities;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Junction;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import com.example.project_countries.database.DAO.ResultTestDAO;

import java.io.Serializable;

@Entity(primaryKeys = {"userId", "testId"})
public class ResultTest {
    public long userId;
    public long testId;
    public int score= 0;
    public Test test;

    private void calculateScore(){
        score+= test.getDifficulty()*5; // fiecare intrebare corecta are valoarea de 5 * nivelul dificultatii
    }
    //TODO: calculam pe buton din interfata cu test -> cand se incepe un test se instantiaza result testul cu id-ul luat din shared pref
    //TODO: fac si restul
}
