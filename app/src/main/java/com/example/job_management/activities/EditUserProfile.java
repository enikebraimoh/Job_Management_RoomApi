package com.example.job_management.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.job_management.R;
import com.example.job_management.data_models.JobProfile;
import com.example.job_management.db_repositories.AsyncTaskCallback;
import com.example.job_management.db_repositories.job_profile.FindJobProfileAsync;
import com.example.job_management.db_repositories.job_profile.GetAllJobProfilesAsync;
import com.example.job_management.db_repositories.job_profile.UpdateJobProfileAsync;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class EditUserProfile extends AppCompatActivity {

    TextInputLayout lytEmail, lytPassword, lytConfirmPass, lytFirstName, lytSurname;
    // lytCellphone, lytIdNo;

    AutoCompleteTextView edtEmail, edtPassword, edtConfirmPass, edtFirstName, edtLastName,cell_phone,id_number,HQ,FOS;
    Button btnUpdate;
    TextView tvResults;
    String emailString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_profile);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Update Profile");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.back);
        SetUPDisplayName();


        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPass = findViewById(R.id.edtConfirmPassword);
        edtFirstName = findViewById(R.id.edtFirstName);
        edtLastName = findViewById(R.id.edtLastname);
        cell_phone = findViewById(R.id.edtCellphoneNo);
        id_number = findViewById(R.id.edtIDNo);
        HQ = findViewById(R.id.qualifications);
        FOS = findViewById(R.id.studyField);

        btnUpdate = findViewById(R.id.btnUpdate);

        edtEmail.setEnabled(false);


        new FindJobProfileAsync(emailString, EditUserProfile.this,
                new AsyncTaskCallback<JobProfile>() {
                    @Override
                    public void onSuccess(JobProfile response) {

                        edtEmail.setText(response.getEmail());
                        edtPassword.setText(response.getPassword());
                        edtConfirmPass.setText(response.getPassword());
                        edtFirstName.setText(response.getFirstName());
                        edtLastName.setText(response.getLastName());
                        cell_phone.setText(response.getCellphone_number());
                        id_number.setText(response.getIdentity_number());
                        HQ.setText(response.getHighest_qualification());
                        FOS.setText(response.getField_of_study());

                    }

                    @Override
                    public void onException(Exception e) {
                        showToast("Error: " + e.getMessage());
                    }
                }).execute();



        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JobProfile newJobProfile = new JobProfile();

                newJobProfile.setEmail(edtEmail.getText().toString());
                newJobProfile.setPassword(edtPassword.getText().toString());
                newJobProfile.setFirstName(edtFirstName.getText().toString());
                newJobProfile.setLastName(edtLastName.getText().toString());
                newJobProfile.setCellphone_number(cell_phone.getText().toString());
                newJobProfile.setIdentity_number(id_number.getText().toString());
                newJobProfile.setHighest_qualification(HQ.getText().toString());
                newJobProfile.setField_of_study(FOS.getText().toString());



                new UpdateJobProfileAsync( EditUserProfile.this,newJobProfile,
                        new AsyncTaskCallback<JobProfile>() {
                            @Override
                            public void onSuccess(JobProfile response) {
                                showToast("Profile Updated Successfully");
                               finish();
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

    public void SetUPDisplayName(){
        SharedPreferences preferences = getSharedPreferences(LoginActivity.USER_PREFS,MODE_PRIVATE);
        emailString  =  preferences.getString(LoginActivity.DISPLAY_NAME_KEY,null);
    }

}