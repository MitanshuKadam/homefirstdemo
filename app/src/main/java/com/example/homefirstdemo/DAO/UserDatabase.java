package com.example.homefirstdemo.DAO;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.homefirstdemo.Model.StudentModel;
import com.example.homefirstdemo.Model.UserModel;

@Database(entities = {UserModel.class, StudentModel.class},version = 2,exportSchema = false)
public abstract class UserDatabase  extends RoomDatabase {
    public abstract UserDAO getUserDAO();
    public abstract StudentDAO getAllStudent();

    private static UserDatabase INSTANCE;

    public static UserDatabase getInstance(Context context){
        if (INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context, UserDatabase.class, "User")
                    .allowMainThreadQueries()
                    .build();
        }

        return INSTANCE;
    }
}
