package com.minhhoang.nhom8_ltdd_tracuubds.menu.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.minhhoang.nhom8_ltdd_tracuubds.R;
import com.minhhoang.nhom8_ltdd_tracuubds.menu.UserDatabase.ApiService;
import com.minhhoang.nhom8_ltdd_tracuubds.menu.UserDatabase.DangKyDatabaseHelper;
import com.minhhoang.nhom8_ltdd_tracuubds.menu.UserDatabase.User;
import com.minhhoang.nhom8_ltdd_tracuubds.menu.UserDatabase.UserSession;
import com.minhhoang.nhom8_ltdd_tracuubds.menu.home.home_main_activity;
import com.minhhoang.nhom8_ltdd_tracuubds.menu.lookup.lookup_main_activity;
import com.minhhoang.nhom8_ltdd_tracuubds.menu.profile.profile_main_activity;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class login_login_Activity extends AppCompatActivity {
    private ImageButton back, login_with_google, login_with_facebook, login;
    private TextView login_error;
    private EditText login_username, login_password;
    private Button login_show_password;
    private DangKyDatabaseHelper registerDbHelper;
    private String admin_username = "admin";
    private String admin_password = "admin";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_login);

        registerDbHelper = new DangKyDatabaseHelper(this);
        login_username = findViewById(R.id.login_username);
        login_password = findViewById(R.id.login_password);
        login = findViewById(R.id.login_login_btn);



        create_button();
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
                String username = login_username.getText().toString();
                String password = login_password.getText().toString();


                if (username.equals(admin_username) && password.equals(admin_password)) {

                    UserSession.getInstance().setLoggedInUsername(username);


                    loadActivity(home_main_activity.class);
                }

                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://crew-beach-ferry-protective.trycloudflare.com/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();


                    ApiService apiService = retrofit.create(ApiService.class);

                    Map<String, String> params = new HashMap<>();
                    params.put("username", username);
                    params.put("password", password);

                    Call<User> call = apiService.auth(username, password);
                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if (response.isSuccessful()) {
                                User user = response.body();
                                if (user != null && user.isAuthenticated()) {
                                    // Đăng nhập thành công
                                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                                    UserSession.getInstance().setLoggedInUsername(username);
                                    loadActivity(home_main_activity.class);
                                } else {
                                    // Đăng nhập thất bại
                                    Toast.makeText(getApplicationContext(), "Đăng nhập thất bại. Kiểm tra lại tên đăng nhập và mật khẩu.", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                // Đăng nhập thất bại (lỗi từ API)
                                Toast.makeText(getApplicationContext(), "Đăng nhập thất bại. Kiểm tra lại kết nối internet.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {

                            Toast.makeText(getApplicationContext(), "Đăng nhập thất bại. Kiểm tra lại kết nối internet.", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {

                    Toast.makeText(getApplicationContext(), "Vui lòng nhập lại!", Toast.LENGTH_SHORT).show();
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
