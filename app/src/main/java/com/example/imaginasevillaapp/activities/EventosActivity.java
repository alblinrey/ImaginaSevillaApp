package com.example.imaginasevillaapp.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.imaginasevillaapp.R;

public class EventosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.eventos);  // Título de la barra superior
        }
    }
}
