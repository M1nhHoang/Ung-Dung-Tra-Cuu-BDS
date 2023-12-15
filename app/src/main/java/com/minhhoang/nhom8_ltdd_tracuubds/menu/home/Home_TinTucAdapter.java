package com.minhhoang.nhom8_ltdd_tracuubds.menu.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.minhhoang.nhom8_ltdd_tracuubds.R;

import java.util.List;

public class Home_TinTucAdapter extends RecyclerView.Adapter<Home_TinTucAdapter.Home_TinTucViewHolder> {

    private Context mContext;
    private List<Home_TinTuc> mListTinTuc;

    public Home_TinTucAdapter(Context mContext, List<Home_TinTuc> mListTinTuc) {
        this.mContext = mContext;
        this.mListTinTuc = mListTinTuc;
    }

   /* public void setData(Context mContext,List<TinTuc> list){
        this.mContext = mContext;
        this.mListTinTuc = list;
        notifyDataSetChanged();
    }*/

    @NonNull
    @Override
    public Home_TinTucViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_home_item_tintuc, parent,false);
        return new Home_TinTucViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Home_TinTucViewHolder holder, int position) {
     Home_TinTuc tintuc = mListTinTuc.get((position));
    if (tintuc == null){
        return;
    }

    holder.tvTenTT.setText(tintuc.getTenTinTuc());
    holder.tvNguoiDg.setText(tintuc.getNguoiDang());
    //holder.tvNoiDung.setText(tintuc.getNoiDungTT());
    holder.imagerTinTuc.setImageResource(tintuc.getAnh());

    holder.layoutItemTintuc.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            onClickGoToNewsDetail(tintuc);
        }
    });

    }

   private void onClickGoToNewsDetail(Home_TinTuc tintuc){
        Intent intent = new Intent(mContext, Home_NewsDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_tintuc", tintuc);
        intent.putExtras(bundle);
        mContext.startActivity(intent);

    }


    public void release(){
        mContext = null;
    }
    @Override
    public int getItemCount() {
        if (mListTinTuc != null){
            return mListTinTuc.size();
        }
        return 0;
    }

    public class Home_TinTucViewHolder extends RecyclerView.ViewHolder{

        private CardView layoutItemTintuc;
        private TextView tvTenTT;
        private TextView tvNguoiDg;
        private TextView tvNoiDung;
        private ImageView imagerTinTuc;

        public Home_TinTucViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutItemTintuc = itemView.findViewById(R.id.layout_item_tintuc);
            tvTenTT = itemView.findViewById(R.id.textVtenTT);
            tvNguoiDg = itemView.findViewById(R.id.txvNguoiDang);
            //tvNoiDung = itemView.findViewById(R.id.tv_noidung_tt);
            imagerTinTuc = itemView.findViewById(R.id.imagerTinTuc);
        }
    }
}

