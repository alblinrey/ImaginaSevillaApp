package com.example.imaginasevillaapp.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.imaginasevillaapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        //Esto hace que en el ActionBar exista una flecha como Up Button.
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //Relacionar variables con id del XML.
        TextView tvNombre = findViewById(R.id.tvNombre);
        TextView tvEmail = findViewById(R.id.tvEmail);
        ImageView ivFotoPerfil = findViewById(R.id.ivFotoPerfil);
        Button btnCerrarSesion = findViewById(R.id.btnCerrarSesion);
        Button btnEliminarCuenta = findViewById(R.id.btnEliminarCuenta);
        Button btnIniciarSesion = findViewById(R.id.btnIniciarSesion);

        //cargar usuario de la BBDD con los datos que tiene en su cuenta de GMail.
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            // Mostrar información del usuario
            tvNombre.setText(user.getDisplayName());
            tvEmail.setText(user.getEmail());

            Uri photoUrl = user.getPhotoUrl();
            if (photoUrl != null) {
                Glide.with(this).load(photoUrl).into(ivFotoPerfil);
            }

            // Mostrar botones de cuenta activa
            btnCerrarSesion.setVisibility(View.VISIBLE);
            btnEliminarCuenta.setVisibility(View.VISIBLE);
            btnIniciarSesion.setVisibility(View.GONE);

        } else {
            // Usuario no autenticado, ocultar datos y mostrar botón de inicio
            tvNombre.setText("Sesión no iniciada");
            tvEmail.setText("");
            ivFotoPerfil.setImageResource(R.drawable.ic_user);

            btnCerrarSesion.setVisibility(View.GONE);
            btnEliminarCuenta.setVisibility(View.GONE);
            btnIniciarSesion.setVisibility(View.VISIBLE);
        }

        //Cierre de sesión.
        btnCerrarSesion.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(this, "Sesión cerrada correctamente", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, HomeMain.class));
            finish();
        });

        //Eliminación de la cuenta.
        btnEliminarCuenta.setOnClickListener(v -> {
            if (user != null) {

                //Confirmar eliminación con un mensaje.
                new AlertDialog.Builder(this)
                        .setTitle("Confirmar Eliminación")
                        .setMessage("¿Estás seguro de que deseas eliminar tu cuenta? Esta acción es irreversible.")
                        .setPositiveButton("Eliminar", (dialog, which) -> user.delete().addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(this, "Cuenta eliminada", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(this, HomeMain.class));
                                finish();
                            } else {
                                Toast.makeText(this, "Error al eliminar la cuenta", Toast.LENGTH_SHORT).show();
                            }
                        }))
                        //Botón de cancelar la eliminación de la cuenta.
                        .setNegativeButton("Cancelar", (dialog, which) -> {
                            // Acción a realizar si el usuario cancela la eliminación
                            dialog.dismiss(); // Simplemente cierra el diálogo
                            Toast.makeText(this, "Cuenta no eliminada", Toast.LENGTH_SHORT).show();
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert) //Icono de alerta.
                        .show();
            }
        });

        //Al hacer click en en Iniciar sesión lleva directo al LoginActivity.
        btnIniciarSesion.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
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
