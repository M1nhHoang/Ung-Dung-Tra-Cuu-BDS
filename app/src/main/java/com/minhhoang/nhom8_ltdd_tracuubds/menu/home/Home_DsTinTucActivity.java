package com.minhhoang.nhom8_ltdd_tracuubds.menu.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.minhhoang.nhom8_ltdd_tracuubds.R;

import java.util.ArrayList;
import java.util.List;

public class Home_DsTinTucActivity extends AppCompatActivity {
    Button btnTrolai;
    private TextView tvSoLgTT;
    private RecyclerView rcvTinTuc;
    private Home_DanhSachTinTucAdapter danhSachTinTucAdapter;
    @Override
    protected  void onCreate (Bundle savaInstanceState) {
        super.onCreate(savaInstanceState);
        setContentView((R.layout.activity_home_news_list));
        btnTrolai = findViewById(R.id.btn_trolai);
        tvSoLgTT = findViewById(R.id.tvSoLgTT);
        btnTrolai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_DsTinTucActivity.this, Home_MainActivity.class);
                startActivity(intent);
            }
        });
        DanhSachTintuc();
        updateItemCount();

    }

   private void updateItemCount(){
        int itemCount = danhSachTinTucAdapter.getItemCount();
        tvSoLgTT.setText("Có " +itemCount+ " tin mới");
    }
    private void DanhSachTintuc(){
        rcvTinTuc = findViewById(R.id.rcv_DsTinTuc);
        danhSachTinTucAdapter = new Home_DanhSachTinTucAdapter(this,getLisTinTuc());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false );
        rcvTinTuc.setLayoutManager(linearLayoutManager);

        //tinTucAdapter.setData(getLisTinTuc());
        rcvTinTuc.setAdapter(danhSachTinTucAdapter);

    }

    private List<Home_TinTuc> getLisTinTuc(){
        List<Home_TinTuc> list = new ArrayList<>();
        list.add(new Home_TinTuc("Đà Nẵng sẽ phê duyệt 9 quy hoạch phân khu đô thị trong năm 2023","Tiến độ quy hoạch các phân khu đô thị chậm, khiến quyền lợi của người " +
                "dân bị ảnh hưởng. Thành phố Đà Nẵng đặt mục tiêu hoàn thành phê duyệt 9 quy hoạch phân khu đô thị.","Minh Hoàng",R.drawable.home_tintuc2));
        list.add(new Home_TinTuc("Đường sẽ mở theo quy hoạch ở phường Hoà Phát, Cẩm Lệ, Đà Nẵng","Những tuyến đường sẽ mở theo quy hoạch ở phường Hoà Phát, Cẩm Lệ, Đà Nẵng đáng chú ý nhất.\n" +
                "28/04/2023 Đường sẽ mở theo quy hoạch ở phường hòa tọ tây, Cẩm Lệ, Đà nẵng","Ngọc Hiếu ",R.drawable.home_tintuc1_2));
        list.add(new Home_TinTuc("Bộ trưởng Nguyễn Chí Dũng họp về Khung định hướng Quy hoạch vùng Đông Nam Bộ thời kỳ 2021-2030, tầm...","Ngày 16/8/2023, Bộ trưởng Bộ Kế hoạch và Đầu tư" +
                " Nguyễn Chí Dũng chủ trì cuộc họp về Khung định hướng Quy hoạch vùng Đông Nam Bộ thời kỳ 2021-2030, tầm nhìn đến năm 2050. Tham dự cuộc họp có đại diện các địa phương thuô...","Ngọc Hiếu ",R.drawable.home_tintuc3));
        list.add(new Home_TinTuc("Đường sẽ mở theo quy hoạch ở phường Hoà Phát, Cẩm Lệ, Đà Nẵng","Tiến độ quy hoạch các phân khu đô thị chậm, khiến quyền lợi của người dân bị ảnh hưởng. Thành phố Đà Nẵng đặt" +
                " mục tiêu hoàn thành phê duyệt 9 quy hoạch phân khu đô thị.","Ngọc Hiếu ",R.drawable.home_tintuc1_2));
        list.add(new Home_TinTuc("Đường sẽ mở theo quy hoạch ở phường Hoà Phát, Cẩm Lệ, Đà Nẵng","Tiến độ quy hoạch các phân khu đô thị chậm, khiến quyền lợi của người dân bị ảnh hưởng. Thành phố Đà Nẵng đặt mục tiêu hoàn thành" +
                " phê duyệt 9 quy hoạch phân khu đô thị.","Ngọc Hiếu ",R.drawable.home_tintuc1_2));
        list.add(new Home_TinTuc("Đường sẽ mở theo quy hoạch ở phường Hoà Phát, Cẩm Lệ, Đà Nẵng","Tiến độ quy hoạch các phân khu đô thị chậm, khiến quyền lợi của người dân bị ảnh hưởng. Thành phố Đà Nẵng đặt mục tiêu hoàn thành " +
                "phê duyệt 9 quy hoạch phân khu đô thị.","Ngọc Hiếu ",R.drawable.home_tintuc1_2));
        list.add(new Home_TinTuc("Đường sẽ mở theo quy hoạch ở phường Hoà Phát, Cẩm Lệ, Đà Nẵng","Tiến độ quy hoạch các phân khu đô thị chậm, khiến quyền lợi của người dân bị ảnh hưởng. Thành phố Đà Nẵng đặt mục tiêu hoàn thành" +
                " phê duyệt 9 quy hoạch phân khu đô thị.","Ngọc Hiếu ",R.drawable.home_tintuc1_2));
        return list;
    }
}
