package com.minhhoang.nhom8_ltdd_tracuubds.menu.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.minhhoang.nhom8_ltdd_tracuubds.FooterFragment;
import com.minhhoang.nhom8_ltdd_tracuubds.R;
import com.minhhoang.nhom8_ltdd_tracuubds.menu.lookup.lookup_main_activity;

public class home_main_activity extends AppCompatActivity {
    private ImageButton home_profile;
    private EditText home_sreach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);

        create_footer();
        create_content();
        create_main_feature();
    }

    private void create_main_feature() {
        home_profile = findViewById(R.id.home_profile);

        home_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadActivity(home_main_activity.class);
            }
        });
    }

    private void create_content() {
        loadFragment(R.id.home_content, new home_content_fragment());
    }

    private void create_footer() {
        loadFragment(R.id.home_main_footer, new FooterFragment());
    }

    private void loadActivity(Class<?> targetActivityClass) {
        Intent intent = new Intent(this, targetActivityClass);
        startActivity(intent);
    }

    private boolean loadFragment(int layout_id, Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(layout_id, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}