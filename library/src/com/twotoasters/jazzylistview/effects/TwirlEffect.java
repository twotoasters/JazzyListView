package com.twotoasters.jazzylistview.effects;

import android.view.View;

import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;
import com.twotoasters.jazzylistview.JazzyEffect;

public class TwirlEffect implements JazzyEffect {

    private static final int INITIAL_ROTATION_X = 80;
    private static final int INITIAL_ROTATION_Y = 70;
    private static final int INITIAL_ROTATION_Z = 10;


    @Override
    public void initView(View item, int position, int scrollDirection) {
        ViewHelper.setPivotX(item, item.getWidth() / 2);
        ViewHelper.setPivotY(item, item.getWidth() / 2);
        ViewHelper.setRotationX(item, INITIAL_ROTATION_X);
        ViewHelper.setRotationY(item, INITIAL_ROTATION_Y * scrollDirection);
        ViewHelper.setRotation(item, INITIAL_ROTATION_Z);
    }

    @Override
    public void setupAnimation(View item, int position, int scrollDirection, ViewPropertyAnimator animator) {
        animator.rotationXBy(-INITIAL_ROTATION_X).rotationYBy(-INITIAL_ROTATION_Y * scrollDirection).rotationBy(-INITIAL_ROTATION_Z);
    }

}
