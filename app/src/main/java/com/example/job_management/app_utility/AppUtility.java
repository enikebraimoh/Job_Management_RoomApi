package com.example.job_management.app_utility;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Toast;

import com.example.job_management.data_models.JobProfile;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;


public class AppUtility extends Application {

    public static String qualification, field;
    public static SharedPreferences preferences;
    public static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z])(?=.*[@#$^&%+=])(?=\\S+$).{6,20}$");

    @Override
    public void onCreate() {
        super.onCreate();
        preferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);
    }

    public static void checkValidation(String [] errors, TextInputLayout... textInputLayouts)
    {
        textInputLayouts = null;
        for (int i = 0; i < errors.length; i++)
        {
            textInputLayouts[i].setError(errors[i]);
        }

    }




}
