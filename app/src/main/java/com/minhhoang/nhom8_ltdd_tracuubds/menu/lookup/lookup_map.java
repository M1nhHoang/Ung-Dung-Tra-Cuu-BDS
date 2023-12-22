package com.minhhoang.nhom8_ltdd_tracuubds.menu.lookup;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.minhhoang.nhom8_ltdd_tracuubds.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class lookup_map extends AppCompatActivity implements OnMapReadyCallback {
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;
    private FusedLocationProviderClient fusedLocationClient;
    private SupportMapFragment mapFragment;
    private String lookup_URL = "https://know-licking-carter-exam.trycloudflare.com/perform_automation/";
    private Button buttonGetPosition;
    private GoogleMap map;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lookup_map);

        buttonGetPosition = findViewById(R.id.button_get_potition);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

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
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
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

                LatLng centerOfMap = googleMap.getCameraPosition().target;

                Point screenPosition = googleMap.getProjection().toScreenLocation(centerOfMap);
                centerDot.setX(screenPosition.x - centerDot.getWidth() / 2);
                centerDot.setY(screenPosition.y - centerDot.getHeight() / 2);

                buttonGetPosition.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        LatLng centerOfMap = googleMap.getCameraPosition().target;

                        AsyncGetBound async = new AsyncGetBound();
                        async.map = googleMap;

                        double length = generateRandomNumber(0.00005, 0.0001);
                        double width = generateRandomNumber(0.00005, 0.0001);

                        double rotationAngle = generateRandomNumber(0, 360);

                        // Thêm Polygon vào bản đồ
                        drawRectangle(centerOfMap, length, width, (float) rotationAngle);
                    }

                    private void drawRectangle(LatLng centerPoint, double length, double width, float rotationAngle) {
                        // Tạo PolygonOptions
                        PolygonOptions polygonOptions = new PolygonOptions();

                        // Tính toán các đỉnh của Polygon dựa trên tâm, chiều dài và chiều rộng
                        double halfLength = length / 2;
                        double halfWidth = width / 2;

                        // Xoay các điểm của hình vuông
                        LatLng point1 = rotatePoint(centerPoint, centerPoint.latitude + halfLength, centerPoint.longitude - halfWidth, rotationAngle);
                        LatLng point2 = rotatePoint(centerPoint, centerPoint.latitude + halfLength, centerPoint.longitude + halfWidth, rotationAngle);
                        LatLng point3 = rotatePoint(centerPoint, centerPoint.latitude - halfLength, centerPoint.longitude + halfWidth, rotationAngle);
                        LatLng point4 = rotatePoint(centerPoint, centerPoint.latitude - halfLength, centerPoint.longitude - halfWidth, rotationAngle);

                        // Thêm các điểm đã xoay vào PolygonOptions
                        polygonOptions.add(point1, point2, point3, point4);

                        // Đặt màu sắc cho Polygon (ví dụ: màu xanh)
                        polygonOptions.strokeColor(Color.BLUE);
                        polygonOptions.fillColor(Color.argb(128, 0, 0, 255));

                        // Thêm Polygon vào bản đồ
                        Polygon polygon = googleMap.addPolygon(polygonOptions);
                    }

                    private LatLng rotatePoint(LatLng center, double x, double y, float angle) {
                        double radians = Math.toRadians(angle);
                        double cos = Math.cos(radians);
                        double sin = Math.sin(radians);
                        double newX = center.latitude + (x - center.latitude) * cos - (y - center.longitude) * sin;
                        double newY = center.longitude + (x - center.latitude) * sin + (y - center.longitude) * cos;
                        return new LatLng(newX, newY);
                    }

                    private double generateRandomNumber(double min, double max) {
                        Random rand = new Random();
                        return min + (max - min) * rand.nextDouble();
                    }
                });
            }
        });
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