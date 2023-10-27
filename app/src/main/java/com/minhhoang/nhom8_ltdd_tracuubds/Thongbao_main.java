package com.minhhoang.nhom8_ltdd_tracuubds;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Thongbao_main extends AppCompatActivity{
    private ListView listView;
    private List<Hoso_thongbao_item> tbtext;
    private Adapter_LvThongbao adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hoso_thongbao);

        listView = findViewById(R.id.today);

        tbtext = new ArrayList<>();
        tbtext.add(new Hoso_thongbao_item("Chúng tôi vừa có 5 căn hộ chung cư mới ở khu vực quận...\n" +
                " phù hợp với tiêu chí tìm kiếm của bạn. Hãy xem ngay!"));
        tbtext.add(new Hoso_thongbao_item("Chúng tôi vừa có 5 căn hộ chung cư mới ở khu vực quận...\n" +
                " phù hợp với tiêu chí tìm kiếm của bạn. Hãy xem ngay!"));
        adapter = new Adapter_LvThongbao(this, tbtext);
        listView.setAdapter(adapter);
    }
}
