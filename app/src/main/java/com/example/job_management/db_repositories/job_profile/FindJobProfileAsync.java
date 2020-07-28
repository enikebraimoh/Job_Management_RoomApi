package com.example.job_management.db_repositories.job_profile;

import android.content.Context;
import android.os.AsyncTask;

import com.example.job_management.data_models.JobProfile;
import com.example.job_management.db_operations.Connections;
import com.example.job_management.db_operations.JobProfileDao;
import com.example.job_management.db_repositories.AsyncTaskCallback;

public class FindJobProfileAsync extends AsyncTask<String,Void, JobProfile> {

    private Context context;
    private AsyncTaskCallback<JobProfile> callback;
    private Exception exception;

    private JobProfileDao jobProfileDao;
    private String email;

    public FindJobProfileAsync( String email, Context context, AsyncTaskCallback<JobProfile> callback) {

        this.context = context;
        this.callback = callback;
        this.email = email;
    }



    @Override
    protected JobProfile doInBackground(String... params) {

        exception = null;
        JobProfile jobProfile = null;

        try
        {
            jobProfileDao = Connections
                    .getInstance(context)
                    .getDatabase()
                    .getJobProfileDao();

            jobProfile = jobProfileDao.getJobProfileByEmail(this.email);

            if(jobProfile == null)
            {
                throw new Exception("Job Profile does not exist!");
            }
        }
        catch (Exception e)
        {
            exception =e;
        }
        return jobProfile;
    }

    @Override
    protected void onPostExecute(JobProfile s) {
        super.onPostExecute(s);

        if (callback != null)
        {
            if (exception == null)
            {
                callback.onSuccess(s);
            }
            else
            {
                callback.onException(exception);
            }
        }
    }
}
