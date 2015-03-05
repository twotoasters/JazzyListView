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

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class JazzyGridView extends GridView {

    private final JazzyHelper mHelper;

    public JazzyGridView(Context context) {
        super(context);
        mHelper = init(context, null);
    }

    public JazzyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHelper = init(context, attrs);
    }

    public JazzyGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mHelper = init(context, attrs);
    }

    private JazzyHelper init(Context context, AttributeSet attrs) {
        JazzyHelper helper = new JazzyHelper(context, attrs);
        super.setOnScrollListener(helper);
        return helper;
    }

    /**
     * @see android.widget.AbsListView#setOnScrollListener
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
