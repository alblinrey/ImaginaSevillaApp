package com.example.imaginasevillaapp.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.imaginasevillaapp.R;

public class AlojamientoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alojamiento); // Enlaza con el layout
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.alojamiento); // Título en la barra superior
        }
    }
}

