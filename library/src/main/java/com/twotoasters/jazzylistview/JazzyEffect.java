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
package com.twotoasters.jazzylistview;

import android.view.View;
import android.view.ViewPropertyAnimator;

/**
 * This interface serves as the contract for a transition effect.
 */
public interface JazzyEffect {
    /**
     * Initializes the view's attributes so that the view is in position to begin the animation.
     *
     * @param item The view to be animated.
     * @param position The index of the view in the list.
     * @param scrollDirection Positive number indicating scrolling down, or negative number indicating scrolling up.
     */
    void initView(View item, int position, int scrollDirection);

    /**
     * Configures the animator object with the relative changes or destination point for any attributes that will be animated.
     *
     * @param item The view to be animated.
     * @param position The index of the view in the list.
     * @param scrollDirection Positive number indicating scrolling down, or negative number indicating scrolling up.
     * @param animator The ViewPropertyAnimator object responsible for animating the view.
     */
    void setupAnimation(View item, int position, int scrollDirection, ViewPropertyAnimator animator);
}
