package com.example.homefirstdemo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.homefirstdemo.DAO.StudentDAO;
import com.example.homefirstdemo.DAO.UserDAO;
import com.example.homefirstdemo.DAO.UserDatabase;
import com.example.homefirstdemo.Model.StudentModel;
import com.example.homefirstdemo.adapter.StudentListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class UserListActivity extends AppCompatActivity {
    RecyclerView rec_list;
    Button btn_logout;
    FloatingActionButton btn_fab;
    UserDAO db;
    UserDatabase database;
    StudentDAO DB;
    public static final String SHARED_PREF = "shared_prefs";
    public static final String USERNAME_KEY = "username_key";
    SharedPreferences sharedPreferences;
    String username;
    StudentListAdapter studentListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        database = Room.databaseBuilder(this, UserDatabase.class, "User")
                .allowMainThreadQueries()
                .build();

        db = database.getUserDAO();

      /*  database = UserDatabase.getInstance(this);
        database.getUserDAO();*/

        setUI();
        setListener();
        initRecyclerView();
        loadDataToAdapter();
    }

    private void loadDataToAdapter() {
        DB = Room.databaseBuilder(this, UserDatabase.class, "student").allowMainThreadQueries().build().getAllStudent();
        List<StudentModel> studentModelList = DB.getAllStudent();
        studentListAdapter.setStudentListAdapter(studentModelList);
    }

    private void setListener() {
        btn_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserListActivity.this, AddStudentActivity.class));
//                initRecyclerView();
            }
        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(UserListActivity.this);
                builder.setTitle("Logout")
                        .setMessage("Are you sure you want to LOGOUT")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.clear();
                                editor.apply();
                                Intent i = new Intent(UserListActivity.this, MainActivity.class);
                                startActivity(i);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
            }
        });
    }

    private void initRecyclerView() {

        studentListAdapter = new StudentListAdapter(this);
        rec_list.setAdapter(studentListAdapter);

        loadDataToAdapter();

    }

    private void setUI() {
        rec_list = findViewById(R.id.rec_list);
        btn_logout = findViewById(R.id.btn_logout);
        btn_fab = findViewById(R.id.btn_fab);

        initRecyclerView();

        sharedPreferences = getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        username = sharedPreferences.getString(USERNAME_KEY, null);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Close Application")
                .setMessage("Are you sure you want to close the application")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finishAffinity();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }
}