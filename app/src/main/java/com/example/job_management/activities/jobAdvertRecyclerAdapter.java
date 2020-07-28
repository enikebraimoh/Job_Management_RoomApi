package com.example.job_management.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.job_management.R;
import com.example.job_management.data_models.JobAdvert;
import com.example.job_management.data_models.JobProfile;
import com.example.job_management.db_repositories.AsyncTaskCallback;
import com.example.job_management.db_repositories.job_advert.DeleteJobAdvertAsync;
import com.example.job_management.db_repositories.job_advert.FindJobAdvertAsync;
import com.example.job_management.db_repositories.job_advert.GetAllJobAdvertsAsync;
import com.example.job_management.db_repositories.job_profile.FindJobProfileAsync;

import java.util.ArrayList;
import java.util.List;

import kotlin.reflect.KVisibility;


public class jobAdvertRecyclerAdapter extends RecyclerView.Adapter<SingleListItemView> {

Context mContext;
List<JobAdvert> ads;
    View view;


    public jobAdvertRecyclerAdapter(Context context, List<JobAdvert> ads) {
        mContext = context;
        this.ads = ads;
    }

    @NonNull
    @Override
    public SingleListItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view  = layoutInflater.inflate(R.layout.single_list_item,null);

        return new SingleListItemView(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final SingleListItemView holder, final int position) {

        holder.Jobtitle.setText(ads.get(position).getJobTitle());
        holder.company.setText(ads.get(position).getAdvertisingCompany());
        holder.location.setText(ads.get(position).getJobLocation());
        holder.school.setText(ads.get(position).getJobQualification());
        holder.salary.setText(ads.get(position).getJobSalary());
        holder.r.setText("R");



        holder.image1.setImageResource(R.drawable.ic_location);
        holder.image2.setImageResource(R.drawable.ic_company);
        holder.image3.setImageResource(R.drawable.ic_school);
        holder.Image.setImageResource(R.drawable.ic_hiring_now);

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.mCardView.setVisibility(View.INVISIBLE);
                holder.mCardView1.setVisibility(View.VISIBLE);
            }
        });



        holder.btninfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               holder.mCardView.setVisibility(View.VISIBLE);
               holder.mCardView1.setVisibility(View.INVISIBLE);


                holder.Jobtitle1.setText(ads.get(position).getJobTitle());
                holder.company1.setText(ads.get(position).getAdvertisingCompany());
                holder.location1.setText(ads.get(position).getJobLocation());
                holder.school1.setText(ads.get(position).getJobQualification());
                holder.salary1.setText(ads.get(position).getJobSalary());
                holder.r1.setText("R");
                holder.apttype1.setText(ads.get(position).getAppointmentType());
                holder.desc1.setText(ads.get(position).getJobDescription());


            }
        });

        holder.btnlike2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                alert.setTitle("Please Confirm")
                        .setMessage("Are you sure you want to Withdraw IT job application?")
                        .setCancelable(false)
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                holder.btnlike2.setVisibility(View.INVISIBLE);
                                holder.btnlike.setVisibility(View.VISIBLE);

                            }
                        })

                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                holder.btnlike2.setVisibility(View.VISIBLE);
                                holder.btnlike.setVisibility(View.INVISIBLE);

                            }
                        }).show();


            }
        });


        holder.btnlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.btnlike2.setVisibility(View.VISIBLE);
                holder.btnlike.setVisibility(View.INVISIBLE);

            }
        });

        holder.btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(),JobDetailActivity.class);
                String job = holder.Jobtitle.getText().toString();
                intent.putExtra("jobtitle",job);
                mContext.startActivity(intent);


            }
        });

        holder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent NewIntent = new Intent(mContext,LoginActivity.class);
                mContext.startActivity(NewIntent);

                Toast.makeText(mContext, "Deleted Successfully", Toast.LENGTH_SHORT).show();

                new DeleteJobAdvertAsync(mContext, ads.get(position),
                        new AsyncTaskCallback<JobAdvert>() {
                            @Override
                            public void onSuccess(JobAdvert response) {


                            }

                            @Override
                            public void onException(Exception e) {

                                Toast.makeText(mContext, "Unable to delete", Toast.LENGTH_SHORT).show();

                            }
                        }).execute();

            }
        });

    }



    @Override
    public int getItemCount() {
    return ads.size();
    }



}
