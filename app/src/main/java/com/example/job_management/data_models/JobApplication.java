package com.example.job_management.data_models;

import androidx.room.Entity;


@Entity(tableName = "JobApplication")
public class JobApplication {

    private long jobId;
    private long profileId;

}
