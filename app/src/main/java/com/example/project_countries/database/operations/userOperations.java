package com.example.project_countries.database.operations;

import android.app.Activity;

import com.example.project_countries.asyncTask.AsyncTaskRunner;
import com.example.project_countries.asyncTask.Callback;
import com.example.project_countries.database.DAO.UserDAO;
import com.example.project_countries.database.entities.User;
import com.example.project_countries.database.manager.DatabaseManager;

import java.util.List;
import java.util.concurrent.Callable;

public class userOperations {
    private List<User> userList;
    private Activity context;
    private DatabaseManager databaseManager;
    private final AsyncTaskRunner taskRunner;
    private final UserDAO userDAO;


    public userOperations(Activity context, List<User> dataList){
        this.context=context;
       // this.userList=dataList;
        notifyAll();
        taskRunner = new AsyncTaskRunner();
        userDAO=DatabaseManager.getInstance(context).getUserDAO();
    }

    public List<User> getUsers(){ return userDAO.getAllUsers(); }
    public void getAll(Callback<List<User>> callback) {
        Callable<List<User>> callable = new Callable<List<User>>() {
            @Override
            public List<User> call() {
                return userDAO.getAllUsers();
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void insert(Callback<User> callback, final User user) {
        Callable<User> callable = new Callable<User>() {
            @Override
            public User call() {
                if (user == null) {
                    return null;
                }
                long id = userDAO.insertUser(user);
                if (id == -1) {
                    return null;
                }
                //user.setId(id);
                return user;
            }
        };
        taskRunner.executeAsync(callable, callback);
    }
    public void update(Callback<User> callback, final User user) {
        Callable<User> callable = new Callable<User>() {
            @Override
            public User call() {
                if (user == null) {
                    return null;
                }
                int count = userDAO.updateUser(user);
                if (count < 1) {
                    return null;
                }
                return user;
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void delete(Callback<Integer> callback, final User user) {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() {
                if (user == null) {
                    return -1;
                }
                return userDAO.deleteUser(user);
            }
        };
        taskRunner.executeAsync(callable, callback);
    }
}
