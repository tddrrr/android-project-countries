package com.example.project_countries.database.operations;

import android.app.Activity;

import com.example.project_countries.asyncTask.AsyncTaskRunner;
import com.example.project_countries.asyncTask.Callback;
import com.example.project_countries.database.DAO.QuestionDAO;
import com.example.project_countries.database.entities.Question;
import com.example.project_countries.database.manager.DatabaseManager;

import java.util.List;
import java.util.concurrent.Callable;

public class QuestionOperations {
    private Activity context;
    private DatabaseManager databaseManager;
    private final AsyncTaskRunner taskRunner;
    private final QuestionDAO questionDAO;


    public QuestionOperations(Activity context, List<Question> dataList){
        this.context=context;
        notifyAll();
        taskRunner = new AsyncTaskRunner();
        questionDAO=DatabaseManager.getInstance(context).getQuestionDAO();
    }

    public List<Question> getUsers(){ return questionDAO.getAllQuestions(); }
    public void getAll(Callback<List<Question>> callback) {
        Callable<List<Question>> callable = new Callable<List<Question>>() {
            @Override
            public List<Question> call() {
                return questionDAO.getAllQuestions();
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void insert(Callback<Question> callback, final Question question) {
        Callable<Question> callable = new Callable<Question>() {
            @Override
            public Question call() {
                if (question == null) {
                    return null;
                }
                long id = questionDAO.insertQuestion(question);
                if (id == -1) {
                    return null;
                }
                //user.setId(id);
                return question;
            }
        };
        taskRunner.executeAsync(callable, callback);
    }
    public void update(Callback<Question> callback, final Question question) {
        Callable<Question> callable = new Callable<Question>() {
            @Override
            public Question call() {
                if (question == null) {
                    return null;
                }
                int count = questionDAO.updateQuestion(question);
                if (count < 1) {
                    return null;
                }
                return question;
            }
        };
        taskRunner.executeAsync(callable, callback);
    }


    public void delete(Callback<Integer> callback, final Question question) {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() {
                if (question == null) {
                    return -1;
                }
                return questionDAO.deleteQuestion(question);
            }
        };
        taskRunner.executeAsync(callable, callback);
    }
}
