package com.twotoasters.jazzylistview;

import android.view.View;

import com.nineoldandroids.view.ViewPropertyAnimator;

public interface JazzyEffect {
    void initView(View item, int position, int scrollDirection);
    void setupAnimation(View item, int position, int scrollDirection, ViewPropertyAnimator animator);
}
