/*
 * Copyright (C) 2015 Two Toasters
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
