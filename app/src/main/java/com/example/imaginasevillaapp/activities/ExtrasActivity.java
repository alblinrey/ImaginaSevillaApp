package com.example.imaginasevillaapp.activities;

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

    private FirebaseFirestore db;
    private ArrayList<Monumentos> listaExtras;
    private MonumentoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extras);

        // Inicializar Firestore
        db = FirebaseFirestore.getInstance();

        // Configurar RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewExtras);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Lista y Adapter
        listaExtras = new ArrayList<>();
        adapter = new MonumentoAdapter(listaExtras, new MonumentoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Monumentos monumento) {
                // Ir a DetalleExtraActivity con el ID del documento
                Intent intent = new Intent(ExtrasActivity.this, DetalleExtraActivity.class);
                intent.putExtra("documentId", monumento.getId());
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(adapter);

        // Leer datos desde Firebase
        db.collection("extras_registrados")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (DocumentSnapshot doc : queryDocumentSnapshots) {
                        Monumentos monumento = doc.toObject(Monumentos.class);
                        // Agregar el monumento a la lista y su ID
                        monumento.setId(doc.getId()); // Aquí guardamos el ID
                        listaExtras.add(monumento);
                    }
                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(Throwable::printStackTrace);
    }
}
