package com.twotoasters.jazzylistview.sample;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PagerListAdapter extends BaseAdapter implements AbsListView.OnScrollListener {

    public static final int COUNT_OF_PER_PAGE = 15;
    private final LayoutInflater inflater;
    private final Resources res;
    private final int itemLayout;
    private final List<String> pagerResults = new ArrayList<String>();
    private LoadingState state = LoadingState.PENDING;
    private final ListView listView;

    private int currentPage = 1;
    private final int totalPages;

    private final Handler handler = new Handler(Looper.getMainLooper());

    public PagerListAdapter(Context context, int itemLayout, ListView listView) {
        inflater = LayoutInflater.from(context);
        res = context.getResources();
        this.itemLayout = itemLayout;
        final int length = ListModel.getModel().length;
        totalPages = length % COUNT_OF_PER_PAGE == 0 ? length / COUNT_OF_PER_PAGE : (length / COUNT_OF_PER_PAGE) + 1;
        this.listView = listView;
        this.listView.setOnScrollListener(this);
    }

    @Override
    public int getCount() {
        return pagerResults.size();
    }

    @Override
    public String getItem(int position) {
        return pagerResults.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(itemLayout, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        int colorResId = position % 2 == 0 ? R.color.even : R.color.odd;
        holder.text.setBackgroundColor(res.getColor(colorResId));
        holder.text.setText(ListModel.getModelItem(position));

        return convertView;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (state != LoadingState.PENDING) {
            return;
        }

        if (firstVisibleItem + visibleItemCount >= totalItemCount && currentPage < totalPages) {
            loadPage(currentPage++);
        }
    }

    private void loadPage(final int page) {
        state = LoadingState.LOADING;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                final int length = ListModel.getModel().length;
                for (int i = 0; i < COUNT_OF_PER_PAGE; i++) {
                    int index = page * COUNT_OF_PER_PAGE + i;
                    if (index >= length) {
                        break;
                    }
                    pagerResults.add(ListModel.getModelItem(index));
                }
                notifyDataSetChanged();
                state = page == totalPages ? LoadingState.END : LoadingState.PENDING;
            }
        }, 2000);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {}

    static class ViewHolder {
        final TextView text;

        ViewHolder(View view) {
            text = (TextView) view.findViewById(R.id.text);
        }
    }

    static enum LoadingState {
        PENDING, LOADING, FAILURE, END
    }

}