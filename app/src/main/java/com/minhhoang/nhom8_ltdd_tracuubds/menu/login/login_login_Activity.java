package com.minhhoang.nhom8_ltdd_tracuubds.menu.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.minhhoang.nhom8_ltdd_tracuubds.R;
import com.minhhoang.nhom8_ltdd_tracuubds.menu.lookup.lookup_main_activity;

public class login_login_Activity extends AppCompatActivity {
    private ImageButton back, login_with_google, login_with_facebook, login;
    private TextView login_error;
    private EditText login_username, login_password;
    private Button login_show_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_login);

        create_button();
    }

    private boolean login_service() {
        String sample_username = "admin", sample_password = "admin";

        if (login_username.getText().toString().equals(sample_username) && login_password.getText().toString().equals(sample_password)){
            return  true;
        }
        else {
            return false;
        }
    }

    private void create_button() {
        back = findViewById(R.id.login_login_back);
        login = findViewById(R.id.login_login_btn);
        login_with_google  = findViewById(R.id.login_with_google);
        login_with_facebook = findViewById(R.id.login_with_facebook);
        login_show_password = findViewById(R.id.login_show_password);
        login_error = findViewById(R.id.login_error);
        login_username = findViewById(R.id.login_username);
        login_password = findViewById(R.id.login_password);

        login_error.setVisibility(View.INVISIBLE);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (login_service()) {
                    loadActivity(lookup_main_activity.class);
                }
                else {
                    login_error.setVisibility(View.VISIBLE);
                }
            }
        });

        login_show_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (login_show_password.getText().toString().equals("Hiện mật khẩu")) {
                    login_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    login_show_password.setText("Ẩn mật khẩu");
                }
                else {
                    login_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    login_show_password.setText("Hiện mật khẩu");
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadActivity(login_main_Activity.class);
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