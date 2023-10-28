package com.minhhoang.nhom8_ltdd_tracuubds.menu.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.minhhoang.nhom8_ltdd_tracuubds.R;
import com.minhhoang.nhom8_ltdd_tracuubds.menu.register.register_main_activity;

public class login_main_Activity extends AppCompatActivity {
    private ImageButton start_app, login_with_google, login_with_facebook;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        create_button();
    }

    private void create_button() {
        start_app = findViewById(R.id.login_main_start);
        login_with_google  = findViewById(R.id.login_with_google1);
        login_with_facebook = findViewById(R.id.login_with_facebook1);
        register = findViewById(R.id.login_main_register);
        start_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadActivity(login_login_Activity.class);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadActivity(register_main_activity.class);
            }
        });

        login_with_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext()
                        , "Chức năng chưa được hỗ trợ!"
                        , Toast.LENGTH_SHORT).show();
            }
        });

        login_with_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext()
                        , "Chức năng chưa được hỗ trợ!"
                        , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadActivity(Class<?> targetActivityClass) {
        Intent intent = new Intent(this, targetActivityClass);
        startActivity(intent);
    }
}