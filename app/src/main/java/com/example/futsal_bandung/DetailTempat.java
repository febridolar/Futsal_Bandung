package com.example.futsal_bandung;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class DetailTempat extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;
    View mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tempat);

        SupportMapFragment mapFragment =(SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mapView = mapFragment.getView();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        float zoomLevel = 16.0f; //This goes up to 21

        LatLng Mapme = new LatLng(-6.888710, 107.619857);
        map.addMarker(new MarkerOptions().position(Mapme).title("Di Sekeloa\nSaya Kos"));

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(Mapme, zoomLevel));
        map.moveCamera(CameraUpdateFactory.newLatLng(Mapme));
    }
}
