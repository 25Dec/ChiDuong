package com.example.androiddrawroute;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.androiddrawroute.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng uitToaB = new LatLng(10.87014780546287, 106.803845382914);
        LatLng vanPhongDoan = new LatLng(10.868907150083595, 106.80374681170142);
        mMap.addMarker(new MarkerOptions().position(uitToaB).title("UIT Tòa B"));
        mMap.addMarker(new MarkerOptions().position(vanPhongDoan).title("Văn phòng đoàn hội UIT"));
        mMap.addPolyline(new PolylineOptions().add(
                        uitToaB,
                        new LatLng(10.869608, 106.803927),
                        new LatLng(10.869205, 106.803865),
                        vanPhongDoan
        )
                .width(10)
                .color(Color.RED));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(uitToaB,18));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
    }
}