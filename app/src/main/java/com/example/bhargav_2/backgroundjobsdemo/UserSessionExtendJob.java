package com.example.bhargav_2.backgroundjobsdemo;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

public class UserSessionExtendJob extends JobService {
    public String TAG="mytag";
    @Override
    public boolean onStartJob(JobParameters params) {
        Log.i("mytag", "onStartJob: user session extend job started");
        long startTime=System.currentTimeMillis();
        long currentTime=System.currentTimeMillis();
        while(currentTime-startTime<=120000){
            currentTime=System.currentTimeMillis();
        }
        Log.i(TAG, "onStartJob: user session extended");
        jobFinished(params,true);
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.i("mytag", "onStopJob: user session extend job cancelled ");
        return false;
    }
}
