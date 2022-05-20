package com.example.homefirstdemo.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Entity(tableName = "user")
public class UserModel {

    @PrimaryKey(autoGenerate = true)
    @NotNull
    private int id;
    @ColumnInfo(name = "username")
    private String username;
    @ColumnInfo(name = "mobile")
    private String mobile;
    @ColumnInfo(name = "course")
    private String course;

    public UserModel(String username, String mobile, String course) {
        this.username = username;
        this.mobile = mobile;
        this.course = course;
    }

    @NotNull
    public int getId() {
        return id;
    }

    public void setId(@NotNull int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

}
