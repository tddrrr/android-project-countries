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
}
