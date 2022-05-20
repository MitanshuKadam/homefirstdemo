package com.example.homefirstdemo.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.homefirstdemo.Model.UserModel;

import java.util.ArrayList;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM User WHERE username = :username and mobile =:mobile" )
    UserModel getUser(String username, String mobile);

    @Insert
    void insert(UserModel userModel);

/*    @Query("SELECT * FROM User")
    ArrayList<UserModel> getallUser();*/

}
