package com.wh.kotlinnotebook;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import java.util.LinkedList;

/**
 * 作者： wh
 * 时间：  2017/8/23
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class MyApplication extends Application {
    public static final String TAG = "MyApplication";
    private static LinkedList<Activity> activityLinkedList;
    public static MyApplication myApplication;
    public static MyApplication getInstance(){

        return myApplication;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication =this;
        activityLinkedList = new LinkedList<>();
        registerActivityStatus();
    }

    private void registerActivityStatus() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                Log.d(TAG, "onActivityCreated: " + activity.getLocalClassName());
                activityLinkedList.add(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                Log.d(TAG, "onActivityStarted: " + activity.getLocalClassName());
            }

            @Override
            public void onActivityResumed(Activity activity) {
                Log.d(TAG, "onActivityResumed: " + activity.getLocalClassName());
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Log.d(TAG, "onActivityPaused: " + activity.getLocalClassName());
            }

            @Override
            public void onActivityStopped(Activity activity) {
                Log.d(TAG, "onActivityStopped: " + activity.getLocalClassName());
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                Log.d(TAG, "onActivityDestroyed: " + activity.getLocalClassName());
                activityLinkedList.remove(activity);
            }
        });
    }
    /**
     * 退出app
     */
    public static void exitApp() {
        for (Activity activity : activityLinkedList) {
            activity.finish();
        }
    }
}
