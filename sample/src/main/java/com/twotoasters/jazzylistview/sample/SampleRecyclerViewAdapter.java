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
package com.twotoasters.jazzylistview.sample;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.twotoasters.jazzylistview.sample.SampleRecyclerViewAdapter.SampleViewHolder;

import java.util.Arrays;
import java.util.List;

class SampleRecyclerViewAdapter extends Adapter<SampleViewHolder> {

    private List<String> items;
    private int itemLayoutRes;
    private boolean isStaggered;

    SampleRecyclerViewAdapter(String[] items, int itemLayoutRes, boolean isStaggered) {
        this.items = Arrays.asList(items);
        this.itemLayoutRes = itemLayoutRes;
        this.isStaggered = isStaggered;
    }

    @Override
    public SampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        if (isStaggered && viewType == 0) {
            view = inflater.inflate(R.layout.grid_item_taller, parent, false);
        } else {
            view = inflater.inflate(itemLayoutRes, parent, false);
        }
        return new SampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SampleViewHolder holder, int position) {
        holder.text.setBackgroundColor(backgroundColor(position, holder));
        holder.text.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        return isStaggered ? position % 2 : 0;
    }

    private int backgroundColor(int position, SampleViewHolder holder) {
        if (isStaggered) {
            int val = (int) (Math.random() * 55 + 200);
            return Color.rgb(val, val, val);
        } else {
            return holder.itemView.getResources().getColor(Utils.getBackgroundColorRes(position, itemLayoutRes));
        }
    }

    public static class SampleViewHolder extends RecyclerView.ViewHolder {
        final TextView text;

        SampleViewHolder(View view) {
            super(view);
            text = (TextView) view.findViewById(R.id.text);
        }
    }
}
