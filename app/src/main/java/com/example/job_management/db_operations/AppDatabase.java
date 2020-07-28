package com.example.job_management.db_operations;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.job_management.data_models.JobProfile;

@Database(entities = {JobProfile.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract JobProfileDao getJobProfileDao();
}
