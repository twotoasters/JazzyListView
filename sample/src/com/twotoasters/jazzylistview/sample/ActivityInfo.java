package com.twotoasters.jazzylistview.sample;

import android.app.Activity;

class ActivityInfo {
    public final Class<? extends Activity> activityClass;
    public final int titleRes;
    public final int itemLayoutRes;
    public final boolean isStaggered;

    public ActivityInfo(Class<? extends Activity> activityClass, int titleRes, int itemLayoutRes, boolean isStaggered) {
        this.activityClass = activityClass;
        this.titleRes = titleRes;
        this.itemLayoutRes = itemLayoutRes;
        this.isStaggered = isStaggered;
    }
}
