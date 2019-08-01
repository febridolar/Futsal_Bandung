package com.apps.futsal_bandung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.apps.futsal_bandung.R;

public class Manfaat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manfaat);

        ImageView imageView = (ImageView) findViewById(R.id.back_manfaat);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Manfaat.this, Home.class);
                startActivity(intent);
            }
        });
    }
}
