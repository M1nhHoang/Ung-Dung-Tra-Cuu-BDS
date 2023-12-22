package com.minhhoang.nhom8_ltdd_tracuubds.menu.lookup;

import android.content.Intent;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AsyncGetBound {
    private final OkHttpClient client = new OkHttpClient();
    public GoogleMap map;

    public interface CompletionCallback {
        void onComplete();
    }

    public void getBound(String url, CompletionCallback completionCallback) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        // Sử dụng enqueue để thực hiện yêu cầu bất đồng bộ
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Xử lý lỗi khi yêu cầu thất bại
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Xử lý kết quả khi nhận được phản hồi
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    // Xử lý dữ liệu trả về ở đây
                    try {
                        pareJson(responseBody);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    // Xử lý lỗi khi nhận được phản hồi không thành công
                    System.out.println("Error: " + response.code() + " - " + response.message());
                }
            }

            private void pareJson(String jsonString) throws JSONException {
                JSONObject json = new JSONObject(jsonString);
                JSONObject data = json.getJSONObject("data");
                JSONObject geometry = data.getJSONObject("geometry");
                JSONArray coordinates = geometry.getJSONArray("coordinates");

                System.out.println("\n\n\n\n\n\n"+coordinates+"\n\n\n\n\n\n");

            }
        });
    }

    public void test() {
        LatLng centerOfMap = this.map.getCameraPosition().target;
        System.out.println(centerOfMap.latitude+","+centerOfMap.longitude);
    }
}
