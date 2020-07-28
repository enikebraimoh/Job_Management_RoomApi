package com.example.job_management.db_operations;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.job_management.data_models.JobProfile;

import java.util.List;

import kotlinx.coroutines.Job;

@Dao
public interface JobProfileDao {

    @Query("DELETE FROM JobProfile WHERE id = :id")
    void delete(long id);

    @Query("SELECT * FROM JobProfile order by email")
    List<JobProfile> getAllJobProfiles();

    @Query("SELECT * FROM JobProfile WHERE id = :id ")
    JobProfile getJobProfileById(long id);

    @Query("SELECT * FROM JobProfile WHERE email = :email")
    JobProfile getJobProfileByEmail(String email);

    //Check document if these are available

    @Insert
    void insert (JobProfile jobProfile);

    @Delete
    void delete(JobProfile jobProfile);


    @Update
    void Update(JobProfile jobProfile);

}
