package com.example.job_management.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.job_management.R;
import com.example.job_management.data_models.JobAdvert;
import com.example.job_management.data_models.JobProfile;
import com.example.job_management.db_repositories.AsyncTaskCallback;
import com.example.job_management.db_repositories.job_advert.GetAllJobAdvertsAsync;
import com.example.job_management.db_repositories.job_profile.FindJobProfileAsync;

import java.util.ArrayList;
import java.util.List;

public class ListAdvertActivity extends AppCompatActivity {

    jobAdvertRecyclerAdapter mAdapter;
    List<JobAdvert> mJobAdverts = new ArrayList<>();
    RecyclerView AdvertRecycle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_advert);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Job Advert");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.back);
        AdvertRecycle = findViewById(R.id.recycle);


        AdvertRecycle.setLayoutManager(new LinearLayoutManager(ListAdvertActivity.this));
        AdvertRecycle.setHasFixedSize(true);

        new GetAllJobAdvertsAsync(ListAdvertActivity.this,
                new AsyncTaskCallback<List<JobAdvert>>() {
                    @Override
                    public void onSuccess(List<JobAdvert> response) {
                        mJobAdverts = response;
                        mAdapter = new jobAdvertRecyclerAdapter(ListAdvertActivity.this,mJobAdverts);
                        AdvertRecycle.setAdapter(mAdapter);
                    }

                    @Override
                    public void onException(Exception e) {
                        showToast("Error: " + e.getMessage());
                    }
                }).execute();





    }

    //Action bar code
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.addAd:
                startActivity(new Intent(ListAdvertActivity.this, JobAdvertActivity.class));
                finish();
                break;

            case R.id.favorite:
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                break;

            case R.id.editUser:
                startActivity(new Intent(ListAdvertActivity.this, EditUserProfile.class));
                break;

            case R.id.logout:
                ListAdvertActivity.this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
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