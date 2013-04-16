package com.twotoasters.jazzylistview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.nineoldandroids.view.ViewPropertyAnimator;

public class JazzyListView extends ListView implements OnScrollListener {

	public enum TransitionEffect {
		Standard,
		Grow,
		Curl,
		Wave,
		Flip,
		Helix,
		Fan,
		Zipper,
		Fade
	}

	private static final String TAG = "Jazzy";

	private static final boolean IS_AT_LEAST_HC =
			Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;

	private static final boolean IS_AT_LEAST_ICS =
			Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;

	public static final int DURATION = 600;
	public static final int OPAQUE = 255, TRANSPARENT = 0;

	private TransitionEffect mEffect = TransitionEffect.Standard;
	private boolean mIsScrolling = false;
	private int mFirstVisibleItem = -1;
	private int mLastVisibleItem = -1;
	// TODO: make work for VHLF
	private int mItemWidth;
	private int mItemHeight;

	// TODO: set transition based on XML
	public JazzyListView(Context context) {
		super(context);
		init();
	}

	public JazzyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public JazzyListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		setOnScrollListener(this);

		Resources res = getResources();
		//mItemWidth = getScreenDimensions(getContext()).x;
		//mItemHeight = res.getDimensionPixelSize(R.dimen.item_height);
		//Log.v("Jazzy", String.format("item size = [w: %d, h: %d]", mItemWidth, mItemHeight));
	}

	@Override
	protected void onScrollChanged(int x, int y, int oldX, int oldY) {
		super.onScrollChanged(x, y, oldX, oldY);
		//Log.v(TAG, String.format("onScrollChanged() - [x: %d, y: %d, oldX: %d, oldY: %d]", x, y, oldX, oldY));
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		Log.v(TAG, String.format("onScroll() - [firstVis: %d, visItemCount: %d, totItemCount: %d]", firstVisibleItem, visibleItemCount, totalItemCount));
		boolean shouldAnimateItems = (mFirstVisibleItem != -1 && mLastVisibleItem != -1);

		int lastVisibleItem = firstVisibleItem + visibleItemCount - 1;
		if (IS_AT_LEAST_HC && mIsScrolling && shouldAnimateItems) {
			// TODO: make sure no items get skipped when scrolling quickly
			// TODO: don't animate twice if scroll one direction then the other
			int indexAfterFirst = 0;
			while(firstVisibleItem + indexAfterFirst < mFirstVisibleItem) {
				Log.v(TAG, "Scrolled to reveal new item(s) ABOVE");
				View item = view.getChildAt(indexAfterFirst);
				doJazziness(item, firstVisibleItem+indexAfterFirst);
				indexAfterFirst++;
			}

			int indexBeforeLast = 0;
			while(lastVisibleItem - indexBeforeLast > mLastVisibleItem) {
				Log.v(TAG, "Scrolled to reveal new item(s) BELOW");
				View item = view.getChildAt(lastVisibleItem - firstVisibleItem - indexBeforeLast);
				doJazziness(item, lastVisibleItem);
				indexBeforeLast++;
			}
		}

		mFirstVisibleItem = firstVisibleItem;
		mLastVisibleItem = lastVisibleItem;
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void doJazziness(View item, int position) {
		if (mIsScrolling) {
			ViewPropertyAnimator animator = com.nineoldandroids.view.ViewPropertyAnimator
					.animate(item)
					.setDuration(DURATION);

			mItemWidth = item.getWidth();
			mItemHeight = item.getHeight();

			switch (mEffect) {
				case Standard:
					break;
				case Grow:
					item.setPivotX(mItemWidth / 2);
					item.setPivotY(mItemHeight / 2);
					item.setScaleX(0);
					item.setScaleY(0);
					animator.scaleX(1).scaleY(1);
					break;
				case Curl:
					item.setPivotX(0);
					item.setPivotY(mItemHeight / 2);
					item.setRotationY(90);
					animator.rotationY(0);
					break;
				case Wave:
					item.setTranslationX(-mItemWidth);
					animator.translationX(0);
					break;
				case Flip:
					item.setPivotX(mItemWidth / 2);
					item.setPivotY(mItemHeight / 2);
					item.setRotationX(90);
					animator.rotationX(0);
					break;
				case Helix:
					item.setRotationY(180);
					animator.rotationY(0);
					break;
				case Fan:
					item.setPivotX(0);
					item.setPivotY(0);
					item.setRotation(70);
					animator.rotationBy(-70);
					break;
				case Zipper:
					boolean isEven = position % 2 == 0;
					item.setTranslationX((isEven ? -1 : 1) * mItemWidth);
					animator.translationX(0);
					break;
				case Fade:
					item.setAlpha(TRANSPARENT);
					animator.setDuration(animator.getDuration()*5);
					animator.alpha(OPAQUE);
					break;
				default:
					break;
			}

			animator.start();
		}
	}

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

		Log.v(TAG, String.format("isScrolling: %s", Boolean.valueOf(mIsScrolling)));
	}

	public void setTransitionEffect(TransitionEffect effect) {
		mEffect = effect;
	}

	@SuppressWarnings("deprecation")
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private Point getScreenDimensions(Context context) {
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point point = new Point();
		if(IS_AT_LEAST_ICS) {
			display.getSize(point);
		} else {
			point.set(display.getWidth(), display.getHeight());
		}
		return point;
	}
}