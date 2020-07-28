package com.example.job_management.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.job_management.R;
import com.example.job_management.app_utility.AppUtility;
import com.example.job_management.data_models.JobProfile;
import com.example.job_management.db_operations.Connections;
import com.example.job_management.db_repositories.AsyncTaskCallback;
import com.example.job_management.db_repositories.job_profile.FindJobProfileAsync;
import com.example.job_management.db_repositories.job_profile.InsertJobProfileAsync;
import com.example.job_management.db_repositories.job_profile.UpdateJobProfileAsync;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class LoginActivity extends AppCompatActivity {


    TextInputEditText edtEmail, edtPassword;

    Switch switchLoggedIn;
    Button btnRegister, btnLogin;
    JobProfile jobProfile;

    // Constants
    public static final String USER_PREFS = "sameUser";
    public static final String DISPLAY_NAME_KEY = "username";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.edtEmail);

        edtPassword = findViewById(R.id.edtPassword);

        btnLogin = findViewById(R.id.btnLogin);

        btnRegister = findViewById(R.id.btnRegister);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view)
            {

                if(edtEmail.getText().toString().trim().isEmpty() || edtPassword.getText().toString().trim().isEmpty())
                {
                    showToast("Enter all fields!");
                }

                else
                {
                    String emailstring = edtEmail.getText().toString();
                    new FindJobProfileAsync(emailstring, LoginActivity.this,
                            new AsyncTaskCallback<JobProfile>() {
                                @Override
                                public void onSuccess(JobProfile response) {

                                    if(edtEmail.getText().toString().equals(response.getEmail()) &&  edtPassword.getText().toString().equals(response.getPassword()) ){

                                        SaveDisplayName();
                                        Intent intent = new Intent(LoginActivity.this,ListAdvertActivity.class);
                                        startActivity(intent);
                                    }else{

                                        showToast("no user found");

                                    }
                                }

                                @Override
                                public void onException(Exception e) {
                                    showToast("Error: " + e.getMessage());
                                }
                            }).execute();




                }

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

    public void SaveDisplayName(){
        String DisplayName = edtEmail.getText().toString();
        SharedPreferences pref = getSharedPreferences(USER_PREFS,0);
        pref.edit().putString(DISPLAY_NAME_KEY,DisplayName).apply();
    }
}