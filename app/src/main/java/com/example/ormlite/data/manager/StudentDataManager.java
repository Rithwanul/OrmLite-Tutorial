package com.example.ormlite.data.manager;

import android.content.Context;
import android.util.Log;

import com.example.ormlite.data.helpers.DatabaseHelper;
import com.example.ormlite.data.models.Student;
import com.example.ormlite.utils.AppConstants;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import java.util.List;
import java.util.UUID;

/**
 * This class holds method for student database operations
 *
 * @author T.M. Rithwanul Islam
 * @company RM technologies llc.
 * */
public class StudentDataManager {
    private final Context context;
    private DatabaseHelper databaseHelper;
    private Dao<Student, Long> studentDao;
    private static StudentDataManager INSTANCE;

    public StudentDataManager(Context context){
        this.context = context;
        databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        try{
            studentDao = databaseHelper.getStudentDao();
        }catch (Exception e){
            Log.d(AppConstants.TAG, "Do not get student dao");
        }
    }

    /**
     * Singleton instance for the student class
     *
     * @param context activity context
     * */
    public static StudentDataManager getInstance(Context context){
        if (INSTANCE == null)
            INSTANCE = new StudentDataManager(context);
        return INSTANCE;
    }

    /**
     * Release database connection with ormlite
     * */
    public void releaseDatabase(){
        if (databaseHelper != null){
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
            INSTANCE = null;
            studentDao = null;
        }
    }

    /**
     * Clear all records in the database
     * */
    public int clearAllStudentData(){
        try{
            if (databaseHelper == null) return -1;
            databaseHelper.clearDatabase();
            return 0;
        }catch (Exception e){
            Log.d(AppConstants.TAG, "Failed to clear all data. Error : " + e.getMessage());
            return -1;
        }
    }

    /**
     * Returns if record exists in the table
     *
     * @param id object primary key
     * */
    public boolean isStudentExists(UUID id){
        QueryBuilder queryBuilder = studentDao.queryBuilder();
        boolean flag = false;
        try{
            if (queryBuilder.where().eq(AppConstants.ID, id).countOf() > 0){
                flag = true;
            }
        }catch (Exception e){
            Log.d(AppConstants.TAG, "Student not exist. Error : " + e.getMessage());
        }
        return flag;
    }

    /**
     * Insert record in the table
     *
     * @param student student object to insert
     * */
    public int insertStudent(Student student){
        int result = 0;
        try{
            studentDao.create(student);
            result = 1;
        }catch (Exception e){
            Log.d(AppConstants.TAG, "Student data not inserted. Error : " + e.getMessage());
        }
        return result;
    }

    /**
     * Delete a specific record in the database
     *
     * @param id unique primary key assign to the record
     * */
    public int deleteStudent(UUID id){
        int result = 0;
        try{
            if (isStudentExists(id)){
                DeleteBuilder deleteBuilder = studentDao.deleteBuilder();
                deleteBuilder.where().eq(AppConstants.ID, id);
                deleteBuilder.delete();
                result = 1;
            }
        }catch (Exception e){
            Log.d(AppConstants.TAG, "Student Can't deleted. Error : " + e.getMessage());
        }
        return result;
    }

    /**
     * returns list of objects from the table
     * */
    public List<Student> getAllStudents(){
        try{
            if (studentDao == null)
                return null;
            return studentDao.queryForAll();
        }catch (Exception e){
            Log.d(AppConstants.TAG, "Error getting all users. Error : " + e.getMessage());
            return null;
        }
    }
}
