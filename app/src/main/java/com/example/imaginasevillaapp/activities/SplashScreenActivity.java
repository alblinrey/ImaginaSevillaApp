package com.example.imaginasevillaapp.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.imaginasevillaapp.R;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 2500; // Tiempo en milisegundos (2.5 segundos)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Ocultar la ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(() -> {
            // Este código se ejecutará después del tiempo definido en SPLASH_TIME_OUT
            Intent i = new Intent(SplashScreenActivity.this, HomeMain.class);
            startActivity(i);
            finish(); // Cierra la SplashScreenActivity para que el usuario no pueda volver atrás fácilmente
        }, SPLASH_TIME_OUT);
    }
}

