package com.example.job_management.db_repositories.job_profile;

import android.content.Context;
import android.os.AsyncTask;

import com.example.job_management.data_models.JobProfile;
import com.example.job_management.db_operations.Connections;
import com.example.job_management.db_repositories.AsyncTaskCallback;

public class DeleteJobProfileAsync extends AsyncTask<Integer, Void, JobProfile> {

    private Context context;
    private AsyncTaskCallback<JobProfile> callback;
    private Exception exception;
    private JobProfile jobProfile;

    public DeleteJobProfileAsync(Context context, AsyncTaskCallback<JobProfile> callback, JobProfile jobProfile) {
        this.context = context;
        this.callback = callback;
        this.jobProfile = jobProfile;
    }

    @Override
    protected JobProfile doInBackground(Integer... integers) {

        exception = null;

        try
        {
            Connections.getInstance(this.context)
                    .getDatabase()
                    .getJobProfileDao()
                    .delete(this.jobProfile);
        }
        catch (Exception e)
        {
            exception = e;
        }
        return this.jobProfile;
    }
}
