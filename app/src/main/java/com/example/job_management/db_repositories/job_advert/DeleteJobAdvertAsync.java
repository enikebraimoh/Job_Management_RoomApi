package com.example.job_management.db_repositories.job_advert;

import android.content.Context;
import android.os.AsyncTask;

import com.example.job_management.data_models.JobAdvert;
import com.example.job_management.data_models.JobProfile;
import com.example.job_management.db_operations.AdvertConnection;
import com.example.job_management.db_operations.Connections;
import com.example.job_management.db_repositories.AsyncTaskCallback;

public class DeleteJobAdvertAsync extends AsyncTask<Integer, Void, JobAdvert> {

    private Context context;
    private AsyncTaskCallback<JobAdvert> callback;
    private Exception exception;
    private JobAdvert jobAdvert;

    public DeleteJobAdvertAsync(Context context, JobAdvert jobAdvert, AsyncTaskCallback<JobAdvert> callback) {
        this.context = context;
        this.callback = callback;
        this.jobAdvert = jobAdvert;
    }

    @Override
    protected JobAdvert doInBackground(Integer... integers) {

        exception = null;

        try
        {
            AdvertConnection.getInstance(this.context)
                    .getDatabase()
                    .getJobAdvertDao()
                    .delete(this.jobAdvert);
        }
        catch (Exception e)
        {
            exception = e;
        }
        return this.jobAdvert;
    }
}