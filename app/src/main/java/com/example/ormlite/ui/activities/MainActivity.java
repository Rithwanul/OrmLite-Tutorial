package com.example.ormlite.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.ormlite.R;
import com.example.ormlite.data.manager.StudentDataManager;
import com.example.ormlite.data.models.Student;
import com.example.ormlite.utils.AppConstants;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private StudentDataManager studentDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        studentDataManager = StudentDataManager.getInstance(this);

        studentDataManager.clearAllStudentData();

        int result = studentDataManager.insertStudent(new Student("Rithwanul", "Dico", "Islam", "Uttara"));

        if (result == 1){
            Log.d(AppConstants.TAG, "Student Inserted");
        }else {
            Log.d(AppConstants.TAG, "Student not Inserted");
        }


        List<Student> allStudents = studentDataManager.getAllStudents();

        for (Student student : allStudents){
            Log.d(AppConstants.TAG, "Student Id Number : " + student.getId());
            Log.d(AppConstants.TAG, "Student First Name : " + student.getFirstName());
            Log.d(AppConstants.TAG, "Student Last Name : " + student.getLastName());
            Log.d(AppConstants.TAG, "Student Middle Name : " + student.getMiddleName());
            Log.d(AppConstants.TAG, "Student Address Name : " + student.getAddress());
        }

    }
}