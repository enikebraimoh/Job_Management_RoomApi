package com.example.job_management.db_repositories.job_profile;

import android.content.Context;
import android.os.AsyncTask;

import com.example.job_management.data_models.JobProfile;
import com.example.job_management.db_operations.Connections;
import com.example.job_management.db_repositories.AsyncTaskCallback;

public class UpdateJobProfileAsync extends AsyncTask<Integer, Void, JobProfile> {

    private Context context;
    private AsyncTaskCallback<JobProfile> callback;
    private Exception exception;
    private JobProfile jobProfile;

    public UpdateJobProfileAsync(Context context,JobProfile jobProfile, AsyncTaskCallback<JobProfile> callback ) {
        this.context = context;
        this.callback = callback;
        this.jobProfile = jobProfile;

    }


    @Override
    protected JobProfile doInBackground(Integer... integers) {

        exception = null;

        try
        {
            if(Connections.getInstance(this.context).getDatabase().getJobProfileDao().getJobProfileByEmail(this.jobProfile.getEmail()) == null){

              throw new Exception("this user profile you are trying to update does not exist");
            }else{

                Connections.getInstance(this.context).getDatabase().getJobProfileDao().Update(jobProfile);
            }

        }
        catch (Exception e)
        {
            exception = e;
        }
        return this.jobProfile;
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
