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
