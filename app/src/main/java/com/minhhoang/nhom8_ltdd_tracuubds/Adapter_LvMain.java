package com.minhhoang.nhom8_ltdd_tracuubds;

import android.app.LauncherActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adapter_LvMain extends ArrayAdapter<ListItem_main> {
    private final Context context;
    private final List<ListItem_main> items;

    public Adapter_LvMain(Context context, List<ListItem_main> items) {
        super(context, R.layout.list_item, items);
        this.context = context;
        this.items = items;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item, parent, false);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView textView = (TextView) rowView.findViewById(R.id.text);


        ListItem_main item = items.get(position);
        imageView.setImageResource(item.getIconId1());
        textView.setText(item.getText());
        return rowView;
    }
}
