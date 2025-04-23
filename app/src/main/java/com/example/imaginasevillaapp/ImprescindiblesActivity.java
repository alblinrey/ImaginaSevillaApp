package com.example.imaginasevillaapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ImprescindiblesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imprescindibles); // Enlaza con el layout
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.imprescindibles); // Título en la barra superior
        }
    }
}
