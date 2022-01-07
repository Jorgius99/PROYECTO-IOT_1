package com.example.loginultimodia;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class EliminarPacienteActivity extends AppCompatActivity {
    TextInputEditText etRegNombreApellido;
    Button btneliminar;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cuestionario_eliminar);

        etRegNombreApellido = findViewById(R.id.userEliminar);
        btneliminar = findViewById(R.id.botoneliminar);


        btneliminar.setOnClickListener(view ->{
            deleteUser();
        });

    }

    private void deleteUser(){
        String nombreApellido = etRegNombreApellido.getText().toString();

            db= FirebaseFirestore.getInstance();
            db.collection("pacientes").document(nombreApellido).delete();
            //db.collection("pacientes").document(nombreApellido).collection("doctor").add(doctor);
    }
}

