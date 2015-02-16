package com.twotoasters.jazzylistview.effects;

import android.view.View;
import android.view.ViewPropertyAnimator;

import com.twotoasters.jazzylistview.JazzyEffect;

public class FlyEffect implements JazzyEffect {

    private static final int INITIAL_ROTATION_ANGLE = 135;

    @Override
    public void initView(View item, int position, int scrollDirection) {
        item.setPivotX(item.getWidth() / 2);
        item.setPivotY(item.getHeight() / 2);
        item.setRotationX(-INITIAL_ROTATION_ANGLE * scrollDirection);
        item.setTranslationY(item.getHeight() * 2 * scrollDirection);
    }

    @Override
    public void setupAnimation(View item, int position, int scrollDirection, ViewPropertyAnimator animator) {
        animator.rotationXBy(INITIAL_ROTATION_ANGLE * scrollDirection)
                .translationYBy(-item.getHeight() * 2 * scrollDirection);
    }
}
