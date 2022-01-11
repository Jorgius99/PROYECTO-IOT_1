package com.example.loginultimodia;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AnyadirDosisActivity extends AppCompatActivity {

    TextInputEditText etRegMedicamento;
    TextInputEditText etRegCantidad;
    TextInputEditText etRegDni;
    TextInputEditText etRegFrecuencia;
    TextInputEditText etRegFechaYHora;

    TextView tvLoginHere;
    Button btnRegister;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cuestionario_pastillass);


        etRegMedicamento=findViewById(R.id.etRegMedicam);
        etRegCantidad=findViewById(R.id.etRegCanti);
        etRegDni=findViewById(R.id.etRegDni);
        etRegFrecuencia=findViewById(R.id.etRegFrecue);
        etRegFechaYHora=findViewById(R.id.etRegFechaYH);

        tvLoginHere = findViewById(R.id.volverAcasa);
        btnRegister = findViewById(R.id.enviarDosis);


        btnRegister.setOnClickListener(view ->{
            createDosis();
        });

        tvLoginHere.setOnClickListener(view ->{
            startActivity(new Intent(AnyadirDosisActivity.this, DoctorSecondActivity.class));
        });
    }

    private void createDosis(){
        String Medicamento = etRegMedicamento.getText().toString();
        String Cantidad = etRegCantidad.getText().toString();
        String Dni = etRegDni.getText().toString();
        String Frecuencia = etRegFrecuencia.getText().toString();
        //String FechaYHora = etRegFechaYHora.getText().toString();

        try{
            Date datee = new SimpleDateFormat("dd/MM/yyyy, hh:mm").parse(etRegFechaYHora.getText().toString());
            //Date hora = new SimpleTimeLimiter()
            long milis = datee.getTime();
            //datee.setHours(16);
            //datee.setMinutes(23);
            ObjetoDosis objetodosis = new ObjetoDosis(Medicamento, Cantidad, Dni, Frecuencia, datee, milis);
            //String motiv, Date fecha, String prior, String dni, String habitacion)
            db= FirebaseFirestore.getInstance();
            db.collection("dosis").document().set(objetodosis);
            //db.collection("pacientes").document(nombreApellido).collection("doctor").add(doctor);
            Toast.makeText(AnyadirDosisActivity.this, "Se ha añadido correctamente", Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        //Date FechaYHora = new Date();


    }

}