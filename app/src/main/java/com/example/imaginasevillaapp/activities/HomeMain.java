package com.example.imaginasevillaapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.imaginasevillaapp.R;

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
        cardImprescindibles.setOnClickListener(v -> {
            // Abrimos la actividad de Imprescindibles
            Intent intent = new Intent(HomeMain.this, ImprescindiblesActivity.class);
            startActivity(intent);
        });

        //Tarjeta "Alojamientos" sea clicable.
        CardView cardAlojamiento = findViewById(R.id.cardAlojamiento);
        cardAlojamiento.setOnClickListener(v -> {
            // Abrimos la actividad de Alojamiento
            Intent intent = new Intent(HomeMain.this, AlojamientoActivity.class);
            startActivity(intent);
        });
        //Tarjeta "Eventos" sea clicable.
        CardView cardEventos = findViewById(R.id.cardEventos);
        cardEventos.setOnClickListener(v -> {
            // Abrimos la actividad de Eventos
            Intent intent = new Intent(HomeMain.this, EventosActivity.class);
            startActivity(intent);
        });
        //Tarjeta "Llegada" sea clicable.
        CardView cardLlegada = findViewById(R.id.cardLlegada);
        cardLlegada.setOnClickListener(v -> {
            // Abrimos la actividad de Llegada
            Intent intent = new Intent(HomeMain.this, LlegadaActivity.class);
            startActivity(intent);
        });
        //Para que el botón Descubre más sobre Sevilla... lleve al LoginAvtivity.

        Button btnDescubreMas = findViewById(R.id.btnDescubreMas);
        btnDescubreMas.setOnClickListener(v -> {
            Intent intent = new Intent(HomeMain.this, LoginActivity.class);
            startActivity(intent);
        });

    }

    //Carga el menú que es donde se ha creado el icono del formulario y el del User en el action bar.

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_help) {
            // Ir al formulario de ayuda
            Intent intent = new Intent(this, FormActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_perfil) {
            // Ir al perfil de usuario
            Intent intent = new Intent(this, UserActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


