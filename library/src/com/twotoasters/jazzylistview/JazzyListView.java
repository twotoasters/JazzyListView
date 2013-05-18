package com.twotoasters.jazzylistview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class JazzyListView extends ListView {

    private final JazzyHelper mHelper;

    public JazzyListView(Context context) {
        this(context, null);
    }

    public JazzyListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JazzyListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        mHelper = new JazzyHelper(context, attrs);
        super.setOnScrollListener(mHelper);
    }

    /**
     * @see AbsListView#setOnScrollListener
     */
    @Override
    public final void setOnScrollListener(OnScrollListener l) {
        mHelper.setOnScrollListener(l);
    }

    /**
     * Sets the desired transition effect.
     *
     * @param transitionEffect Numeric constant representing a bundled transition effect.
     */
    public void setTransitionEffect(int transitionEffect) {
        mHelper.setTransitionEffect(transitionEffect);
    }

    /**
     * Sets the desired transition effect.
     *
     * @param transitionEffect The non-bundled transition provided by the client.
     */
    public void setTransitionEffect(JazzyEffect transitionEffect) {
        mHelper.setTransitionEffect(transitionEffect);
    }

    /**
     * Sets whether new items or all items should be animated when they become visible.
     *
     * @param onlyAnimateNew True if only new items should be animated; false otherwise.
     */
    public void setShouldOnlyAnimateNewItems(boolean onlyAnimateNew) {
        mHelper.setShouldOnlyAnimateNewItems(onlyAnimateNew);
    }
}
