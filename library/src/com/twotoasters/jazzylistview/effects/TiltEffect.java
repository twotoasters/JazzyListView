package com.twotoasters.jazzylistview.effects;

import android.view.View;
import android.view.ViewPropertyAnimator;

import com.twotoasters.jazzylistview.JazzyEffect;
import com.twotoasters.jazzylistview.JazzyHelper;

public class TiltEffect implements JazzyEffect {

    private static final float INITIAL_SCALE_FACTOR = 0.7f;

    @Override
    public void initView(View item, int position, int scrollDirection) {
        item.setPivotX(item.getWidth() / 2);
        item.setPivotY(item.getHeight() / 2);
        item.setScaleX(INITIAL_SCALE_FACTOR);
        item.setScaleY(INITIAL_SCALE_FACTOR);
        item.setTranslationY(item.getHeight() / 2 * scrollDirection);
        item.setAlpha(JazzyHelper.OPAQUE / 2);
    }

    @Override
    public void setupAnimation(View item, int position, int scrollDirection, ViewPropertyAnimator animator) {
        animator
            .translationYBy(-item.getHeight() / 2 * scrollDirection)
            .scaleX(1)
            .scaleY(1)
            .alpha(JazzyHelper.OPAQUE);
    }
}
