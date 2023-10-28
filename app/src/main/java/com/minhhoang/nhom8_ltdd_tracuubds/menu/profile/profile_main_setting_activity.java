package com.minhhoang.nhom8_ltdd_tracuubds.menu.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.minhhoang.nhom8_ltdd_tracuubds.R;
import com.minhhoang.nhom8_ltdd_tracuubds.menu.home.home_main_activity;

public class profile_main_setting_activity extends AppCompatActivity {
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_hoso_chinhsua);
        create_back_button();
    }

    private void create_back_button() {
        back = findViewById(R.id.profile_setting_button_back);
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