package com.example.loginultimodia;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;

public class AnyadirAvisoActivity extends AppCompatActivity {

    TextInputEditText etRegMotivo;
    TextInputEditText etRegPrioridad;
    TextInputEditText etRegDni;
    TextInputEditText etRegHabitacion;
    TextView tvLoginHere;
    Button btnRegister;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cuestionario_avisos);


        etRegMotivo=findViewById(R.id.etRegMoti);
        etRegPrioridad=findViewById(R.id.etRegPrio);
        etRegDni=findViewById(R.id.etRegDni);
        etRegHabitacion=findViewById(R.id.etRegHabi);
        tvLoginHere = findViewById(R.id.volverAcasa);
        btnRegister = findViewById(R.id.enviaravi);


        btnRegister.setOnClickListener(view ->{
            createUser();
        });

        tvLoginHere.setOnClickListener(view ->{
            startActivity(new Intent(AnyadirAvisoActivity.this, SuperAdminSecondActivity.class));
        });
    }

    private void createUser(){
        String motivo = etRegMotivo.getText().toString();
        String prioridad = etRegPrioridad.getText().toString();
        String dni = etRegDni.getText().toString();
        String NumHab = etRegHabitacion.getText().toString();
        Date fecha = new Date();
            Aviso aviso = new Aviso(motivo, fecha, prioridad, dni, NumHab);
        //String motiv, Date fecha, String prior, String dni, String habitacion)
            db= FirebaseFirestore.getInstance();
            db.collection("avisos").document().set(aviso);
            //db.collection("pacientes").document(nombreApellido).collection("doctor").add(doctor);
    }

}