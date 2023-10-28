package com.minhhoang.nhom8_ltdd_tracuubds.menu.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.minhhoang.nhom8_ltdd_tracuubds.R;

import java.util.List;

public class Adapter_LvLichsu extends ArrayAdapter<Hoso_Ls_Item> {
    public Adapter_LvLichsu (Context context, List<Hoso_Ls_Item> items) {
        super(context,0,items);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_profile_listview_lichsutracuu, parent, false);
        }
        Hoso_Ls_Item currentItem = getItem(position);
        ImageView anhbds = convertView.findViewById(R.id.lv_anhbds);
        TextView tieude = convertView.findViewById(R.id.tieude);
        TextView noidung = convertView.findViewById(R.id.noidung);
        anhbds.setImageResource(currentItem.getImgResource());
        tieude.setText(currentItem.getTieude());
        noidung.setText(currentItem.getNoidung());
        return convertView;
    }
}
