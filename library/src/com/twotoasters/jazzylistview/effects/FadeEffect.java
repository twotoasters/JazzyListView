package com.twotoasters.jazzylistview.effects;

import android.view.View;

import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;
import com.twotoasters.jazzylistview.JazzyEffect;
import com.twotoasters.jazzylistview.JazzyListView;

public class FadeEffect implements JazzyEffect {

    private static final int DURATION_MULTIPLIER = 5;

    @Override
    public void initView(View item, int position, int scrollDirection) {
        ViewHelper.setAlpha(item, JazzyListView.TRANSPARENT);
    }

    @Override
    public void setupAnimation(View item, int position, int scrollDirection, ViewPropertyAnimator animator) {
        animator.setDuration(JazzyListView.DURATION * DURATION_MULTIPLIER);
        animator.alpha(JazzyListView.OPAQUE);
    }

}
