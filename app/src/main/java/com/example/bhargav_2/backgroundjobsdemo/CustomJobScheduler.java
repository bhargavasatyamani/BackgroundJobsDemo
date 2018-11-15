package com.example.bhargav_2.backgroundjobsdemo;

import android.app.Application;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;

public class CustomJobScheduler extends Application {
    JobScheduler sessionExtendJobScheduler;
    JobInfo sessionExtendJobInfo;

    protected void scheduleSessionExtendJob(){
        sessionExtendJobScheduler=(JobScheduler)getApplicationContext().getSystemService(JOB_SCHEDULER_SERVICE);
        sessionExtendJobInfo=new JobInfo.Builder(1,new ComponentName(getPackageName(),UserSessionExtendJob.class.getName())).setPeriodic(0).build();
        sessionExtendJobScheduler.schedule(sessionExtendJobInfo);
    }
    protected void cancelSessionExtendJob(){
        sessionExtendJobScheduler.cancelAll();
    }
}
