package com.example.job_management.db_operations;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.job_management.data_models.JobAdvert;
import com.example.job_management.data_models.JobProfile;

@Database(entities = {JobAdvert.class}, version = 1, exportSchema = false)
public abstract class AppAdvertDatabase extends RoomDatabase {

    public abstract JobAdvertDao getJobAdvertDao();
}
