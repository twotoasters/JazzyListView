package com.twotoasters.jazzylistview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

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
import com.twotoasters.jazzylistview.effects.StandardEffect;
import com.twotoasters.jazzylistview.effects.TiltEffect;
import com.twotoasters.jazzylistview.effects.TwirlEffect;
import com.twotoasters.jazzylistview.effects.WaveEffect;
import com.twotoasters.jazzylistview.effects.ZipperEffect;

public class JazzyListView extends ListView implements OnScrollListener {

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

    public static final int DURATION = 600;
    public static final int OPAQUE = 255, TRANSPARENT = 0;

    private JazzyEffect mTransitionEffect = null;
    private boolean mIsScrolling = false;
    private int mFirstVisibleItem = -1;
    private int mLastVisibleItem = -1;

    private OnScrollListener mAdditionalOnScrollListener;

    public JazzyListView(Context context) {
        this(context, null);
    }

    public JazzyListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JazzyListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        super.setOnScrollListener(this); // call super's method to actually register this list as the listener


        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.JazzyListView);
        int transitionEffect = a.getInteger(R.styleable.JazzyListView_effect, STANDARD);
        a.recycle();

        setTransitionEffect(transitionEffect);
    }

    /**
     * @see AbsListView#setOnScrollListener
     */
    @Override
    public final void setOnScrollListener(OnScrollListener l) {
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
            int indexAfterFirst = 0;
            while (firstVisibleItem + indexAfterFirst < mFirstVisibleItem) {
                View item = view.getChildAt(indexAfterFirst);
                doJazziness(item, firstVisibleItem + indexAfterFirst, -1);
                indexAfterFirst++;
            }

            int indexBeforeLast = 0;
            while (lastVisibleItem - indexBeforeLast > mLastVisibleItem) {
                View item = view.getChildAt(lastVisibleItem - firstVisibleItem - indexBeforeLast);
                doJazziness(item, lastVisibleItem, 1);
                indexBeforeLast++;
            }
        }

        mFirstVisibleItem = firstVisibleItem;
        mLastVisibleItem = lastVisibleItem;

        notifyAdditionalScrollListener(view, firstVisibleItem, visibleItemCount, totalItemCount);
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
            ViewPropertyAnimator animator = com.nineoldandroids.view.ViewPropertyAnimator
                    .animate(item)
                    .setDuration(DURATION)
                    .setInterpolator(new AccelerateDecelerateInterpolator());

            scrollDirection = scrollDirection > 0 ? 1 : -1;
            mTransitionEffect.initView(item, position, scrollDirection);
            mTransitionEffect.setupAnimation(item, position, scrollDirection, animator);
            animator.start();
        }
    }

    /**
     * @see AbsListView#onScrollStateChanged
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch(scrollState) {
            case OnScrollListener.SCROLL_STATE_IDLE:
                mIsScrolling = false;
                break;
            case OnScrollListener.SCROLL_STATE_FLING:
            case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                mIsScrolling = true;
                break;
            default: break;
        }
    }

    /**
     * Sets the desired transition effect.
     *
     * @param transitionEffect Numeric constant representing a bundled transition effect.
     */
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
            default: break;
        }
    }

    /**
     * Sets the desired transition effect.
     *
     * @param transitionEffect The non-bundled transition provided by the client.
     */
    public void setTransitionEffect(JazzyEffect transitionEffect) {
        mTransitionEffect = transitionEffect;
    }

    /**
     * Notifies the OnScrollListener of an onScroll event, since JazzyListView is the primary listener for onScroll events.
     */
    private void notifyAdditionalScrollListener(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (mAdditionalOnScrollListener != null) {
            mAdditionalOnScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
        }
    }
}
