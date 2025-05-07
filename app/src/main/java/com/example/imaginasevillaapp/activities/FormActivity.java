package com.example.imaginasevillaapp.activities;

import android.os.Bundle;
import android.util.Patterns;
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
    private EditText etEmail;
    private FirebaseFirestore db;//Variable para conectar con la BBDD.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form); //Se carga el activity_form.xml con el cuadro de texto y el botón.

        etSugerencia = findViewById(R.id.etSugerencia);
        etEmail = findViewById(R.id.etEmail);
        Button btnEnviar = findViewById(R.id.btnEnviar);
        db = FirebaseFirestore.getInstance();

        //Evento de click del botón enviar.
        btnEnviar.setOnClickListener(v -> {
            String texto = etSugerencia.getText().toString().trim();
            String email = etEmail.getText().toString().trim();

            //Valida que el texto tiene contenido y lo guarda en cada campo al pulsar el botón.
            if (!texto.isEmpty()) {

                //Se usa Patterns, que esta clase se importa para proporcionar expresiones predefinidas, en este caso para validar el
                //formato del email.
                if (email.isEmpty() || Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Map<String, Object> sugerencia = new HashMap<>();
                    sugerencia.put("mensaje", texto);
                    sugerencia.put("timestamp", System.currentTimeMillis());
                    sugerencia.put("email", email);

                    //Esto hace que en mensaje se guarde en la BBDD en la colección "sugerencias"
                    db.collection("sugerencias")
                            .add(sugerencia)
                            .addOnSuccessListener(documentReference -> {
                                //Una vez enviado el mensaje, salta un toast agradeciendo.
                                Toast.makeText(this, "¡Gracias por tu mensaje!", Toast.LENGTH_SHORT).show();
                                //Limpia los campos y los deja vacíos.
                                etSugerencia.setText("");
                                etEmail.setText("");
                            })
                            .addOnFailureListener(e -> Toast.makeText(this, "Error al enviar. Intenta de nuevo.", Toast.LENGTH_SHORT).show());
                } else {
                    Toast.makeText(this, "Por favor, introduzca un correo electrónico válido.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Por favor escribe algo.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

