package com.twotoasters.jazzylistview;

import java.util.HashSet;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AbsListView;

import com.nineoldandroids.view.ViewPropertyAnimator;
import com.twotoasters.jazzylistview.effects.CardsEffect;
import com.twotoasters.jazzylistview.effects.CurlEffect;
import com.twotoasters.jazzylistview.effects.FadeEffect;
import com.twotoasters.jazzylistview.effects.FanEffect;
import com.twotoasters.jazzylistview.effects.FlipEffect;
import com.twotoasters.jazzylistview.effects.FlyEffect;
import com.twotoasters.jazzylistview.effects.GrowEffect;
import com.twotoasters.jazzylistview.effects.HelixEffect;
import com.twotoasters.jazzylistview.effects.ReverseFlyEffect;
import com.twotoasters.jazzylistview.effects.SlideInEffect;
import com.twotoasters.jazzylistview.effects.StandardEffect;
import com.twotoasters.jazzylistview.effects.TiltEffect;
import com.twotoasters.jazzylistview.effects.TwirlEffect;
import com.twotoasters.jazzylistview.effects.WaveEffect;
import com.twotoasters.jazzylistview.effects.ZipperEffect;

public class JazzyHelper implements AbsListView.OnScrollListener {

    public static final int STANDARD = 0;
    public static final int GROW = 1;
    public static final int CARDS = 2;
    public static final int CURL = 3;
    public static final int WAVE = 4;
    public static final int FLIP = 5;
    public static final int FLY = 6;
    public static final int REVERSE_FLY = 7;
    public static final int HELIX = 8;
    public static final int FAN = 9;
    public static final int TILT = 10;
    public static final int ZIPPER = 11;
    public static final int FADE = 12;
    public static final int TWIRL = 13;
    public static final int SLIDE_IN = 14;

    public static final int DURATION = 600;
    public static final int OPAQUE = 255, TRANSPARENT = 0;

    private JazzyEffect mTransitionEffect = null;
    private boolean mIsScrolling = false;
    private int mFirstVisibleItem = -1;
    private int mLastVisibleItem = -1;
    private int mPreviousFirstVisibleItem = 0;
    private long mPreviousEventTime = 0;
    private double mSpeed = 0;
    private int mMaxVelocity = 0;
    public static final int MAX_VELOCITY_OFF = 0;

    private AbsListView.OnScrollListener mAdditionalOnScrollListener;

    private boolean mOnlyAnimateNewItems;
    private boolean mOnlyAnimateOnFling;
    private boolean mIsFlingEvent;
    private boolean mSimulateGridWithList;
    private final HashSet<Integer> mAlreadyAnimatedItems;

    public JazzyHelper(Context context, AttributeSet attrs) {

        mAlreadyAnimatedItems = new HashSet<Integer>();

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.JazzyListView);
        int transitionEffect = a.getInteger(R.styleable.JazzyListView_effect, STANDARD);
        int maxVelocity = a.getInteger(R.styleable.JazzyListView_max_velocity, MAX_VELOCITY_OFF);
        mOnlyAnimateNewItems = a.getBoolean(R.styleable.JazzyListView_only_animate_new_items, false);
        mOnlyAnimateOnFling = a.getBoolean(R.styleable.JazzyListView_max_velocity, false);
        mSimulateGridWithList = a.getBoolean(R.styleable.JazzyListView_simulate_grid_with_list, false);
        a.recycle();

