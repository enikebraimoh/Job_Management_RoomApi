package com.example.job_management.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.job_management.R;
import com.example.job_management.app_utility.AppUtility;
import com.example.job_management.data_models.JobProfile;
import com.example.job_management.db_operations.Connections;
import com.example.job_management.db_operations.JobProfileDao;
import com.example.job_management.db_repositories.AsyncTaskCallback;
import com.example.job_management.db_repositories.job_profile.InsertJobProfileAsync;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    //TextInputLayout lytEmail, lytPassword, lytConfirmPass, lytFirstName, lytSurname;
    AutoCompleteTextView edtEmail, edtPassword, edtConfirmPass, edtFirstName, edtLastName, cell_phone,id_number,HQ,FOS;

    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Register Profile");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.back);


        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPass = findViewById(R.id.edtConfirmPassword);
        edtFirstName = findViewById(R.id.edtFirstName);
        edtLastName = findViewById(R.id.edtLastname);
        cell_phone = findViewById(R.id.edtCellphoneNo);
        id_number = findViewById(R.id.edtIDNo);
        HQ = findViewById(R.id.qualifications);
        FOS = findViewById(R.id.studyField);


        btnRegister = findViewById(R.id.btnRegister);

        //call the registerUser method
        registerUser();

    }

    //save data in shared preferences onClick
    private void registerUser() {

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JobProfile jobProfile = new JobProfile();

                jobProfile.setEmail(edtEmail.getText().toString().trim());
                jobProfile.setPassword(edtPassword.getText().toString().trim());
                jobProfile.setFirstName(edtFirstName.getText().toString().trim());
                jobProfile.setLastName(edtLastName.getText().toString().trim());
                jobProfile.setCellphone_number(cell_phone.getText().toString().trim());
                jobProfile.setIdentity_number(id_number.getText().toString().trim());
                jobProfile.setHighest_qualification(HQ.getText().toString().trim());
                jobProfile.setField_of_study(FOS.getText().toString().trim());

                new InsertJobProfileAsync(jobProfile, getApplicationContext(),
                        new AsyncTaskCallback<JobProfile>() {
                            @Override
                            public void onSuccess(JobProfile response) {

                                showToast("Hi " + response.getFirstName() + " " +
                                        response.getLastName() + " your profile has been created!");
                                ProfileActivity.this.finish();
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