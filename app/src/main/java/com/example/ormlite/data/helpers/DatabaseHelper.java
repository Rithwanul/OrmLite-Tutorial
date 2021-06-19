package com.example.ormlite.data.helpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.ormlite.data.models.Student;
import com.example.ormlite.utils.AppConstants;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 *  This class creates and drops database table and connections
 *  using ORMLITE library.
 *
 * @author T.M. Rithwanul Islam
 * @company RM technologies llc.
 * */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    // Dao to access data from database
    private Dao<Student, Long> studentDao;

    public DatabaseHelper(Context context) {
        super(context, AppConstants.DATABASE_NAME, null, AppConstants.DATABASE_VERSION);
        //getWritableDatabase();
    }

    /**
     *  Default create method from ORMLITE
     *  for creating table.
     * */
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Student.class);
        } catch (SQLException e) {
            Log.d(AppConstants.TAG, "Failed to create database. Error : " + e.getMessage());
        }
    }


    /**
     *  Default drop method from ORMLITE database
     *  for dropping table
     * */
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            //if (checkTableExists(database, AppConstants.STUDENT_TABLE_NAME))
                TableUtils.dropTable(connectionSource, Student.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            Log.d(AppConstants.TAG, "Failed to drop database. Error : " +e.getMessage());
        }
    }

    /**
     *  Checks whether a table exists in the
     *  database or not.
     *
     * @param database database name
     * @param tableName table name
     * @return returns table exists or not; true or false
     * */
    private boolean checkTableExists(SQLiteDatabase database, String tableName){
        Cursor c = null;
        boolean tableExits = false;

        try{
            c = database.query(tableName, null, null, null, null, null, null);
            tableExits = true;
        }catch (Exception e){
            Log.d(AppConstants.TAG, "Table not exists. Error : " + e.getMessage());
        }
        return tableExits;
    }

    /**
     * DAO data access object for accessing database
     * */
    public Dao<Student, Long> getStudentDao() throws SQLException {
        if (studentDao == null){
            studentDao = getDao(Student.class);
        }
        return studentDao;
    }

    /**
     *  Delete all database tables
     * */
    public void clearDatabase() throws SQLException {
        TableUtils.clearTable(getConnectionSource(), Student.class);
    }

    /**
     * Closing database connection.
     * */
    @Override
    public void close() {
        studentDao = null;
        super.close();
    }
}
