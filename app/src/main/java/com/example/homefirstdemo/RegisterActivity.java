package com.example.homefirstdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.homefirstdemo.DAO.UserDAO;
import com.example.homefirstdemo.DAO.UserDatabase;
import com.example.homefirstdemo.Model.UserModel;

public class RegisterActivity extends AppCompatActivity {

    EditText edt_reg_username,edt_reg_mobile,edt_course;
    Button btn_register;
    UserDAO userDAO;
    String str_regUserName,str_regMobile,str_course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setUI();
        userDAO = Room.databaseBuilder(this, UserDatabase.class,"User").allowMainThreadQueries().build().getUserDAO();
        setListener();
    }

    private void setListener() {
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkData()){
                    str_course = edt_course.getText().toString().trim();
                    str_regMobile = edt_reg_mobile.getText().toString();
                    str_regUserName = edt_reg_username.getText().toString();

                    UserModel userModel= new UserModel(str_regUserName,str_regMobile,str_course);
                    userDAO.insert(userModel);
                    Intent i = new Intent(RegisterActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();

                }
            }
        });
    }

    private boolean checkData() {
        if (edt_reg_username.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, Constants.Name_Blank, Toast.LENGTH_SHORT).show();
            return false;
        }else if(edt_reg_username.getText().toString().length()<5){
            Toast.makeText(this, Constants.Name_length, Toast.LENGTH_SHORT).show();
            return false;
        }else if(edt_course.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, Constants.Course_Blank, Toast.LENGTH_SHORT).show();
            return false;
        }else if(edt_reg_mobile.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, Constants.Mobile_Blank, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void setUI() {
        edt_reg_username = findViewById(R.id.edt_reg_username);
        edt_reg_mobile = findViewById(R.id.edt_reg_mobile);
        edt_course = findViewById(R.id.edt_course);
        btn_register = findViewById(R.id.btn_register);
    }
}