package com.example.imaginasevillaapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.imaginasevillaapp.R;

public class ImprescindiblesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imprescindibles);

        //Esto hace que en el ActionBar exista una flecha como Up Button.
        if (getSupportActionBar() !=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //Hacer que la tarjeta realAlcazar funcione al hacer click.
        CardView cardAlcazar = findViewById(R.id.cardrealAlcazar);
        cardAlcazar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ImprescindiblesActivity.this, AlcazarActivity.class);
                startActivity(intent);
            }
        });

        //Tarjeta catedral.
        CardView cardCatedral = findViewById(R.id.cardCatedral);
        cardCatedral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ImprescindiblesActivity.this, CatedralActivity.class);
                startActivity(intent);
            }
        });

        //Tarjeta Plaza España y Parque María Luisa.
        CardView cardPlazaEspaña = findViewById(R.id.cardPlazaEspaña);
        cardPlazaEspaña.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ImprescindiblesActivity.this, PlazaActivity.class);
                startActivity(intent);
            }
        });
        //Tarjeta Torre del Oro.
        CardView cardTorre = findViewById(R.id.cardTorreOro);
        cardTorre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ImprescindiblesActivity.this, TorreActivity.class);
                startActivity(intent);
            }
        });
    }
    // Metodo de AppCompatActivity para que la el up button del Action Bar retroceda, en este caso
    //lo dirigimos al HomeMain.
    @Override
    public boolean onSupportNavigateUp() {
        // Al pulsar la flechita, volver siempre al HomeMain
        Intent intent = new Intent(this, HomeMain.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        return true;
    }
}
