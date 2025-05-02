package com.example.imaginasevillaapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.imaginasevillaapp.R;
import com.google.android.gms.common.SignInButton;

public class HomeMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Establece el título en la barra superior
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.title_home); // "Inicio"
        }
        //Hacer que la tarjeta "Imprescindibles" sea clicable.
        CardView cardImprescindibles = findViewById(R.id.cardImprescindibles);
        cardImprescindibles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrimos la actividad de Imprescindibles
                Intent intent = new Intent(HomeMain.this, ImprescindiblesActivity.class);
                startActivity(intent);
            }
        });

        //Tarjeta "Alojamientos" sea clicable.
        CardView cardAlojamiento = findViewById(R.id.cardAlojamiento);
        cardAlojamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrimos la actividad de Alojamiento
                Intent intent = new Intent(HomeMain.this, AlojamientoActivity.class);
                startActivity(intent);
            }
        });
        //Tarjeta "Eventos" sea clicable.
        CardView cardEventos = findViewById(R.id.cardEventos);
        cardEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrimos la actividad de Eventos
                Intent intent = new Intent(HomeMain.this, EventosActivity.class);
                startActivity(intent);
            }
        });
        //Tarjeta "Llegada" sea clicable.
        CardView cardLlegada = findViewById(R.id.cardLlegada);
        cardLlegada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrimos la actividad de Llegada
                Intent intent = new Intent(HomeMain.this, LlegadaActivity.class);
                startActivity(intent);
            }
        });
        //Para que el botón de inicio de sesión lleve al LoginAvtivity.
        SignInButton btnSignIn = findViewById(R.id.btnGoogleSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMain.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}