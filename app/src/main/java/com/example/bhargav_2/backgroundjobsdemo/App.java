package com.example.bhargav_2.backgroundjobsdemo;

import android.app.Activity;
import android.app.Application;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

public class App extends Application implements Application.ActivityLifecycleCallbacks {
    private int activityReferences=0;
    private boolean isActivityChangingConfigurations=false;
    public String TAG="mytag";

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
