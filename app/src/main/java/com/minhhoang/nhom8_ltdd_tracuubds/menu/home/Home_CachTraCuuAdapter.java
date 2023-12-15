package com.minhhoang.nhom8_ltdd_tracuubds.menu.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.minhhoang.nhom8_ltdd_tracuubds.R;

import java.util.List;

public class Home_CachTraCuuAdapter extends RecyclerView.Adapter<Home_CachTraCuuAdapter.Home_CachTraCuuViewHolder> {
    private Context mContext;
    private List<Home_TinTuc> mListTinTuc;

    public Home_CachTraCuuAdapter(Context mContext, List<Home_TinTuc> mListTinTuc) {
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
    public Home_CachTraCuuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_home_item_cactracuu, parent,false);
        return new Home_CachTraCuuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Home_CachTraCuuViewHolder holder, int position) {
        Home_TinTuc tintuc = mListTinTuc.get((position));
        if (tintuc == null){
            return;
        }

        holder.tvTenCTC.setText(tintuc.getTenTinTuc());
        holder.imagerCTC.setImageResource(tintuc.getAnh());

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

    public class Home_CachTraCuuViewHolder extends RecyclerView.ViewHolder{

        private TextView tvTenCTC;
        private ImageView imagerCTC;

        public Home_CachTraCuuViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenCTC = itemView.findViewById(R.id.tvTenCTC);
            imagerCTC = itemView.findViewById(R.id.imagerCTC);
        }
    }
}
