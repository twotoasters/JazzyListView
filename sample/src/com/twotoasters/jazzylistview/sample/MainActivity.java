package com.twotoasters.jazzylistview.sample;

import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.twotoasters.jazzylistview.JazzyListView;

public class MainActivity extends Activity {

    private JazzyListView mList;
    private HashMap<String, Integer> mEffectMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mList = (JazzyListView) findViewById(android.R.id.list);
        mList.setAdapter(new ListAdapter(this));

        // Initial effect is specified in XML layout
        // setupJazziness(TransitionEffect.Helix);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mEffectMap = new HashMap<String, Integer>();
        int i = 0;
        String[] effects = this.getResources().getStringArray(R.array.jazzy_effects);
        for (String effect : effects) {
            menu.add(effect);
            mEffectMap.put(effect, Integer.valueOf(i++));
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

    private void setupJazziness(int effect) {
        mList.setTransitionEffect(effect);
    }
}
