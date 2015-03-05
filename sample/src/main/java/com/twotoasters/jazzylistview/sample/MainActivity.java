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
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends ListActivity {

    public static final String EXTRA_TITLE_RES = "title_res";
    public static final String EXTRA_ITEM_LAYOUT_RES = "item_layout_res";
    public static final String EXTRA_IS_STAGGERED = "is_staggered";

    private final List<ActivityInfo> activityInfos = Arrays.asList(
            new ActivityInfo(ListViewActivity.class, R.string.listview_example, R.layout.item, false),
            new ActivityInfo(GridViewActivity.class, R.string.gridview_example, R.layout.grid_item, false),
            new ActivityInfo(RecyclerViewActivity.class, R.string.recyclerview_list_example, R.layout.item, false),
            new ActivityInfo(RecyclerViewActivity.class, R.string.recyclerview_grid_example, R.layout.grid_item, false),
            new ActivityInfo(RecyclerViewActivity.class, R.string.recyclerview_staggered_grid_example, R.layout.grid_item, true)
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] titles = getActivityTitles();
        setListAdapter(new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, android.R.id.text1, titles));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        ActivityInfo activityInfo = activityInfos.get(position);
        Class<? extends Activity> clazz = activityInfo.activityClass;
        startActivity(new Intent(this, clazz)
                .putExtra(EXTRA_TITLE_RES, activityInfo.titleRes)
                .putExtra(EXTRA_ITEM_LAYOUT_RES, activityInfo.itemLayoutRes)
                .putExtra(EXTRA_IS_STAGGERED, activityInfo.isStaggered));
    }

    private String[] getActivityTitles() {
        String[] result = new String[activityInfos.size()];
        int i = 0;
        for (ActivityInfo info : activityInfos) {
            result[i++] = getString(info.titleRes);
        }
        return result;
    }
}
