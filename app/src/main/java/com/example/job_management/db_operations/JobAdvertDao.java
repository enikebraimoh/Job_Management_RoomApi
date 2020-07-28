package com.example.job_management.db_operations;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.job_management.data_models.JobAdvert;
import com.example.job_management.data_models.JobProfile;

import java.util.List;

@Dao
public interface JobAdvertDao {

//    @Query("DELETE FROM JobProfile WHERE id = :id")
//    void delete(long id);

    @Query("SELECT * FROM JobAdvert order by jobTitle")
    List<JobAdvert> getAllJobAdverts();

    @Query("SELECT * FROM JobAdvert WHERE jobTitle = :jobTitle")
    JobAdvert getJobByJobTitle(String jobTitle);

    //Check document if these are available

    @Insert
    void insert (JobAdvert jobAdvert);

    @Delete
    void delete(JobAdvert jobAdvert);


    @Update
    void Update(JobAdvert jobAdvert);
}
