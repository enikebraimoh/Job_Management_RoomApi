package com.example.job_management.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.job_management.R;
import com.example.job_management.data_models.JobAdvert;
import com.example.job_management.data_models.JobProfile;
import com.example.job_management.db_operations.Connections;
import com.example.job_management.db_operations.JobProfileDao;
import com.example.job_management.db_repositories.AsyncTaskCallback;
import com.example.job_management.db_repositories.job_advert.FindJobAdvertAsync;
import com.example.job_management.db_repositories.job_advert.InsertJobAdvertAsync;
import com.example.job_management.db_repositories.job_advert.UpdateJobAdvertAsync;
import com.example.job_management.db_repositories.job_profile.FindJobProfileAsync;
import com.example.job_management.db_repositories.job_profile.GetAllJobProfilesAsync;
import com.example.job_management.db_repositories.job_profile.UpdateJobProfileAsync;

import java.util.List;

public class JobDetailActivity extends AppCompatActivity
{
    AutoCompleteTextView edtjobTitle, edtAppType, edtJobPosition, edtJobLocation, edtAdCompany, edtJobDesc,edtMinQualification,edtGrossSalary;
    Context mContext;
    Button btnupdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);

        Intent getIntent = getIntent();
        String ajob = getIntent.getStringExtra("jobtitle");

        edtjobTitle = findViewById(R.id.edtjobtitle);
        edtAppType = findViewById(R.id.edtAppType);
        edtJobPosition = findViewById(R.id.edtJobPosition);
        edtJobLocation = findViewById(R.id.edtJobLocation);
        edtAdCompany = findViewById(R.id.edtAdCompany);
        edtJobDesc = findViewById(R.id.edtJobDesc);
        edtMinQualification = findViewById(R.id.edtMinQualification);
        edtGrossSalary = findViewById(R.id.edtGrossSalary);

        btnupdate = findViewById(R.id.btnupdatee);

        edtjobTitle.setEnabled(false);


        new FindJobAdvertAsync(ajob,JobDetailActivity.this,
                new AsyncTaskCallback<JobAdvert>() {
                    @Override
                    public void onSuccess(JobAdvert response) {

                        edtjobTitle.setText(response.getJobTitle());
                        edtAppType.setText(response.getAppointmentType());
                        edtJobPosition.setText(response.getJobPosition());
                        edtJobLocation.setText(response.getJobLocation());
                        edtAdCompany.setText(response.getAdvertisingCompany());
                        edtJobDesc.setText(response.getJobDescription());
                        edtMinQualification.setText(response.getJobQualification());
                        edtGrossSalary.setText(response.getJobSalary());
                    }

                    @Override
                    public void onException(Exception e) {
                        showToast("Error: " + e.getMessage());
                    }
                }).execute();



        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JobAdvert jobAdvert = new JobAdvert();

                jobAdvert.setJobTitle(edtjobTitle.getText().toString());
                jobAdvert.setAppointmentType(edtAppType.getText().toString());
                jobAdvert.setJobPosition(edtJobPosition.getText().toString());
                jobAdvert.setJobLocation(edtJobLocation.getText().toString());
                jobAdvert.setAdvertisingCompany(edtAdCompany.getText().toString());
                jobAdvert.setJobDescription(edtJobDesc.getText().toString());
                jobAdvert.setJobQualification(edtMinQualification.getText().toString());
                jobAdvert.setJobSalary(edtGrossSalary.getText().toString());

                new UpdateJobAdvertAsync(getApplicationContext(),jobAdvert,

                        new AsyncTaskCallback<JobAdvert>() {
                            @Override
                            public void onSuccess(JobAdvert response) {
                                showToast(" Your Job Adverts has being Updated");
                                Intent intent = new Intent (JobDetailActivity.this,ListAdvertActivity.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onException(Exception e) {
                                showToast("Error: " + e.getMessage());
                            }
                        }).execute();
            }
        });





    }
    public void showToast(String message) {

        View toastView = getLayoutInflater().inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.lin_lay));
        TextView tvToast = (TextView) toastView.findViewById(R.id.tvToast);
        tvToast.setText(message);
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(toastView);
        toast.setGravity(Gravity.BOTTOM | Gravity.FILL_HORIZONTAL, 0, 0);
        toast.show();
    }
}