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
