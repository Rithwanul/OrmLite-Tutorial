package com.example.ormlite.data.models;

import com.example.ormlite.utils.AppConstants;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.UUID;

/**
 *  This class holds the properties for any individual
 *  Student.
 *
 * @author T.M. Rithwanul Islam
 * @company RM technologies llc.
 *
 * */

@DatabaseTable(tableName = AppConstants.STUDENT_TABLE_NAME)
public class Student {

    @DatabaseField(columnName = "id", generatedId = true, canBeNull = false)
    private UUID id;

    @DatabaseField(columnName = "first_name", canBeNull = false)
    private String firstName;

    @DatabaseField(columnName = "middle_name", canBeNull = true)
    private String middleName;

    @DatabaseField(columnName = "last_name", canBeNull = false)
    private String lastName;

    @DatabaseField(columnName = "address", canBeNull = false)
    private String address;

    public Student(){}

    public Student(String firstName, String middleName, String lastName, String address){
        setFirstName(firstName);
        setMiddleName(middleName);
        setLastName(lastName);
        setAddress(address);
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
