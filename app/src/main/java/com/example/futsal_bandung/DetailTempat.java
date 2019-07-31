package com.example.futsal_bandung;

import android.content.Context;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class DetailTempat extends FragmentActivity implements OnMapReadyCallback {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private TextView mNamaTempat, mAlamat, mJamOperasi,mTelepon,mFasilitas,mTarif,urlImg;
    private ImageView Mimg;
    String nm_tempat, userID;
    private ImageView mFoto;
    private GoogleMap map;
    private View mapView;

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
        mFasilitas = findViewById(R.id.fasilitas);
        mTarif = findViewById(R.id.tarif);
        mTelepon = findViewById(R.id.telepon);
        Mimg = findViewById(R.id.foto_detail);
        urlImg = findViewById(R.id.url_gambar_detail);

        mFoto = findViewById(R.id.foto_detail);


        userID = getIntent().getStringExtra("nama_tempat");

        mDatabase= FirebaseDatabase.getInstance().getReference().child("detail").child(userID);

        mDatabase.keepSynced(true);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String alamat = dataSnapshot.child("alamat").getValue(String.class);
                String jam_operasi = dataSnapshot.child("jam_operasi").getValue(String.class);
                String fasilitas = dataSnapshot.child("fasilitas").getValue(String.class);
                String tarif = dataSnapshot.child("tarif").getValue(String.class);
                String telepon = dataSnapshot.child("telepon").getValue(String.class);
                String img = dataSnapshot.child("img").getValue(String.class);

                mAlamat.setText(alamat);
                mJamOperasi.setText(jam_operasi);
                mFasilitas.setText(fasilitas);
                mTarif.setText(tarif);
                mTelepon.setText(telepon);

                urlImg.setText(img);
                Picasso.with(getApplicationContext()).load(img).into(Mimg);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getIntentExtra(){

        String nama_tempat = getIntent().getStringExtra("nama_tempat");
        nm_tempat.valueOf(getIntent().getStringExtra("nama_tempat"));
        setExtra(nama_tempat);

    }

    private void setExtra(String nama_tempat){
        mNamaTempat = findViewById(R.id.namatempatdetail);
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
