package com.proyecto.loginultimodia;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.proyecto.loginultimodia.R;
import com.firebase.ui.auth.AuthUI;

import com.proyecto.loginultimodia.Controlador.Dosis;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText etLoginEmail;
    TextInputEditText etLoginPassword;
    //TextView tvRegisterHere;//aquiiiiiiiiiiiiiiiiiiii
    Button olvidasteContrasena;
    Button btnLogin;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        olvidasteContrasena = findViewById(R.id.olvidasteContra);
        etLoginEmail = findViewById(R.id.etLoginEmail);
        etLoginPassword = findViewById(R.id.etLoginPass);
        //tvRegisterHere = findViewById(R.id.texttt);//aquiiiiiiiiiiiiiiiiiiii
        btnLogin = findViewById(R.id.btnLogin);


        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(view -> {
            loginUser();
        });
/*
   tvRegisterHere.setOnClickListener(view -> {//aquiiiiiiiiiiiiiiiiiiii
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
*/


        olvidasteContrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgotPassword.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void loginUser() {
        String email = etLoginEmail.getText().toString();
        String password = etLoginPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            etLoginEmail.setError("Email cannot be empty");
            etLoginEmail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            etLoginPassword.setError("Password cannot be empty");
            etLoginPassword.requestFocus();
        } else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                        FirebaseUser usuario = FirebaseAuth.getInstance().getCurrentUser();
                        String emailAver = usuario.getEmail();

                        //QuerySnapshot nuevoUSUS=  MainActivity.sacaDatos(emailAver);
                        //Log.d("WEBO", ""+nuevoUSUS.toString());
                        /*Query query = FirebaseFirestore.getInstance()
                                .collection("pacientes")
                                .whereEqualTo("email", usuario.getEmail());
                        FirestoreRecyclerOptions<Usuario> opciones = new FirestoreRecyclerOptions
                                .Builder<Usuario>().setQuery(query, Usuario.class).build();*/
                        if (usuario.getEmail().equals("admins@gmail.com")) {
                            startActivity(new Intent(LoginActivity.this, SuperAdminSecondActivity.class));// AQUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
                        } else if (usuario.getEmail().equals("admin@gmail.com")) {
                            //HabitacionesDoc.sacaDatos("admin@gmail.com");
                            startActivity(new Intent(LoginActivity.this, DoctorSecondActivity.class));// AQUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII

                        } else {
                            Dosis.sacaDatos(emailAver);
                            startActivity(new Intent(LoginActivity.this, SecondActivity.class));// AQUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Error : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.acercaDe) {
            lanzarAcercaDe(null);
            return true;
        }
        if (id == R.id.acercadebien) {
            lanzarAcercaDeBien(null);
            return true;
        }
        if (id == R.id.btnLogout) {
            AuthUI.getInstance().signOut(this);
            return true;

        }
        if (id == R.id.listadoMedicamentos) {
            lanzarMedicamento(null);
            return true;

        }


        return super.onOptionsItemSelected(item);
    }

    public void lanzarAcercaDe(View view) {
        Intent i = new Intent(this, AcercaDeActivity.class);
        startActivity(i);
    }

    public void lanzarMedicamento(View view) {
        Intent i = new Intent(this, MedicamentosActivity.class);

    }
    public void lanzarAcercaDeBien (View view){
        Intent i = new Intent(this, AcercaDeActivityREAL.class);
        startActivity(i);
    }

    public void lanzarLogOut (View view){
        Intent i = new Intent(this, LogOutActivity.class);
        startActivity(i);

    }
}