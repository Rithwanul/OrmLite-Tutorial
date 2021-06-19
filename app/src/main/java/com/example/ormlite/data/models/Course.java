package com.example.ormlite.data.models;

import com.example.ormlite.utils.AppConstants;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.UUID;

@DatabaseTable(tableName = AppConstants.COURSE_TABLE_NAME)
public class Course {

    @DatabaseField(columnName = "id", generatedId = true, canBeNull = false)
    private UUID id;

    @DatabaseField(columnName = "course_name", canBeNull = false)
    private String courseName;

    @DatabaseField(columnName = "faculty_name")
    private String facultyName;

    @DatabaseField(columnName = "student_id", foreign = true)
    private Student studentId;

    public Course(){}

    public UUID getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }
}
