package com.example.project_countries.database.operations;

import android.app.Activity;
import android.content.Context;

import com.example.project_countries.asyncTask.AsyncTaskRunner;
import com.example.project_countries.asyncTask.Callback;
import com.example.project_countries.database.DAO.UserDAO;
import com.example.project_countries.database.entities.User;
import com.example.project_countries.database.manager.DatabaseManager;

import java.util.List;
import java.util.concurrent.Callable;

public class UserOperations {
    private List<User> userList;
    private Context context;
    private DatabaseManager databaseManager;
    private final AsyncTaskRunner taskRunner;
    private final UserDAO userDAO;


    public UserOperations(Context context){
        this.context=context;
        this.taskRunner = new AsyncTaskRunner();
        this.userDAO = DatabaseManager.getInstance(context).getUserDAO();
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
    public void findUserById(Callback<User> callback, final int id) {
        Callable<User> callable = new Callable<User>() {
            @Override
            public User call() {
                if (id <1) {
                    return null;
                }
                User user = userDAO.findUserById(id);
                if(user == null){
                    return null;
                }
                return user;
            }
        };
        taskRunner.executeAsync(callable, callback);
    }
    public void findUserByEmail(final String email, Callback<User> callback){
        Callable<User> callable = new Callable<User>() {
            @Override
            public User call() throws Exception {
                if(email.isEmpty() || email == null){
                    return null;
                }
                User user = userDAO.findUserByEmail(email);
                if(user ==null){
                    return null;
                }
                return user;
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

//    public void updateFirstName(Callback<User> callback, final User user, final int id, final String fname) {
//        Callable<User> callable = new Callable<User>() {
//            @Override
//            public User call() {
//                if (user == null) {
//                    return null;
//                }
//                userDAO.updateFirstName(userDAO.findUserById(id), fname);
//                return user;
//            }
//        };
//        taskRunner.executeAsync(callable, callback);
//    }

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

    public void updateUserScore(Callback<Integer> callback, int id, int score) {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                if (id < 0) {
                    return -1;
                }
                return userDAO.updateUserScore(score, id);
            }
        };
        taskRunner.executeAsync(callable, callback);
    }
}
