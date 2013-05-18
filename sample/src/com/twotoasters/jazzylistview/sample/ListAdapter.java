package com.twotoasters.jazzylistview.sample;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

class ListAdapter extends ArrayAdapter<String> {

    private final LayoutInflater inflater;
    private final Resources res;
    private final int itemLayout;

    public ListAdapter(Context context, int itemLayout) {
        super(context, itemLayout, R.id.text, ListModel.getModel());
        inflater = LayoutInflater.from(context);
        res = context.getResources();
        this.itemLayout = itemLayout;
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

    static class ViewHolder {
        final TextView text;

        ViewHolder(View view) {
            text = (TextView) view.findViewById(R.id.text);
        }
    }
}
