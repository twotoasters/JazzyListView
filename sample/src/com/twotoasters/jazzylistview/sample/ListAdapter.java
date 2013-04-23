package com.twotoasters.jazzylistview.sample;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListAdapter extends ArrayAdapter<String> {

    public static final int DURATION = 600;
    public static final int OPAQUE = 255, TRANSPARENT = 0;

    private LayoutInflater inflater;
    private Resources res;

    public ListAdapter(Context context) {
        super(context, R.layout.item, R.id.text, ListModel.getModel());
        inflater = LayoutInflater.from(context);
        res = context.getResources();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item, null);
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
        TextView text;

        ViewHolder(View view) {
            text = (TextView) view.findViewById(R.id.text);
        }
    }
}
