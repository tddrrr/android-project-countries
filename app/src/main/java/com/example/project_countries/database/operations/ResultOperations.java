package com.example.project_countries.database.operations;

import android.app.Activity;
import android.content.Context;

import com.example.project_countries.asyncTask.AsyncTaskRunner;
import com.example.project_countries.asyncTask.Callback;
import com.example.project_countries.database.DAO.ResultQuestionDAO;
import com.example.project_countries.database.entities.ResultQuestion;
import com.example.project_countries.database.manager.DatabaseManager;

import java.util.List;
import java.util.concurrent.Callable;

public class ResultOperations {
    private Context context;
    private DatabaseManager databaseManager;
    private final AsyncTaskRunner taskRunner;
    private final ResultQuestionDAO resultQuestionDAO;


    public ResultOperations(Context context){
        this.context=context;
        taskRunner = new AsyncTaskRunner();
        resultQuestionDAO=DatabaseManager.getInstance(context).getResultQuestionDAO();
    }

    public List<ResultQuestion> getUsers(){ return resultQuestionDAO.getAllResults(); }
    public void getAll(Callback<List<ResultQuestion>> callback) {
        Callable<List<ResultQuestion>> callable = new Callable<List<ResultQuestion>>() {
            @Override
            public List<ResultQuestion> call() {
                return resultQuestionDAO.getAllResults();
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void insert(Callback<ResultQuestion> callback, final ResultQuestion resultQuestion) {
        Callable<ResultQuestion> callable = new Callable<ResultQuestion>() {
            @Override
            public ResultQuestion call() {
                if (resultQuestion == null) {
                    return null;
                }
                long id = resultQuestionDAO.insertResultQuestion(resultQuestion);
                if (id == -1) {
                    return null;
                }
                //user.setId(id);
                return resultQuestion;
            }
        };
        taskRunner.executeAsync(callable, callback);
    }
    public void update(Callback<ResultQuestion> callback, final ResultQuestion resultQuestion) {
        Callable<ResultQuestion> callable = new Callable<ResultQuestion>() {
            @Override
            public ResultQuestion call() {
                if (resultQuestion == null) {
                    return null;
                }
                int count = resultQuestionDAO.updateResultQuestion(resultQuestion);
                if (count < 1) {
                    return null;
                }
                return resultQuestion;
            }
        };
        taskRunner.executeAsync(callable, callback);
    }


    public void delete(Callback<Integer> callback, final ResultQuestion resultQuestion) {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() {
                if (resultQuestion == null) {
                    return -1;
                }
                return resultQuestionDAO.deleteResultQuestion(resultQuestion);
            }
        };
        taskRunner.executeAsync(callable, callback);
    }
    public void getScore(Callback<Integer> callback, final ResultQuestion resultQuestion, final int id){
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() {
                return resultQuestionDAO.getScore(id);
            }
        };
        taskRunner.executeAsync(callable, callback);
    }
}
