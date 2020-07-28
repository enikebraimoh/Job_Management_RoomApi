package com.example.job_management.db_repositories.job_advert;

import android.content.Context;
import android.os.AsyncTask;

import com.example.job_management.data_models.JobAdvert;
import com.example.job_management.data_models.JobProfile;
import com.example.job_management.db_operations.AdvertConnection;
import com.example.job_management.db_operations.Connections;
import com.example.job_management.db_repositories.AsyncTaskCallback;

public class InsertJobAdvertAsync extends AsyncTask<Integer, Void, JobAdvert> {

    private Context context;
    private AsyncTaskCallback<JobAdvert> callback;
    private Exception exception;
    private JobAdvert jobAdvert;

    public InsertJobAdvertAsync(JobAdvert jobAdvert, Context context, AsyncTaskCallback<JobAdvert> callback)
    {
        this.context = context;
        this.callback = callback;
        this.jobAdvert = jobAdvert;
    }

    @Override
    protected JobAdvert doInBackground(Integer... integers) {

        exception = null;

        try
        {
            JobAdvert jobAdvert = AdvertConnection
                    .getInstance(context)
                    .getDatabase()
                    .getJobAdvertDao()
                    .getJobByJobTitle(this.jobAdvert.getJobTitle());


            if(jobAdvert == null)
            {
                AdvertConnection.getInstance(context)
                        .getDatabase()
                        .getJobAdvertDao()
                        .insert(this.jobAdvert);
            }

            else
            {
                throw new Exception("Job Advert already exists!");
            }
        }
        catch (Exception e)
        {
            exception = e;
        }

        return this.jobAdvert;
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