        setTransitionEffect(transitionEffect);
        setMaxAnimationVelocity(maxVelocity);
    }

    public void setOnScrollListener(AbsListView.OnScrollListener l) {
        // hijack the scroll listener setter and have this list also notify the additional listener
        mAdditionalOnScrollListener = l;
    }

    /**
     * @see AbsListView.OnScrollListener#onScroll
     */
    @Override
    public final void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        boolean shouldAnimateItems = (mFirstVisibleItem != -1 && mLastVisibleItem != -1);

        int lastVisibleItem = firstVisibleItem + visibleItemCount - 1;
        if (mIsScrolling && shouldAnimateItems) {
            setVelocity(firstVisibleItem, totalItemCount);
            int indexAfterFirst = 0;
            while (firstVisibleItem + indexAfterFirst < mFirstVisibleItem) {
                View item = view.getChildAt(indexAfterFirst);
                doJazziness(item, firstVisibleItem + indexAfterFirst, -1);
                indexAfterFirst++;
            }

            int indexBeforeLast = 0;
            while (lastVisibleItem - indexBeforeLast > mLastVisibleItem) {
                View item = view.getChildAt(lastVisibleItem - firstVisibleItem - indexBeforeLast);
                doJazziness(item, lastVisibleItem - indexBeforeLast, 1);
                indexBeforeLast++;
            }
        } else if (!shouldAnimateItems) {
            for (int i = firstVisibleItem; i < visibleItemCount; i++) {
                mAlreadyAnimatedItems.add(i);
            }
        }

        mFirstVisibleItem = firstVisibleItem;
        mLastVisibleItem = lastVisibleItem;

        notifyAdditionalOnScrollListener(view, firstVisibleItem, visibleItemCount, totalItemCount);
    }

    /**
     * Should be called in onScroll to keep take of current Velocity.
     *
     * @param firstVisibleItem
     *            The index of the first visible item in the ListView.
     */
    private void setVelocity(int firstVisibleItem, int totalItemCount) {
        if (mMaxVelocity > MAX_VELOCITY_OFF && mPreviousFirstVisibleItem != firstVisibleItem) {
            long currTime = System.currentTimeMillis();
            long timeToScrollOneItem = currTime - mPreviousEventTime;
            if (timeToScrollOneItem < 1) {
                double newSpeed = ((1.0d / timeToScrollOneItem) * 1000);
                // We need to normalize velocity so different size item don't
                // give largely different velocities.
                if (newSpeed < (0.9f * mSpeed)) {
                    mSpeed *= 0.9f;
                } else if (newSpeed > (1.1f * mSpeed)) {
                    mSpeed *= 1.1f;
                } else {
                    mSpeed = newSpeed;
                }
            } else {
                mSpeed = ((1.0d / timeToScrollOneItem) * 1000);
            }

            mPreviousFirstVisibleItem = firstVisibleItem;
            mPreviousEventTime = currTime;
        }
    }

    /**
     *
     * @return Returns the current Velocity of the ListView's scrolling in items
     *         per second.
     */
    private double getVelocity() {
        return mSpeed;
    }

    /**
     * Initializes the item view and triggers the animation.
     *
     * @param item The view to be animated.
     * @param position The index of the view in the list.
     * @param scrollDirection Positive number indicating scrolling down, or negative number indicating scrolling up.
     */
    private void doJazziness(View item, int position, int scrollDirection) {
        if (mIsScrolling) {
            if (mOnlyAnimateNewItems && mAlreadyAnimatedItems.contains(position))
                return;

            if (mOnlyAnimateOnFling && !mIsFlingEvent)
                return;

            if (mMaxVelocity > MAX_VELOCITY_OFF && mMaxVelocity < getVelocity())
                return;

            if (mSimulateGridWithList) {
                ViewGroup itemRow = (ViewGroup) item;
                for (int i = 0; i < itemRow.getChildCount(); i++)
                    doJazzinessImpl(itemRow.getChildAt(i), position, scrollDirection);
            } else {
                doJazzinessImpl(item, position, scrollDirection);
            }

            mAlreadyAnimatedItems.add(position);
        }
    }

    private void doJazzinessImpl(View item, int position, int scrollDirection) {
        ViewPropertyAnimator animator = com.nineoldandroids.view.ViewPropertyAnimator
                .animate(item)
                .setDuration(DURATION)
                .setInterpolator(new AccelerateDecelerateInterpolator());

        scrollDirection = scrollDirection > 0 ? 1 : -1;
        mTransitionEffect.initView(item, position, scrollDirection);
        mTransitionEffect.setupAnimation(item, position, scrollDirection, animator);
        animator.start();
    }

    /**
     * @see AbsListView.OnScrollListener#onScrollStateChanged
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch(scrollState) {
            case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                mIsScrolling = false;
                mIsFlingEvent = false;
                break;
            case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                mIsFlingEvent = true;
                break;
            case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                mIsScrolling = true;
                mIsFlingEvent = false;
                break;
            default: break;
        }
        notifyAdditionalOnScrollStateChangedListener(view, scrollState);
    }

    public void setTransitionEffect(int transitionEffect) {

        switch (transitionEffect) {
            case STANDARD: setTransitionEffect(new StandardEffect()); break;
            case GROW: setTransitionEffect(new GrowEffect()); break;
            case CARDS: setTransitionEffect(new CardsEffect()); break;
            case CURL: setTransitionEffect(new CurlEffect()); break;
            case WAVE: setTransitionEffect(new WaveEffect()); break;
            case FLIP: setTransitionEffect(new FlipEffect()); break;
            case FLY: setTransitionEffect(new FlyEffect()); break;
            case REVERSE_FLY: setTransitionEffect(new ReverseFlyEffect()); break;
            case HELIX: setTransitionEffect(new HelixEffect()); break;
            case FAN: setTransitionEffect(new FanEffect()); break;
            case TILT: setTransitionEffect(new TiltEffect()); break;
            case ZIPPER: setTransitionEffect(new ZipperEffect()); break;
            case FADE: setTransitionEffect(new FadeEffect()); break;
            case TWIRL: setTransitionEffect(new TwirlEffect()); break;
            case SLIDE_IN: setTransitionEffect(new SlideInEffect()); break;
            default: break;
        }
    }

    public void setTransitionEffect(JazzyEffect transitionEffect) {
        mTransitionEffect = transitionEffect;
    }

    public void setShouldOnlyAnimateNewItems(boolean onlyAnimateNew) {
        mOnlyAnimateNewItems = onlyAnimateNew;
    }

    public void setShouldOnlyAnimateFling(boolean onlyFling) {
        mOnlyAnimateOnFling = onlyFling;
    }

    public void setMaxAnimationVelocity(int itemsPerSecond) {
        mMaxVelocity = itemsPerSecond;
    }

    public void setSimulateGridWithList(boolean simulateGridWithList) {
        mSimulateGridWithList = simulateGridWithList;
    }

    /**
     * Notifies the OnScrollListener of an onScroll event, since JazzyListView is the primary listener for onScroll events.
     */
    private void notifyAdditionalOnScrollListener(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (mAdditionalOnScrollListener != null) {
            mAdditionalOnScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
        }
    }

    /**
     * Notifies the OnScrollListener of an onScrollStateChanged event, since JazzyListView is the primary listener for onScrollStateChanged events.
     */
    private void notifyAdditionalOnScrollStateChangedListener(AbsListView view, int scrollState) {
        if (mAdditionalOnScrollListener != null) {
            mAdditionalOnScrollListener.onScrollStateChanged(view, scrollState);
        }
    }
}
