package com.minhhoang.nhom8_ltdd_tracuubds.menu.lookup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.minhhoang.nhom8_ltdd_tracuubds.R;

public class lookup_sreach_activity extends AppCompatActivity {
    private ImageButton diachiButton;
    private ImageButton toadoButton;
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lookup_sreach);

        create_switch_button();
        create_back_button();
    }

    private void create_back_button() {
        back = findViewById(R.id.lookup_sreach_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadActivity(lookup_main_activity.class);
            }
        });
    }

    private void create_switch_button() {
        loadFragment(new lookup_by_diachi_Fragment());

        diachiButton = findViewById(R.id.diachi);
        toadoButton = findViewById(R.id.toado);
        diachiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diachiButton.setImageResource(R.drawable.lookup_sreach_enable_diachi_button);
                toadoButton.setImageResource(R.drawable.lookup_sreach_disable_toado_button);
                loadFragment(new lookup_by_diachi_Fragment());
            }
        });

        toadoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toadoButton.setImageResource(R.drawable.lookup_sreach_enable_toado_button);
                diachiButton.setImageResource(R.drawable.lookup_sreach_disable_diachi_button);
                loadFragment(new lookup_by_toado_Fragment());
            }
        });
    }

    private void loadActivity(Class<?> targetActivityClass) {
        Intent intent = new Intent(this, targetActivityClass);
        startActivity(intent);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.lookup_frame, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}