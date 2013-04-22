package com.twotoasters.jazzylistview.effects;

import android.view.View;

import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;
import com.twotoasters.jazzylistview.JazzyEffect;
import com.twotoasters.jazzylistview.JazzyListView;

public class TiltEffect implements JazzyEffect {

    @Override
    public void initView(View item, int position, int scrollDirection) {
        ViewHelper.setPivotX(item, item.getWidth() / 2);
        ViewHelper.setPivotY(item, item.getHeight() / 2);
        ViewHelper.setScaleX(item, 0.7f);
        ViewHelper.setScaleY(item, 0.7f);
        ViewHelper.setTranslationY(item, item.getHeight() / 2 * scrollDirection);
        ViewHelper.setAlpha(item, JazzyListView.OPAQUE / 2);
    }

    @Override
    public void setupAnimation(View item, int position, int scrollDirection, ViewPropertyAnimator animator) {
        animator
            .translationYBy(-item.getHeight() / 2 * scrollDirection)
            .scaleX(1)
            .scaleY(1)
            .alpha(JazzyListView.OPAQUE);
    }

}
