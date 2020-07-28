package com.example.job_management.db_repositories;

public interface AsyncTaskCallback<T> {
    void onSuccess(T response);
    void onException(Exception e);
}
