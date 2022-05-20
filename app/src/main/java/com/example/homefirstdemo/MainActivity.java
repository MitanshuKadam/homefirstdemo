package com.example.homefirstdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homefirstdemo.DAO.UserDAO;
import com.example.homefirstdemo.DAO.UserDatabase;
import com.example.homefirstdemo.Model.UserModel;

public class MainActivity extends AppCompatActivity {
    UserDAO db;
    UserDatabase database;
    TextView tv_register;
    EditText edt_userName, edt_mobile;
    Button btn_submit;
    Activity activity;
    String str_userName,str_mobile;

    public static final String SHARED_PREF= "shared_prefs";
    public static final String USERNAME_KEY = "username_key";
    public static final String MOBILE_KEY = "mobile_key";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        database = Room.databaseBuilder(this, UserDatabase.class, "User")
                .allowMainThreadQueries()
                .build();

        db = database.getUserDAO();

        setUI();
        setListener();

    }

    private void setListener() {
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity, RegisterActivity.class));
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_userName = edt_userName.getText().toString().trim();
                str_mobile = edt_mobile.getText().toString().trim();

                UserModel userModel = db.getUser(str_userName,str_mobile);

                if (userModel!=null){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(USERNAME_KEY,str_userName);
                    editor.putString(MOBILE_KEY,str_mobile);
                    editor.apply();

                    Intent i = new Intent(activity,UserListActivity.class);
                    i.putExtra(str_userName,Constants.USERNAME);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(activity, Constants.INVALID_USER, Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void setUI() {
        tv_register = findViewById(R.id.tv_register);
        edt_userName = findViewById(R.id.edt_userName);
        edt_mobile = findViewById(R.id.edt_mobile);
        btn_submit = findViewById(R.id.btn_submit);

        sharedPreferences = getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        str_userName = sharedPreferences.getString(USERNAME_KEY,null);
        str_mobile = sharedPreferences.getString(MOBILE_KEY,null);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (str_userName!=null && str_mobile!=null){
            Intent i = new Intent(MainActivity.this,UserListActivity.class);
            startActivity(i);
        }
    }
}