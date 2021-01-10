package com.example.project_countries.database.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.project_countries.database.entities.User;

import java.util.List;
@Dao
public interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertUser(User user); //long e val id-ului
    @Delete
    int deleteUser(User user);
    @Update
    int updateUser(User user);
    @Query("SELECT * FROM users")
    List<User> getAllUsers();
    @Query("UPDATE users SET firstName=:fText Where userId = :id")
    void updateFirstName(User id, String fText);
    @Query("SELECT * FROM users WHERE userId=:id")
    User findUserById(int id);
    @Query("SELECT * FROM users WHERE email=:email")
    User findUserByEmail(String email);
}
