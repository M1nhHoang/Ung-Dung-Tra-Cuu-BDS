package com.minhhoang.nhom8_ltdd_tracuubds.menu.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.minhhoang.nhom8_ltdd_tracuubds.R;
import com.minhhoang.nhom8_ltdd_tracuubds.menu.UserDatabase.DangKyDatabaseHelper;
import com.minhhoang.nhom8_ltdd_tracuubds.menu.UserDatabase.SendData;
import com.minhhoang.nhom8_ltdd_tracuubds.menu.UserDatabase.User;
import com.minhhoang.nhom8_ltdd_tracuubds.menu.login.login_login_Activity;
import com.minhhoang.nhom8_ltdd_tracuubds.menu.login.login_main_Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class register_main_activity extends AppCompatActivity {
    private ImageButton back;
    private Button dieukhoan_btn;
    private Button show_password_btn;
    private Button register_btn;
    private EditText username_input, email_input, password_input, name_input;

    private DangKyDatabaseHelper registerDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_main);

        registerDbHelper = new DangKyDatabaseHelper(this);

        create_back_button();
        create_register_feature();
        create_sub_register();
    }

    private void create_register_feature(){
        username_input = findViewById(R.id.register_username);
        name_input = findViewById(R.id.register_name);
        email_input = findViewById(R.id.register_email);
        password_input = findViewById(R.id.register_password);

        register_btn = findViewById(R.id.register_btn);

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = username_input.getText().toString();
                String name = name_input.getText().toString();
                String  email  = email_input.getText().toString();
                String  password  = password_input.getText().toString();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                    return;
                }
                User user = new User(username, name, email, password);
                new SendData().execute(user);

                long newRowId = registerDbHelper.addUser(username,password,email,name);
                if (newRowId != -1) {
                    Toast.makeText(getApplicationContext(),
                            String.format("Bạn đã đăng ký thành công với tài khoản là: %s.", username),
                            Toast.LENGTH_LONG).show();

                    loadActivity(login_login_Activity.class);
                } else {
                    Toast.makeText(getApplicationContext(), "Đăng ký không thành công. Vui lòng thử lại.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void create_sub_register() {
        show_password_btn = findViewById(R.id.register_show_password);
        dieukhoan_btn = findViewById(R.id.register_show_dieukhoan);
        password_input = findViewById(R.id.register_password);


        dieukhoan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadActivity(register_dieukhoan_activity.class);
            }
        });

        show_password_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (show_password_btn.getText().toString().equals("Hiện mật khẩu")) {
                    password_input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                    show_password_btn.setText("Ẩn mật khẩu");
                }
                else {
                    password_input.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

                    show_password_btn.setText("Hiện mật khẩu");
                }
            }
        });
    }

    private void create_back_button() {
        back = findViewById(R.id.register_back_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadActivity(login_main_Activity.class);
            }
        });
    }

    private void loadActivity(Class<?> targetActivityClass) {
        Intent intent = new Intent(this, targetActivityClass);
        startActivity(intent);
    }
}