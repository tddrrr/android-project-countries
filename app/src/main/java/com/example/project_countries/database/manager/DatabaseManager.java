package com.example.project_countries.database.manager;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.project_countries.database.DAO.QuestionDAO;
import com.example.project_countries.database.DAO.ResultQuestionDAO;
import com.example.project_countries.database.DAO.UserDAO;
import com.example.project_countries.database.entities.ResultQuestion;
import com.example.project_countries.database.entities.Question;
import com.example.project_countries.database.entities.User;

@Database(entities = {User.class, Question.class, ResultQuestion.class}, exportSchema = false, version = 2) //de incrementat version daca schimb structura BD
public abstract class DatabaseManager extends RoomDatabase {
    private static final String PROJECT_DB = "ProjectDB";
    private static DatabaseManager databaseManager;

    public static DatabaseManager getInstance(Context context) {
        if (databaseManager == null) { //la al doilea thread verific iar
            synchronized (DatabaseManager.class) { //pt prioritizarea threadurilor; daca 2 fragmente mi-ar face 2 apeluri in acelasi timp, synchronized ar ordona procesarea threadurilor
                if (databaseManager == null) { //daca e nul, initializez;
                    databaseManager = Room.databaseBuilder(context, DatabaseManager.class, PROJECT_DB) //initializare: context, clasa de initial, nume bd
                    .fallbackToDestructiveMigration() //la fiecare modificare asupra tabelei, daca nu merge migrarea, ne sterge inregistrarile din tabela
                            //                    .allowMainThreadQueries()
                            .build();
                }
            }
        }

        return databaseManager;
    }
    //conexiunea cu DAO
    public abstract UserDAO getUserDAO();
    public abstract QuestionDAO getQuestionDAO();
    public abstract ResultQuestionDAO getResultQuestionDAO();
}
