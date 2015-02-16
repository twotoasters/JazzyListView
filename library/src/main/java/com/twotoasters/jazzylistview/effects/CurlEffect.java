package com.twotoasters.jazzylistview.effects;

import android.view.View;
import android.view.ViewPropertyAnimator;

import com.twotoasters.jazzylistview.JazzyEffect;

public class CurlEffect implements JazzyEffect {

    private static final int INITIAL_ROTATION_ANGLE = 90;

    @Override
    public void initView(View item, int position, int scrollDirection) {
        item.setPivotX(0);
        item.setPivotY(item.getHeight() / 2);
        item.setRotationY(INITIAL_ROTATION_ANGLE);
    }

    @Override
    public void setupAnimation(View item, int position, int scrollDirection, ViewPropertyAnimator animator) {
        animator.rotationYBy(-INITIAL_ROTATION_ANGLE);
    }
}
