package com.example.job_management.db_repositories.job_profile;

import android.content.Context;
import android.os.AsyncTask;

import com.example.job_management.data_models.JobProfile;
import com.example.job_management.db_operations.Connections;
import com.example.job_management.db_repositories.AsyncTaskCallback;

import java.util.List;

public class GetAllJobProfilesAsync extends AsyncTask<Integer, Void, List<JobProfile>> {

    private Context context;
    private AsyncTaskCallback<List<JobProfile>> callback;
    private Exception exception;

    public GetAllJobProfilesAsync(Context context, AsyncTaskCallback<List<JobProfile>> callback) {
        this.context = context;
        this.callback = callback;
    }

    @Override
    protected List<JobProfile> doInBackground(Integer... integers) {

        exception = null;
        List<JobProfile> jobProfiles = null;

        try {
            jobProfiles = Connections.getInstance(this.context)
                    .getDatabase()
                    .getJobProfileDao()
                    .getAllJobProfiles();

            if (jobProfiles.size() == 0) {
                throw new Exception("No data found!");
            }
        } catch (Exception e) {
            exception = e;
        }
        return jobProfiles;
    }

    @Override
    protected void onPostExecute(List<JobProfile> jobProfiles) {
        super.onPostExecute(jobProfiles);

        if(callback != null)
        {
            if(exception == null)
            {
                callback.onSuccess(jobProfiles);
            }
            else
            {
                callback.onException(exception);
            }
        }
    }
}