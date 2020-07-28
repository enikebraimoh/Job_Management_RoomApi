package com.example.job_management.data_models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "JobAdvert")
public class JobAdvert {

    @ColumnInfo(name = "id")
    private long id;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "jobTitle")
    private String jobTitle;

    @ColumnInfo(name = "jobSalary")
    private String jobSalary;

    @ColumnInfo(name = "jobLocation")
    private String jobLocation;

    @ColumnInfo(name = "appointmentType")
    private String appointmentType;

    @ColumnInfo(name = "jobPosition")
    private String jobPosition;

    @ColumnInfo(name = "jobDescription")
    private String jobDescription;

    @ColumnInfo (name = "advertisingCompany")
    private String AdvertisingCompany;


    @ColumnInfo(name = "jobQualification")
    private String jobQualification;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobSalary() {
        return jobSalary;
    }

    public void setJobSalary(String jobSalary) {
        this.jobSalary = jobSalary;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getAdvertisingCompany() {
        return AdvertisingCompany;
    }

    public void setAdvertisingCompany(String advertisingCompany) {
        AdvertisingCompany = advertisingCompany;
    }

    public String getJobQualification() {
        return jobQualification;
    }

    public void setJobQualification(String jobQualification) {
        this.jobQualification = jobQualification;
    }
}
