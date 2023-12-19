package com.minhhoang.nhom8_ltdd_tracuubds.menu.UserDatabase;

import android.os.AsyncTask;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SendData extends AsyncTask<User, Void, Boolean> {

    @Override
    protected Boolean doInBackground(User... users) {
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://crew-beach-ferry-protective.trycloudflare.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiService apiService = retrofit.create(ApiService.class);

            Call<Void> call = apiService.registerUser(users[0]);
            retrofit2.Response<Void> response = call.execute();

            if (response.isSuccessful()) {
                return true;
            } else {
                Log.e("SendData", "HTTP response code: " + response.code());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("SendData", "Exception: " + e.getMessage());
        }

        return false;
    }

    @Override
    protected void onPostExecute(Boolean success) {
        if (success) {
            Log.d("SendData", "Dữ liệu đã được gửi thành công ");
        } else {
            Log.e("SendData", "Gửi dữ liệu không thành công");
        }
    }
}
