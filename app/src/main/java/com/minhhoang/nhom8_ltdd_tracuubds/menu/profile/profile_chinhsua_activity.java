package com.minhhoang.nhom8_ltdd_tracuubds.menu.profile;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.minhhoang.nhom8_ltdd_tracuubds.R;
import com.minhhoang.nhom8_ltdd_tracuubds.menu.UserDatabase.DangKyDatabaseHelper;

public class profile_chinhsua_activity extends AppCompatActivity {
    private ImageButton back;
    private EditText txtbHoten, txtbUsername, txtbPassword, txtbPasswordConfirm, txtbEmail;
    private Button button_editprofile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_hoso_chinhsua);
        // Khởi tạo các EditText
        txtbHoten = findViewById(R.id.txtbHoten);
        txtbUsername = findViewById(R.id.txtbUsername);
        txtbPassword = findViewById(R.id.txtbPassword);
        txtbPasswordConfirm = findViewById(R.id.txtbPasswordConfirm);
        txtbEmail = findViewById(R.id.txtbEmail);
        create_back_button();

        // Thêm sự kiện cho nút "Thay đổi"
        button_editprofile = findViewById(R.id.button_editProfile);
        button_editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gọi phương thức để cập nhật cơ sở dữ liệu từ dữ liệu nhập vào từ các EditText
                updateDatabase();
                updateTenText();
            }
        });

    }
    private void updateTenText() {
        String hoten = txtbHoten.getText().toString();

        // Tạo Intent để chuyển dữ liệu giữa các Activity
        Intent intent = new Intent(profile_chinhsua_activity.this, profile_main_activity.class);

        // Đặt dữ liệu trong Intent
        intent.putExtra("hoten", hoten);

        // Chuyển đến trang profile_hoso_main
        startActivity(intent);
    }
    private void updateDatabase() {
        // Lấy dữ liệu từ các EditText
        String username = txtbUsername.getText().toString();
        String email = txtbEmail.getText().toString();
        String newPassword = txtbPassword.getText().toString();  // Có thể thêm kiểm tra mật khẩu mới và xác nhận mật khẩu ở đây

        // Kiểm tra nếu có dữ liệu đầy đủ để cập nhật
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(newPassword)) {
            // Tạo đối tượng DangKyDatabaseHelper
            DangKyDatabaseHelper dangKyDbHelper = new DangKyDatabaseHelper(this);

            // Thực hiện cập nhật trong cơ sở dữ liệu
            boolean updateSuccess = dangKyDbHelper.updateUser(username, email, newPassword);

            // Kiểm tra kết quả cập nhật và thông báo cho người dùng
            if (updateSuccess) {
                Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }
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
