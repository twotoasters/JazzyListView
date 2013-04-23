package com.twotoasters.jazzylistview.effects;

import android.view.View;

import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;
import com.twotoasters.jazzylistview.JazzyEffect;

public class ReverseFlyEffect implements JazzyEffect {

    private static final int INITIAL_ROTATION_ANGLE = 135;

    @Override
    public void initView(View item, int position, int scrollDirection) {
        ViewHelper.setPivotX(item, item.getWidth() / 2);
        ViewHelper.setPivotY(item, item.getHeight() / 2);
        ViewHelper.setRotationX(item, INITIAL_ROTATION_ANGLE * scrollDirection);
        ViewHelper.setTranslationY(item, -item.getHeight() * 2 * scrollDirection);
    }

    @Override
    public void setupAnimation(View item, int position, int scrollDirection, ViewPropertyAnimator animator) {
        animator.rotationXBy(-INITIAL_ROTATION_ANGLE * scrollDirection).translationYBy(item.getHeight() * 2 * scrollDirection);
    }

}
