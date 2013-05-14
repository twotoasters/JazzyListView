package com.twotoasters.jazzylistview.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.twotoasters.jazzylistview.JazzyHelper;
import com.twotoasters.jazzylistview.JazzyListView;

import java.util.*;

public class SimpleListActivity extends Activity {

    private static final String KEY_TRANSITION_EFFECT = "transition_effect";

    private JazzyListView mList;
    private HashMap<String, Integer> mEffectMap;
    private int mCurrentTransitionEffect = JazzyHelper.HELIX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mList = (JazzyListView) findViewById(android.R.id.list);
        mList.setAdapter(new ListAdapter(this, R.layout.item));

        if (savedInstanceState != null){
            mCurrentTransitionEffect = savedInstanceState.getInt(KEY_TRANSITION_EFFECT, JazzyHelper.HELIX);
            setupJazziness(mCurrentTransitionEffect);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mEffectMap = new HashMap<String, Integer>();
        int i = 0;
        String[] effects = this.getResources().getStringArray(R.array.jazzy_effects);
        for (String effect : effects) {
            mEffectMap.put(effect, i++);
        }

        List<String> effectList = new ArrayList<String>(Arrays.asList(effects));
        Collections.sort(effectList);
        effectList.remove(getString(R.string.standard));
        effectList.add(0, getString(R.string.standard));
        for (String effect : effectList) {
            menu.add(effect);
        }

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
        mList.setTransitionEffect(mCurrentTransitionEffect);
    }
}