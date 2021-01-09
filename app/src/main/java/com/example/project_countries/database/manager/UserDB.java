package com.example.project_countries.database.manager;

        import androidx.room.Database;
        import androidx.room.RoomDatabase;

        import com.example.project_countries.database.DAO.UserDAO;
        import com.example.project_countries.database.entities.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDB extends RoomDatabase {
    public abstract UserDAO userDAO();
}
