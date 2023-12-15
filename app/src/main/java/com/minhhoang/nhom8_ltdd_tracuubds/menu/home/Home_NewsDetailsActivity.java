package com.minhhoang.nhom8_ltdd_tracuubds.menu.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.minhhoang.nhom8_ltdd_tracuubds.R;

public class Home_NewsDetailsActivity extends AppCompatActivity {
    Button btnTroLai;
    @Override
    protected  void onCreate (Bundle savaInstanceState) {
        super.onCreate(savaInstanceState);
        setContentView((R.layout.activity_home_news_details));

        btnTroLai = findViewById(R.id.btn_trolai_detail);
        Intent intent = getIntent();
        String data = intent.getStringExtra("key");
        btnTroLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(data !=null){
                    Intent intent = new Intent(Home_NewsDetailsActivity.this, Home_DsTinTucActivity.class);
                    startActivity(intent);
               }else {
                   Intent intent = new Intent(Home_NewsDetailsActivity.this, Home_MainActivity.class);
                   startActivity(intent);
               }
            }
        });

        Button btnLike = findViewById(R.id.buttonLike);
        // Trạng thái like hiện tại

        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle trạng thái like
                boolean isLiked = false;
                isLiked = !isLiked;

                // Cập nhật giao diện người dùng dựa trên trạng thái like mới
                updateLikeButton(isLiked);
            }
        });

        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        Home_TinTuc tinTuc = (Home_TinTuc) bundle.get("object_tintuc");
        TextView  tvTenTT = findViewById(R.id.textVtenTT);
        TextView tvNguoiDg = findViewById(R.id.txvNguoiDang);
        TextView  tvNoiDung = findViewById(R.id.tv_noidung_tt);
        ImageView imagerTinTuc = findViewById(R.id.imagerTinTuc);
        tvTenTT.setText(tinTuc.getTenTinTuc());
        tvNguoiDg.setText(tinTuc.getNguoiDang());
        tvNoiDung.setText(tinTuc.getNoiDungTT());
        imagerTinTuc.setImageResource(tinTuc.getAnh());
    }

    private void updateLikeButton(boolean isLiked) {
        Button btnLike = findViewById(R.id.buttonLike);

        if (isLiked) {
            // Thay đổi màu sắc hoặc hình ảnh của nút khi được like
            btnLike.setTextColor(ContextCompat.getColor(this, android.R.color.holo_blue_dark));
            // Các thao tác khác khi like
        } else {
            // Thay đổi màu sắc hoặc hình ảnh của nút khi không được like
            btnLike.setTextColor(ContextCompat.getColor(this, android.R.color.black));
            // Các thao tác khác khi không like
        }
    }
}
