package com.example.homefirstdemo.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "student")
public class StudentModel {
    @PrimaryKey(autoGenerate = true)
    @NotNull
    private int ID;
    @ColumnInfo(name = "studentName")
    private String studentName;
    @ColumnInfo(name = "mobile")
    private String mobile;
    @ColumnInfo(name = "studentCourse")
    private String studentCourse;

  /*  public StudentModel(String studentName, String mobile, String studentCourse) {
        this.studentName = studentName;
        this.mobile = mobile;
        this.studentCourse = studentCourse;
    }*/

    @NotNull
    public int getID() {
        return ID;
    }

    public void setID(@NotNull int ID) {
        this.ID = ID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStudentCourse() {
        return studentCourse;
    }

    public void setStudentCourse(String studentCourse) {
        this.studentCourse = studentCourse;
    }
}
