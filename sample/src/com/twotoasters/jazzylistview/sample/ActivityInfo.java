package com.twotoasters.jazzylistview.sample;

import android.app.Activity;

public class ActivityInfo {
    public Class<? extends Activity> activityClass;
    public int titleResourceId;

    public ActivityInfo(Class<? extends Activity> activityClass, int titleResourceId) {
        this.activityClass = activityClass;
        this.titleResourceId = titleResourceId;
    }
}
