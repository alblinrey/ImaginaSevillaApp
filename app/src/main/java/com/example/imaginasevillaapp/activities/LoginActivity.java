package com.example.imaginasevillaapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.imaginasevillaapp.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;


public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 9001; //Identificación de la solicitud de inicio de sesión.
    private FirebaseAuth mAuth; //Instancia de FirebaseAuth para utenticar al usuario.
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Inicialización FirebaseAuth.
        mAuth = FirebaseAuth.getInstance();

        //Configuración del inicio de sesión donde se solicita el token del ID y el mail.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        //Se crea el botón de inicio de sesión de Google con su evento click.
        SignInButton signInButton = findViewById(R.id.btnGoogleSignIn);
        signInButton.setOnClickListener(view -> signIn());
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Verificación de que el código de solicitud es correcto, si lo es, se autentica con Firebase, si no lo es, el catch manda el mensaje de error.
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

      // Autenticar al usuario con Firebase usando el token de Google.
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser(); //Se obtiene el usuario desde firebase y se muestra una alerta para confirmar el inicio de sesión.
                        new AlertDialog.Builder(this)
                                .setTitle("Inicio de sesión")
                                .setMessage("¡Bienvenido " + user.getDisplayName() + "!\nTu sesión se ha iniciado correctamente.")
                                .setPositiveButton("Continuar", (dialog, which) -> {
                                    startActivity(new Intent(LoginActivity.this, ExtrasActivity.class)); //se inicia sesion y lleva al Extras.
                                    finish();
                                })
                                .show();
                    } else {
                        Toast.makeText(this, "Autenticación fallida", Toast.LENGTH_SHORT).show();//En caso contratio, se muestra el error.
                    }
                });
    }
}

