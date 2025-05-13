package com.example.imaginasevillaapp.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imaginasevillaapp.R;
import com.example.imaginasevillaapp.adapters.MonumentoAdapter;
import com.example.imaginasevillaapp.models.Monumentos;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ExtrasActivity extends AppCompatActivity {

    private ArrayList<Monumentos> listaExtras;
    private MonumentoAdapter adapter;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extras);

        //Esto hace que en el ActionBar exista una flecha como Up Button.
        if (getSupportActionBar() !=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Inicializar Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Configurar RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewExtras);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Lista y Adapter
        listaExtras = new ArrayList<>();
        adapter = new MonumentoAdapter(listaExtras, monumento -> {
            // Ir a DetalleExtraActivity con el ID del documento
            Intent intent = new Intent(ExtrasActivity.this, DetalleExtraActivity.class);
            intent.putExtra("documentId", monumento.getId());
            startActivity(intent);
        });

        recyclerView.setAdapter(adapter);

        // Leer datos desde Firebase
        db.collection("extras_registrados")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (DocumentSnapshot doc : queryDocumentSnapshots) {
                        Monumentos monumento = doc.toObject(Monumentos.class);
                        // Agregar el monumento a la lista y su ID
                        assert monumento != null;
                        monumento.setId(doc.getId()); // Aquí guardamos el ID
                        listaExtras.add(monumento);
                    }
                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(Throwable::printStackTrace);
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
