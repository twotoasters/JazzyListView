package com.twotoasters.jazzylistview.effects;

import android.view.View;
import android.view.ViewPropertyAnimator;

import com.twotoasters.jazzylistview.JazzyEffect;

public class FanEffect implements JazzyEffect {

    private static final int INITIAL_ROTATION_ANGLE = 70;

    @Override
    public void initView(View item, int position, int scrollDirection) {
        item.setPivotX(0);
        item.setPivotY(0);
        item.setRotation(INITIAL_ROTATION_ANGLE * scrollDirection);
    }

    @Override
    public void setupAnimation(View item, int position, int scrollDirection, ViewPropertyAnimator animator) {
        animator.rotationBy(-INITIAL_ROTATION_ANGLE * scrollDirection);
    }
}
