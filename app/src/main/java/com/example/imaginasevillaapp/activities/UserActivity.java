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

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.imaginasevillaapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserActivity extends AppCompatActivity {

    private TextView tvNombre, tvEmail;
    private ImageView ivFotoPerfil;
    private Button btnCerrarSesion, btnEliminarCuenta, btnIniciarSesion;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        //Relacionar variables con id del XML.
        tvNombre = findViewById(R.id.tvNombre);
        tvEmail = findViewById(R.id.tvEmail);
        ivFotoPerfil = findViewById(R.id.ivFotoPerfil);
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);
        btnEliminarCuenta = findViewById(R.id.btnEliminarCuenta);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);

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

        btnCerrarSesion.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(this, "Sesión cerrada correctamente", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, HomeMain.class));
            finish();
        });

        btnEliminarCuenta.setOnClickListener(v -> {
            if (user != null) {
                user.delete().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Cuenta eliminada", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, HomeMain.class));
                        finish();
                    } else {
                        Toast.makeText(this, "Error al eliminar la cuenta", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnIniciarSesion.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }
}
