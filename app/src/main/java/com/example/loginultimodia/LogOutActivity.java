package com.example.loginultimodia;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

public class LogOutActivity extends AppCompatActivity {
    FirebaseAuth mAuth;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
<<<<<<< HEAD
=======
        mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
>>>>>>> inicioSesionYDatosXevii
    }
}

