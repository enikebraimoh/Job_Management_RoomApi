package com.example.job_management.activities;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.job_management.R;

import org.w3c.dom.Text;

public class SingleListItemView extends RecyclerView.ViewHolder {

    TextView Jobtitle,location,company,school,salary,r;
    TextView Jobtitle1,location1,company1,school1,salary1,r1,apttype1,desc1;

    ImageView Image,image1,image2,image3,btninfo,btnedit,btndelete,btnlike,btnlike2;

    ImageView image01,image11,image21,imageapttype,imagedesc,imageposition;

    CardView mCardView;
    CardView mCardView1;
    public SingleListItemView(@NonNull View itemView) {
        super(itemView);

        Jobtitle = itemView.findViewById(R.id.tvDisplay);
        Image = itemView.findViewById(R.id.ivPic);
        image1 = itemView.findViewById(R.id.imagelocation);
        image2 = itemView.findViewById(R.id.imagecompany);
        image3 = itemView.findViewById(R.id.school);
        mCardView = itemView.findViewById(R.id.card2);
        mCardView1 = itemView.findViewById(R.id.card1);

        location = itemView.findViewById(R.id.tvLocation);
        company = itemView.findViewById(R.id.tvcompany);
        school = itemView.findViewById(R.id.tvschool);
        salary = itemView.findViewById(R.id.tvr);


        btninfo = itemView.findViewById(R.id.btnInfo);
        btnedit = itemView.findViewById(R.id.btnEdit);
        btndelete = itemView.findViewById(R.id.btndelete);
        btnlike2 = itemView.findViewById(R.id.btnLike2);



        Jobtitle1 = itemView.findViewById(R.id.tvDisplay1);
        location1 = itemView.findViewById(R.id.tvLocation1);
        company1 = itemView.findViewById(R.id.tvcompany1);
        school1 = itemView.findViewById(R.id.tvschool1);
        salary1 = itemView.findViewById(R.id.tvr1);
        r1 = itemView.findViewById(R.id.r1);
        apttype1 = itemView.findViewById(R.id.tvappointmenttype);
        desc1 = itemView.findViewById(R.id.tvdescription);


        image01 = itemView.findViewById(R.id.imagelocation1);
        image11 = itemView.findViewById(R.id.imagecompany1);
        image21 = itemView.findViewById(R.id.school1);
        imageapttype = itemView.findViewById(R.id.appointmenttypeimage);
        imagedesc =itemView.findViewById(R.id.imagedescription);
        imageposition = itemView.findViewById(R.id.imagejobposition);
        btnlike = itemView.findViewById(R.id.btnLike);

        r = itemView.findViewById(R.id.r);

    }


}
