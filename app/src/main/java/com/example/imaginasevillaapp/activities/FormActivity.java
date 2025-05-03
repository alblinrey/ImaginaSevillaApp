package com.example.imaginasevillaapp.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.imaginasevillaapp.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FormActivity extends AppCompatActivity {

    private EditText etSugerencia;
    private FirebaseFirestore db;//Variable para conectar con la BBDD.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form); //Se carga el activity_form.xml con el cuadro de texto y el botón.

        etSugerencia = findViewById(R.id.etSugerencia);
        Button btnEnviar = findViewById(R.id.btnEnviar);
        db = FirebaseFirestore.getInstance();

        //Evento de click del botón enviar.
        btnEnviar.setOnClickListener(v -> {
            String texto = etSugerencia.getText().toString().trim();
            if (!texto.isEmpty()) {
                Map<String, Object> sugerencia = new HashMap<>();
                sugerencia.put("mensaje", texto);
                sugerencia.put("timestamp", System.currentTimeMillis());

                //Esto hace que en mensaje se guarde en la BBDD en la colección "sugerencias"
                db.collection("sugerencias")
                        .add(sugerencia)
                        .addOnSuccessListener(documentReference -> {
                            Toast.makeText(this, "¡Gracias por tu mensaje!", Toast.LENGTH_SHORT).show();
                            etSugerencia.setText("");
                        })
                        .addOnFailureListener(e -> Toast.makeText(this, "Error al enviar. Intenta de nuevo.", Toast.LENGTH_SHORT).show());
            } else {
                Toast.makeText(this, "Por favor escribe algo.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
