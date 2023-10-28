package com.minhhoang.nhom8_ltdd_tracuubds.menu.profile;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.minhhoang.nhom8_ltdd_tracuubds.R;

import java.util.ArrayList;
import java.util.List;

public class Thongbao_main extends AppCompatActivity{
    private ListView listView;
    private List<Hoso_thongbao_item> tbtext;
    private ImageButton back;
    private Adapter_LvThongbao adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_hoso_thongbao);

        listView = findViewById(R.id.today);

        tbtext = new ArrayList<>();
        tbtext.add(new Hoso_thongbao_item("Chúng tôi vừa có 5 căn hộ chung cư mới ở khu vực quận...\n" +
                " phù hợp với tiêu chí tìm kiếm của bạn. Hãy xem ngay!"));
        tbtext.add(new Hoso_thongbao_item("Chúng tôi vừa có 5 căn hộ chung cư mới ở khu vực quận...\n" +
                " phù hợp với tiêu chí tìm kiếm của bạn. Hãy xem ngay!"));
        adapter = new Adapter_LvThongbao(this, tbtext);
        listView.setAdapter(adapter);

        create_back_button();
    }

    private void create_back_button() {
        back = findViewById(R.id.profile_notification_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadActivity(profile_main_activity.class);
            }
        });
    }

    private void loadActivity(Class<?> targetActivityClass) {
        Intent intent = new Intent(this, targetActivityClass);
        startActivity(intent);
    }
}
