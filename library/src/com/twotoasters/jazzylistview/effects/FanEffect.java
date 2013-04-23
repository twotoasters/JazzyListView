package com.twotoasters.jazzylistview.effects;

import android.view.View;

import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;
import com.twotoasters.jazzylistview.JazzyEffect;

public class FanEffect implements JazzyEffect {

    private static final int INITIAL_ROTATION_ANGLE = 70;

    @Override
    public void initView(View item, int position, int scrollDirection) {
        ViewHelper.setPivotX(item, 0);
        ViewHelper.setPivotY(item, 0);
        ViewHelper.setRotation(item, INITIAL_ROTATION_ANGLE * scrollDirection);
    }

    @Override
    public void setupAnimation(View item, int position, int scrollDirection, ViewPropertyAnimator animator) {
        animator.rotationBy(-INITIAL_ROTATION_ANGLE * scrollDirection);
    }

}
