package com.example.job_management.db_repositories.job_advert;

import android.content.Context;
import android.os.AsyncTask;

import com.example.job_management.data_models.JobAdvert;
import com.example.job_management.data_models.JobProfile;
import com.example.job_management.db_operations.AdvertConnection;
import com.example.job_management.db_operations.Connections;
import com.example.job_management.db_operations.JobAdvertDao;
import com.example.job_management.db_operations.JobProfileDao;
import com.example.job_management.db_repositories.AsyncTaskCallback;

public class FindJobAdvertAsync extends AsyncTask<String,Void, JobAdvert> {

    private Context context;
    private AsyncTaskCallback<JobAdvert> callback;
    private Exception exception;

    private JobAdvertDao jobAdvertDao;
    private String job;

    public FindJobAdvertAsync( String email, Context context, AsyncTaskCallback<JobAdvert> callback) {

        this.context = context;
        this.callback = callback;
        this.job = email;
    }



    @Override
    protected JobAdvert doInBackground(String... params) {

        exception = null;
        JobAdvert jobAdvert = null;

        try
        {
            jobAdvertDao = AdvertConnection
                    .getInstance(context)
                    .getDatabase()
                    .getJobAdvertDao();

            jobAdvert = jobAdvertDao.getJobByJobTitle(this.job);

            if(jobAdvert == null)
            {
                throw new Exception("Job Profile does not exist!");
            }
        }
        catch (Exception e)
        {
            exception =e;
        }
        return jobAdvert;
    }

    @Override
    protected void onPostExecute(JobAdvert s) {
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