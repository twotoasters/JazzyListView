package com.twotoasters.jazzylistview.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.twotoasters.jazzylistview.JazzyListView;
import com.twotoasters.jazzylistview.JazzyListView.TransitionEffect;

public class MainActivity extends Activity {

	private JazzyListView mList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mList = (JazzyListView) findViewById(android.R.id.list);
		mList.setAdapter(new ListAdapter(this));
		setupJazziness(TransitionEffect.Wave);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		String[] effects = this.getResources().getStringArray(R.array.jazzy_effects);
		for (String effect : effects) {
			menu.add(effect);
		}
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		String strEffect = item.getTitle().toString();
		Toast.makeText(this, strEffect, Toast.LENGTH_SHORT).show();
		TransitionEffect effect = TransitionEffect.valueOf(strEffect);
		setupJazziness(effect);
		return true;
	}

	private void setupJazziness(TransitionEffect effect) {
		mList.setTransitionEffect(effect);
	}
}
