package com.example.homefirstdemo.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.homefirstdemo.Model.StudentModel;

import java.util.List;

@Dao
public interface StudentDAO {
    @Query("SELECT * FROM student")
    List<StudentModel> getAllStudent();

    @Insert
    void insertStudent(StudentModel studentModel);
}
