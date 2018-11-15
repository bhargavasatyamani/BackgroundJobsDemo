package com.example.bhargav_2.backgroundjobsdemo;

import android.app.Activity;
import android.app.Application;
import android.app.Application;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.example.bhargav_2.backgroundjobsdemo.CustomJobScheduler;

public class App extends Application implements Application.ActivityLifecycleCallbacks {
    private int activityReferences=0;
    private boolean isActivityChangingConfigurations=false;
    public String TAG="mytag";

    CustomJobScheduler customJobScheduler;

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        if (++activityReferences == 1 && !isActivityChangingConfigurations) {
            // App enters foreground
            Log.i("mytag", "onActivityStarted: App is in foreground");
            customJobScheduler=new CustomJobScheduler();
            customJobScheduler.scheduleSessionExtendJob();
            Log.i(TAG, "onActivityStarted: user session extend job is scheduled");
        }

    }

    @Override
    public void onActivityResumed(Activity activity) {
        Log.i(TAG, "onActivityResumed: App resumed");
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Log.i("mytag", "onActivityPaused: App paused");

    }

    @Override
    public void onActivityStopped(Activity activity) {
        isActivityChangingConfigurations = activity.isChangingConfigurations();
        if (--activityReferences == 0 && !isActivityChangingConfigurations) {
            // App enters background
            Log.i("mytag", "onActivityStopped: App is in background");

            //cancel the session extend job scheduled
            customJobScheduler.cancelSessionExtendJob();

            Log.i(TAG, "onActivityStopped: session extension job cancelled");
        }

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

        Log.i(TAG, "onActivityDestroyed: App destroyed");

    }
}
