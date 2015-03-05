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
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.twotoasters.jazzylistview.JazzyHelper;
import com.twotoasters.jazzylistview.recyclerview.JazzyRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.Map;

public class RecyclerViewActivity extends Activity {

    private static final String KEY_TRANSITION_EFFECT = "transition_effect";

    private Map<String, Integer> mEffectMap;
    private int mCurrentTransitionEffect = JazzyHelper.HELIX;
    private JazzyRecyclerViewScrollListener jazzyScrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        setTitle(getString(getIntent().getIntExtra(MainActivity.EXTRA_TITLE_RES, 0)));

        int itemLayoutRes = getIntent().getIntExtra(MainActivity.EXTRA_ITEM_LAYOUT_RES, R.layout.item);
        boolean isStaggered = getIntent().getBooleanExtra(MainActivity.EXTRA_IS_STAGGERED, false);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(createLayoutManager(itemLayoutRes, isStaggered));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new SampleRecyclerViewAdapter(ListModel.getModel(), itemLayoutRes, isStaggered));

        jazzyScrollListener = new JazzyRecyclerViewScrollListener();
        recyclerView.setOnScrollListener(jazzyScrollListener);

        if (savedInstanceState != null) {
            mCurrentTransitionEffect = savedInstanceState.getInt(KEY_TRANSITION_EFFECT, JazzyHelper.HELIX);
            setupJazziness(mCurrentTransitionEffect);
        }
    }

    private LayoutManager createLayoutManager(int itemLayoutRes, boolean isStaggered) {
        if (itemLayoutRes == R.layout.item) {
            return new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        } else {
            if (isStaggered) {
                return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            } else {
                return new GridLayoutManager(this, 2);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mEffectMap = Utils.buildEffectMap(this);
        Utils.populateEffectMenu(menu, new ArrayList<>(mEffectMap.keySet()), this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String strEffect = item.getTitle().toString();
        Toast.makeText(this, strEffect, Toast.LENGTH_SHORT).show();
        setupJazziness(mEffectMap.get(strEffect));
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_TRANSITION_EFFECT, mCurrentTransitionEffect);
    }

    private void setupJazziness(int effect) {
        mCurrentTransitionEffect = effect;
        jazzyScrollListener.setTransitionEffect(mCurrentTransitionEffect);
    }
}
