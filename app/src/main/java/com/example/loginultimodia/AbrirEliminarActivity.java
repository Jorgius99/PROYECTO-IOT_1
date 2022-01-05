package com.example.loginultimodia;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;

public class AbrirEliminarActivity extends AppCompatActivity{
    TextInputLayout etRegNombreApellido;
    Button btneliminar;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView tvLoginHere;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cuestionario_eliminar);
        etRegNombreApellido = findViewById(R.id.userEliminar);
        btneliminar = findViewById(R.id.botoneliminar);
        tvLoginHere = findViewById(R.id.volverAcasa2);

        btneliminar.setOnClickListener(view ->{
            deleteUser();
        });
        tvLoginHere.setOnClickListener(view ->{
            startActivity(new Intent(AbrirEliminarActivity.this, SuperAdminSecondActivity.class));
        });

    }
    private void deleteUser(){
        String nombreApellido = etRegNombreApellido.getEditText().getText().toString();

        db= FirebaseFirestore.getInstance();
        db.collection("pacientes").document(nombreApellido).delete();
        //db.collection("pacientes").document(nombreApellido).collection("doctor").add(doctor);
        Toast.makeText(AbrirEliminarActivity.this, "Se ha eliminado correctamente", Toast.LENGTH_SHORT).show();
    }

}
