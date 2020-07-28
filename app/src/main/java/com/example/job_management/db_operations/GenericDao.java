package com.example.job_management.db_operations;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

public interface GenericDao<T> {

    @Insert
    void insertProfile(T...obj);

    @Update
    void updateProfile(T...obj);

    @Delete
    void deleteProfile(T...obj);
}
