package com.minhhoang.nhom8_ltdd_tracuubds.menu.profile;

import android.widget.ArrayAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.minhhoang.nhom8_ltdd_tracuubds.R;

import java.util.List;
public class Adapter_LvThongbao extends ArrayAdapter<Hoso_thongbao_item> {
    private final Context context;
    private final List<Hoso_thongbao_item> items;

    public Adapter_LvThongbao( Context context, List<Hoso_thongbao_item> items) {
        super(context, R.layout.adapter_profile_listview_thongbao, items);
        this.context = context;
        this.items = items;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.adapter_profile_listview_thongbao, parent, false);

        TextView tvText = rowView.findViewById(R.id.tv_thongbao);
        tvText.setText(items.get(position).getText_thongbao());

        return rowView;
    }
}
