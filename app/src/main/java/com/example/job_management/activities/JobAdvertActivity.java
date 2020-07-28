package com.example.job_management.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.job_management.R;
import com.example.job_management.data_models.JobAdvert;
import com.example.job_management.data_models.JobProfile;
import com.example.job_management.db_repositories.AsyncTaskCallback;
import com.example.job_management.db_repositories.job_advert.InsertJobAdvertAsync;
import com.example.job_management.db_repositories.job_profile.InsertJobProfileAsync;

import kotlinx.coroutines.Job;


public class JobAdvertActivity extends AppCompatActivity {

    AutoCompleteTextView edtjobTitle = null, edtAppType = null, edtJobPosition = null, edtJobLocation = null, edtAdCompany = null, edtJobDesc=null,edtMinQualification = null,edtGrossSalary= null;

    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_advert);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Add Job Advert");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.back);
        btnSave = findViewById(R.id.btnSave);


        edtjobTitle = findViewById(R.id.edtjobtitle);
        edtAppType = findViewById(R.id.edtAppType);
        edtJobPosition = findViewById(R.id.edtJobPosition);
        edtJobLocation = findViewById(R.id.edtJobLocation);
        edtAdCompany = findViewById(R.id.edtAdCompany);
        edtJobDesc = findViewById(R.id.edtJobDesc);
        edtMinQualification = findViewById(R.id.edtMinQualification);
        edtGrossSalary = findViewById(R.id.edtGrossSalary);


        btnSave.setOnClickListener(new View.OnClickListener() {
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

                new InsertJobAdvertAsync(jobAdvert, getApplicationContext(),

                        new AsyncTaskCallback<JobAdvert>() {
                            @Override
                            public void onSuccess(JobAdvert response) {
                                showToast(" Your Job Adverts has being saved");
                                Intent intent = new Intent (JobAdvertActivity.this,ListAdvertActivity.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onException(Exception e) {
                                showToast("Error: " + e.getMessage());
                            }
                        }).execute();

//                Intent intent = new Intent(JobAdvertActivity.this, JobDetailActivity.class);
//                startActivity(intent);
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