package com.example.job_management.db_repositories.job_advert;

import android.content.Context;
import android.os.AsyncTask;

import com.example.job_management.data_models.JobAdvert;
import com.example.job_management.data_models.JobProfile;
import com.example.job_management.db_operations.AdvertConnection;
import com.example.job_management.db_operations.Connections;
import com.example.job_management.db_repositories.AsyncTaskCallback;

import kotlinx.coroutines.Job;

/* Check out this class on the tutorials */
public class UpdateJobAdvertAsync extends AsyncTask<Integer, Void, JobAdvert> {

    private Context context;
    private AsyncTaskCallback<JobAdvert> callback;
    private Exception exception;
    private JobAdvert jobAdvert;

    public UpdateJobAdvertAsync(Context context,JobAdvert jobAdvert, AsyncTaskCallback<JobAdvert> callback ) {
        this.context = context;
        this.callback = callback;
        this.jobAdvert = jobAdvert;

    }


    @Override
    protected JobAdvert doInBackground(Integer... integers) {

        exception = null;

        try
        {
            if(AdvertConnection.getInstance(this.context).getDatabase().getJobAdvertDao().getJobByJobTitle(this.jobAdvert.getJobTitle()) == null){

                throw new Exception("this user profile you are trying to update does not exist");
            }else{

                AdvertConnection.getInstance(this.context).getDatabase().getJobAdvertDao().Update(jobAdvert);
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

