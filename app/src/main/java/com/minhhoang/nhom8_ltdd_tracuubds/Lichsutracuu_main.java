package com.minhhoang.nhom8_ltdd_tracuubds;

import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Lichsutracuu_main extends AppCompatActivity {
    private ListView listView;
    private List<Hoso_Ls_Item> hsitems;
    private Adapter_LvLichsu adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hoso_lichsutracuu);

        listView = findViewById(R.id.listview_lichsu);

        hsitems = new ArrayList<>();
        hsitems.add(new Hoso_Ls_Item(R.drawable.bds_1, "1 căn khối đế kinh doanh The Ori Garden không sử dụng bán rẻ", "Hàng ngoại giao không có nhu cầu sử dụng nhượng lại căn vip Shophouse khối đế view hồ bơi, Vị trí kinh doanh tốt, cho thuê 10 - 15tr/tháng."));
        hsitems.add(new Hoso_Ls_Item(R.drawable.bds_2, "Căn Hộ Cao Cấp Sun Cosmo View Sông Hàn, View Biển", "Sun Cosmo Residence có vị trí tọa lạc tại tuyến đường Trần Thị Lý, phường Bắc Mỹ Phú, quận Ngũ Hành Sơn, thành phố Đà Nẵng, 1 dự án mới của chủ đầu tư Sungroup. Dự án sở hữu..."));

        adapter = new Adapter_LvLichsu(this, hsitems);
        listView.setAdapter(adapter);
    }
}
