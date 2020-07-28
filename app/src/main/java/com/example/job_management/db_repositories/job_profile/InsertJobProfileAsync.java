package com.example.job_management.db_repositories.job_profile;

import android.content.Context;
import android.os.AsyncTask;

import com.example.job_management.data_models.JobProfile;
import com.example.job_management.db_operations.Connections;
import com.example.job_management.db_repositories.AsyncTaskCallback;

/* Check out this class on the tutorials */
public class InsertJobProfileAsync extends AsyncTask<Integer, Void, JobProfile> {

    private Context context;
    private AsyncTaskCallback<JobProfile> callback;
    private Exception exception;
    private JobProfile jobProfile;

    public InsertJobProfileAsync (JobProfile jobProfile,Context context, AsyncTaskCallback<JobProfile> callback)
    {
        this.context = context;
        this.callback = callback;
        this.jobProfile = jobProfile;
    }

    @Override
    protected JobProfile doInBackground(Integer... integers) {

        exception = null;

        try
        {
            JobProfile jobProfile = Connections
                    .getInstance(context)
                    .getDatabase()
                    .getJobProfileDao()
                    .getJobProfileByEmail(this.jobProfile.getEmail());

                    //.getJobProfileById(this.jobProfile.getId())

            if(jobProfile == null)
            {
                Connections.getInstance(context)
                        .getDatabase()
                        .getJobProfileDao()
                        .insert(this.jobProfile);
            }

            else
            {
                throw new Exception("Job Profile already exists!");
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
