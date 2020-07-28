package com.example.job_management.db_operations;

import android.content.Context;

import androidx.room.Room;

public class  AdvertConnection {

    private static AdvertConnection instance;
    private AppAdvertDatabase database;

    private AdvertConnection (Context context)
    {
        database = Room.databaseBuilder(context, AppAdvertDatabase.class, "advert_management").build();
    }

    public static AdvertConnection getInstance(Context context)
    {
        synchronized (AdvertConnection.class)
        {
            if(instance == null)
            {
                instance = new AdvertConnection(context);
            }

            return instance;
        }
    }

    public AppAdvertDatabase getDatabase(){return database;}
}
