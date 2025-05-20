package com.example.imaginasevillaapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.imaginasevillaapp.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 9001;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        SignInButton signInButton = findViewById(R.id.btnGoogleSignIn);
        Button btnUserName = findViewById(R.id.btnUserName);

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            // Ya hay una sesión iniciada
            signInButton.setVisibility(View.GONE);
            btnUserName.setVisibility(View.VISIBLE);
            btnUserName.setText(currentUser.getDisplayName());

            btnUserName.setOnClickListener(v -> {
                Intent intent = new Intent(LoginActivity.this, ExtrasActivity.class);
                startActivity(intent);
                finish();
            });
        } else {
            // No hay sesión iniciada
            signInButton.setVisibility(View.VISIBLE);
            btnUserName.setVisibility(View.GONE);

            signInButton.setOnClickListener(view -> signIn());
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Toast.makeText(this, "Fallo en Google Sign In", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            guardarInformacionUsuario(user);

                            new AlertDialog.Builder(this)
                                    .setTitle("Inicio de sesión")
                                    .setMessage("¡Bienvenido " + user.getDisplayName() + "!\nTu sesión se ha iniciado correctamente.")
                                    .setPositiveButton("Continuar", (dialog, which) -> {
                                        startActivity(new Intent(LoginActivity.this, ExtrasActivity.class));
                                        finish();
                                    })
                                    .show();
                        }
                    } else {
                        Toast.makeText(this, "Autenticación fallida", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void guardarInformacionUsuario(FirebaseUser user) {
        Map<String, Object> usuario = new HashMap<>();
        usuario.put("uid", user.getUid());
        usuario.put("nombre", user.getDisplayName());
        usuario.put("email", user.getEmail());

        firestore.collection("usuarios").document(user.getUid())
                .set(usuario, SetOptions.merge())
                .addOnSuccessListener(aVoid -> {
                    // Datos guardados con éxito
                });
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(this, HomeMain.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        return true;
    }
}
