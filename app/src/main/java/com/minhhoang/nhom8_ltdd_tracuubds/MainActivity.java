 
package com.minhhoang.nhom8_ltdd_tracuubds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.minhhoang.nhom8_ltdd_tracuubds.menu.login.login_main_Activity;
import com.minhhoang.nhom8_ltdd_tracuubds.menu.lookup.lookup_main_activity;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadActivity(login_main_Activity.class);
    }

    private void loadActivity(Class<?> targetActivityClass) {
        Intent intent = new Intent(this, targetActivityClass);
        startActivity(intent);
    }

}