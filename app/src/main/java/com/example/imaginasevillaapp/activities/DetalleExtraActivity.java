package com.example.imaginasevillaapp.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.imaginasevillaapp.R;
import com.example.imaginasevillaapp.models.Monumentos; // Usamos Monumentos porque las estructuras son iguales
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class DetalleExtraActivity extends AppCompatActivity {

    private TextView tvNombre, tvDescripcion, tvDireccion, tvHorarios, tvPrecio, tvInfo, tvWeb;
    private ImageView ivImagen;
    private WebView wvMapa;

    @SuppressLint({"SetJavaScriptEnabled", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_extra); // Aquí usa el XML correspondiente

        //Esto hace que en el ActionBar exista una flecha como Up Button.
        if (getSupportActionBar() !=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Referencias UI
        tvNombre = findViewById(R.id.tvNombre);
        tvDescripcion = findViewById(R.id.tvDescripcion);
        tvDireccion = findViewById(R.id.tvDireccion);
        tvHorarios = findViewById(R.id.tvHorarios);
        tvPrecio = findViewById(R.id.tvPrecio);
        tvInfo = findViewById(R.id.tvInfo);
        tvWeb = findViewById(R.id.tvWeb);
        ivImagen = findViewById(R.id.ivImagen);
        wvMapa = findViewById(R.id.wvMapa);

        // Habilitar JavaScript para el WebView (embed de Google Maps)
        WebSettings ws = wvMapa.getSettings();
        ws.setJavaScriptEnabled(true);

        // Inicializar Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Obtener el ID del documento desde el Intent
        String documentId = getIntent().getStringExtra("documentId");

        // Cargar el documento de la colección "extras_registrados"
        assert documentId != null;
        DocumentReference docRef = db.collection("extras_registrados")
                .document(documentId);

        docRef.get().addOnSuccessListener(documentSnapshot -> {
            if (!documentSnapshot.exists()) {
                Toast.makeText(this, "Ficha no encontrada", Toast.LENGTH_SHORT).show();
                return;
            }

            // Crear un objeto Monumento desde los datos de Firestore (en este caso son extras, pero se usa la clase modelo Monumentos
            // también usada en los "Imprescindibles").)
            Monumentos monumento = documentSnapshot.toObject(Monumentos.class);

            if (monumento != null) {
                // Asignar los valores del objeto Monumento (Extra) a la UI
                tvNombre.setText(monumento.getNombre());
                tvDescripcion.setText(monumento.getDescripcion());
                tvDireccion.setText("📍 " + monumento.getDireccion());
                tvHorarios.setText("⏰ " + monumento.getHorarios());
                tvPrecio.setText("💶 " + monumento.getPrecio());
                tvInfo.setText(monumento.getInfo());
                tvWeb.setText("Web Oficial: " + monumento.getWebOficial());

                // Cargar la imagen con Glide
                Glide.with(this)
                        .load(monumento.getImagenUrl())
                        .into(ivImagen);

                // Cargar el mapa embebido
                String html = "<html><body style='margin:0;padding:0;'><iframe " +
                        "width='100%' height='100%' frameborder='0' style='border:0' " +
                        "src='" + monumento.getMapaUrl() + "' allowfullscreen></iframe></body></html>";
                wvMapa.loadData(html, "text/html", "utf-8");
            }

        }).addOnFailureListener(e -> Toast.makeText(this, "Error al cargar datos", Toast.LENGTH_SHORT).show());
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
