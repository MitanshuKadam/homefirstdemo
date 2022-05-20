package com.example.homefirstdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.homefirstdemo.DAO.StudentDAO;
import com.example.homefirstdemo.DAO.UserDatabase;
import com.example.homefirstdemo.Model.StudentModel;

public class AddStudentActivity extends AppCompatActivity {
    EditText edt_add_Student, edt_add_mobile, edt_add_course;
    Button btn_add_student;
    StudentDAO db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        InITUI();
        setListener();
    }

    private void setListener() {

        btn_add_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkData()){
                    saveStudent(edt_add_Student.getText().toString().trim(),edt_add_course.getText().toString().trim(),edt_add_mobile.getText().toString().trim());
                }
            }
        });
    }

    private void saveStudent(String trim, String trim1, String trim2) {
        db = Room.databaseBuilder(this,UserDatabase.class,"student").allowMainThreadQueries().build().getAllStudent();
        StudentModel studentModel = new StudentModel();
        studentModel.setStudentCourse(trim1);
        studentModel.setMobile(trim2);
        studentModel.setStudentName(trim);
        db.insertStudent(studentModel);
//        finish();
        Intent intent = new Intent(AddStudentActivity.this,UserListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private boolean checkData() {
        if (edt_add_Student.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, Constants.Name_Blank, Toast.LENGTH_SHORT).show();
            return false;
        }else if(edt_add_Student.getText().toString().length()<5){
            Toast.makeText(this, Constants.Name_length, Toast.LENGTH_SHORT).show();
            return false;
        }else if(edt_add_course.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, Constants.Course_Blank, Toast.LENGTH_SHORT).show();
            return false;
        }else if(edt_add_mobile.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, Constants.Mobile_Blank, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void InITUI() {
        edt_add_course = findViewById(R.id.edt_add_course);
        edt_add_mobile = findViewById(R.id.edt_add_mobile);
        edt_add_Student = findViewById(R.id.edt_add_Student);
        btn_add_student = findViewById(R.id.btn_add_student);
    }
}