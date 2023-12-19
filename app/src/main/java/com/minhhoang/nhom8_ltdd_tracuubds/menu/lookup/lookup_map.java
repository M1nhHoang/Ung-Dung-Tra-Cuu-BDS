package com.minhhoang.nhom8_ltdd_tracuubds.menu.lookup;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.minhhoang.nhom8_ltdd_tracuubds.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class lookup_map extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;
    private FusedLocationProviderClient fusedLocationClient;
    private SupportMapFragment mapFragment;
    private String lookup_URL = "";
    private Polyline borderPolyline;
    private Polygon polygon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lookup_map);

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        // Lấy giá trị từ Intent
        Intent intent = getIntent();
        if (intent != null) {
            String coordinates = intent.getStringExtra("Coordinates");

            if (!coordinates.equals("")) {
                double x = Double.parseDouble(coordinates.split(",")[0].replace(" ", ""));
                double y = Double.parseDouble(coordinates.split(",")[1].replace(" ", ""));
                getMap(x, y);
            }
            else {
                // Khởi tạo FusedLocationProviderClient
                fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

                // Kiểm tra và yêu cầu quyền truy cập
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    // Nếu quyền truy cập đã được cấp, lấy vị trí hiện tại
                    getLastLocation();
                } else {
                    // Nếu quyền truy cập chưa được cấp, yêu cầu quyền
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
                }
            }
        }
    }
    private void getMap(double x, double y) {
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                LatLng initialLatLng = new LatLng(x, y);

                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(initialLatLng, 20.0f));

                View centerDot = findViewById(R.id.center_dot);

                googleMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
                    @Override
                    public void onCameraMove() {
                        LatLng centerOfMap = googleMap.getCameraPosition().target;

                        // Cập nhật vị trí của dấu chấm
                        Point screenPosition = googleMap.getProjection().toScreenLocation(centerOfMap);
                        centerDot.setX(screenPosition.x - centerDot.getWidth() / 2);
                        centerDot.setY(screenPosition.y - centerDot.getHeight() / 2);

                        // Hiển thị tọa độ
                        Log.d("Map", "onCameraMove: " + centerOfMap.latitude + ", " + centerOfMap.longitude);
                        // Vẽ đường viền xung quanh tọa độ hiện tại


                        // Tô đậm phần nằm dưới dấu chấm
                        drawFilledAreaUnderCenterDot(googleMap, centerOfMap);
                    }
                });
            }
        });
    }

    private void drawFilledAreaUnderCenterDot(GoogleMap googleMap, LatLng location) {

        if (polygon != null) {
            polygon.remove();
        }

        double squareSize = 0.002;
        // Tạo một đối tượng PolygonOptions để vẽ đa giác hình vuông
        PolygonOptions polygonOptions = new PolygonOptions()
                .add(new LatLng(location.latitude - squareSize, location.longitude - squareSize))
                .add(new LatLng(location.latitude + squareSize, location.longitude - squareSize))
                .add(new LatLng(location.latitude + squareSize, location.longitude + squareSize))
                .add(new LatLng(location.latitude - squareSize, location.longitude + squareSize))
                .fillColor(Color.argb(100, 255, 0, 0)); // Màu sắc của phần tô đậm (đỏ với độ mờ 100)
        // Vẽ Polygon trên bản đồ và lưu vào biến polygon
        polygon = googleMap.addPolygon(polygonOptions);
    }
    private void get_bound() {
        try {
            // Mở kết nối
            URL obj = new URL(this.lookup_URL);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

            // Cấu hình yêu cầu
            connection.setRequestMethod("GET");

            // Đọc phản hồi từ máy chủ
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                // In dữ liệu nhận được
                System.out.println("Response Data: " + response.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getLastLocation() {
        // Check if permission is still granted before attempting to get the last location
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            // Get the last location
            fusedLocationClient.getLastLocation()
                    .addOnCompleteListener(this, new OnCompleteListener<Location>() {
                        @Override
                        public void onComplete(@NonNull Task<Location> task) {
                            if (task.isSuccessful() && task.getResult() != null) {
                                // Location retrieved successfully
                                Location lastLocation = task.getResult();
                                double latitude = lastLocation.getLatitude();
                                double longitude = lastLocation.getLongitude();
                                // Use latitude and longitude as needed
                                getMap(latitude, longitude);
                            } else {
                                // Unable to get the last location, handle as needed
                            }
                        }
                    });
        } else {
            // Permission is not granted, handle as needed (e.g., show a message to the user)
        }
    }

    // Handle permission request results
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, get the last location
                getLastLocation();
            } else {
                // Permission not granted, handle as needed (e.g., show a message to the user)
            }
        }
    }
}