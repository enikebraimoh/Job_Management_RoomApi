package com.example.job_management.db_operations;

import android.content.Context;

import androidx.room.Room;

public class Connections {

    private static Connections instance;
    private AppDatabase database;

    private Connections (Context context)
    {
        database = Room.databaseBuilder(context, AppDatabase.class, "db_job_management").build();
    }

    public static Connections getInstance(Context context)
    {
        synchronized (Connections.class)
        {
            if(instance == null)
            {
                instance = new Connections(context);
            }

            return instance;
        }
    }

    public AppDatabase getDatabase(){return database;}
}
