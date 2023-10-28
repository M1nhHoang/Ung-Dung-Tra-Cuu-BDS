
package com.minhhoang.nhom8_ltdd_tracuubds.menu.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import com.minhhoang.nhom8_ltdd_tracuubds.FooterFragment;
import com.minhhoang.nhom8_ltdd_tracuubds.R;
import com.minhhoang.nhom8_ltdd_tracuubds.menu.home.home_main_activity;
import com.minhhoang.nhom8_ltdd_tracuubds.menu.login.login_main_Activity;
import com.minhhoang.nhom8_ltdd_tracuubds.menu.lookup.lookup_main_activity;

public class profile_main_activity extends AppCompatActivity {
    private ListView listView;
    private List<ListItem_main> items;
    private Adapter_LvMain adapter;
    private ImageButton back, profile_setting_button, profile_history_button, profile_notification_button, profile_support_button, profile_logout;
    private Button profile_main_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_hoso_main);

        createFooter();
        create_back_button();
        create_button();
    }

    private void create_button() {
        profile_main_setting = findViewById(R.id.profile_main_setting);
        profile_notification_button = findViewById(R.id.profile_notification_button);
        profile_support_button = findViewById(R.id.profile_support_button);
        profile_logout = findViewById(R.id.profile_logout);
        profile_setting_button = findViewById(R.id.profile_setting_button);
        profile_history_button = findViewById(R.id.profile_history_button);

        profile_main_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadActivity(profile_main_setting_activity.class);
            }
        });

        profile_notification_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadActivity(Thongbao_main.class);
            }
        });

        profile_support_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadActivity(Hotro_main.class);
            }
        });

        profile_history_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadActivity(Lichsutracuu_main.class);
            }
        });

        profile_setting_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadActivity(profile_main_setting_activity.class);
            }
        });

        profile_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadActivity(login_main_Activity.class);
            }
        });
    }

    private void createFooter() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.profile_main_footer, new FooterFragment())
                .commit();
    }

    private void create_back_button() {
        back = findViewById(R.id.profile_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadActivity(home_main_activity.class);
            }
        });
    }

    private void loadActivity(Class<?> targetActivityClass) {
        Intent intent = new Intent(this, targetActivityClass);
        startActivity(intent);
    }
}