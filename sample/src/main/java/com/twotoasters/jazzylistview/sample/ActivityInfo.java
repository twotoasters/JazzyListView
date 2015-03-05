/*
 * Copyright (C) 2015 Two Toasters
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
