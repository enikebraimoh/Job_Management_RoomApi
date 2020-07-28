package com.example.job_management.data_models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "JobProfile")
public class JobProfile {


    @ColumnInfo(name = "id")
    private long id;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "firstName")
    private String firstName;

    @ColumnInfo(name = "lastName")
    private String lastName;

    @ColumnInfo(name = "cellphoneNumber")
    private String cellphone_number;

    @ColumnInfo(name = "identityNumber")
    private String identity_number;

    @ColumnInfo(name = "HighestQualification")
    private String Highest_qualification;

    @ColumnInfo(name = "FieldOfStudy")
    private String Field_of_study;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCellphone_number() {
        return cellphone_number;
    }

    public void setCellphone_number(String cellphone_number) {
        this.cellphone_number = cellphone_number;
    }

    public String getIdentity_number() {
        return identity_number;
    }

    public void setIdentity_number(String identity_number) {
        this.identity_number = identity_number;
    }

    public String getHighest_qualification() {
        return Highest_qualification;
    }

    public void setHighest_qualification(String highest_qualification) {
        Highest_qualification = highest_qualification;
    }

    public String getField_of_study() {
        return Field_of_study;
    }

    public void setField_of_study(String field_of_study) {
        Field_of_study = field_of_study;
    }
}
