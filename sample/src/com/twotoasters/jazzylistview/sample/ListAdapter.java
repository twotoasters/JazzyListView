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
    private final int itemLayoutRes;

    public ListAdapter(Context context, int itemLayoutRes) {
        super(context, itemLayoutRes, R.id.text, ListModel.getModel());
        inflater = LayoutInflater.from(context);
        res = context.getResources();
        this.itemLayoutRes = itemLayoutRes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(itemLayoutRes, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text.setBackgroundColor(res.getColor(Utils.getBackgroundColorRes(position, itemLayoutRes)));
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
