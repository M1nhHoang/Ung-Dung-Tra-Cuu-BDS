
package com.minhhoang.nhom8_ltdd_tracuubds;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private List<ListItem_main> items;
    private Adapter_LvMain adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hoso_main);

        items = new ArrayList<>();
        items.add(new ListItem_main(R.drawable.hoso_caidat, "Cài đặt"));
        items.add(new ListItem_main(R.drawable.hoso_lichsutracuu, "Lịch sử tra cứu"));
        items.add(new ListItem_main(R.drawable.hoso_thongbao, "Thông báo"));
        items.add(new ListItem_main(R.drawable.hoso_hotro, "Hỗ trợ"));
        // Thêm nhiều mục khác nếu cần

        // Khởi tạo adapter với dữ liệu
        adapter = new Adapter_LvMain(this, items);

        // Gán adapter cho ListView
        listView = findViewById(R.id.menuitem);
        listView.setAdapter(adapter);
    }
}