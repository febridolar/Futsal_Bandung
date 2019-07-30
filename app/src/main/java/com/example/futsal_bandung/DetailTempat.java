package com.example.futsal_bandung;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class DetailTempat extends FragmentActivity implements OnMapReadyCallback {

    TextView mNamaTempat, mAlamat, mJamOperasi;
    ImageView mFoto;
    GoogleMap map;
    View mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tempat);

        getIntentExtra();

        SupportMapFragment mapFragment =(SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mapView = mapFragment.getView();


        mAlamat = findViewById(R.id.alamat_detail);
        mJamOperasi = findViewById(R.id.jam_operasi);
        mFoto = findViewById(R.id.foto_detail);

    }

    private void getIntentExtra(){

        String nama_tempat = getIntent().getStringExtra("nama_tempat");

        setExtra(nama_tempat);
    }

    private void setExtra(String nama_tempat){
        mNamaTempat = findViewById(R.id.nama_tempat_detail);
        mNamaTempat.setText(nama_tempat);
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
