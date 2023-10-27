package com.minhhoang.nhom8_ltdd_tracuubds.menu.lookup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.minhhoang.nhom8_ltdd_tracuubds.FooterFragment;
import com.minhhoang.nhom8_ltdd_tracuubds.R;

public class lookup_main_activity extends AppCompatActivity {
    private ImageButton let_sreach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lookup_main);

        createFooter();

        let_sreach = findViewById(R.id.tra_cuu_button);
        let_sreach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadActivity(lookup_sreach_activity.class);
            }
        });
    }

    private void createFooter() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_footer, new FooterFragment())
                .commit();
    }

    private void loadActivity(Class<?> targetActivityClass) {
        Intent intent = new Intent(this, targetActivityClass);
        startActivity(intent);
    }
}