package com.example.job_management.db_repositories.job_advert;

import android.content.Context;
import android.os.AsyncTask;

import com.example.job_management.data_models.JobAdvert;
import com.example.job_management.data_models.JobProfile;
import com.example.job_management.db_operations.AdvertConnection;
import com.example.job_management.db_operations.Connections;
import com.example.job_management.db_repositories.AsyncTaskCallback;

import java.util.List;

public class GetAllJobAdvertsAsync extends AsyncTask<Integer, Void, List<JobAdvert>> {

    private Context context;
    private AsyncTaskCallback<List<JobAdvert>> callback;
    private Exception exception;

    public GetAllJobAdvertsAsync(Context context, AsyncTaskCallback<List<JobAdvert>> callback) {
        this.context = context;
        this.callback = callback;
    }

    @Override
    protected List<JobAdvert> doInBackground(Integer... integers) {

        exception = null;
        List<JobAdvert> jobAdvert = null;

        try {
            jobAdvert = AdvertConnection.getInstance(this.context)
                    .getDatabase()
                    .getJobAdvertDao()
                    .getAllJobAdverts();

            if (jobAdvert.size() == 0) {
                throw new Exception("No data found!");
            }
        } catch (Exception e) {
            exception = e;
        }
        return jobAdvert;
    }



    @Override
    protected void onPostExecute(List<JobAdvert> jobAdverts) {
        super.onPostExecute(jobAdverts);

        if (callback != null)
        {
            if (exception == null)
            {
                callback.onSuccess(jobAdverts);
            }
            else
            {
                callback.onException(exception);
            }
        }
    }

}
