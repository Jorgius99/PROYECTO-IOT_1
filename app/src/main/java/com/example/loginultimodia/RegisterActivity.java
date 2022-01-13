package com.example.loginultimodia;

import static com.example.loginultimodia.Registro.creaMapa;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
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

import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    TextInputEditText etRegEmail;
    TextInputEditText etRegPassword;
    TextInputEditText etRegNombreApellido;
    TextInputEditText etRegDni;
    TextInputEditText etRegDniDoctor;
    TextInputEditText etRegNumHabitacion;

    TextView tvLoginHere;
    Button btnRegister;
    FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();




   @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        etRegEmail = findViewById(R.id.emailEditText);
        etRegPassword = findViewById(R.id.etRegPass);
        etRegNombreApellido = findViewById(R.id.etRegNombreApellido);
        etRegDni=findViewById(R.id.etRegDni);
        etRegDniDoctor=findViewById(R.id.etRegDniDoc);
        etRegNumHabitacion=findViewById(R.id.etRegNumHab);
        tvLoginHere = findViewById(R.id.tvLoginHere);
        btnRegister = findViewById(R.id.ppp);

        mAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(view ->{
            createUser();
        });

        tvLoginHere.setOnClickListener(view ->{
            startActivity(new Intent(RegisterActivity.this, SuperAdminSecondActivity.class));
        });
/*

        //7 y 8.-RecyclerView
        Button button3 = findViewById(R.id.listadoMedicamentos);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MedicamentosActivity.class);
                startActivity(i);
            }
        });*/


    }

    private void createUser(){
        String email = etRegEmail.getText().toString();
        String password = etRegPassword.getText().toString();
        String nombreApellido = etRegNombreApellido.getText().toString();
        String dni = etRegDni.getText().toString();
        String dniDoc = etRegDniDoctor.getText().toString();
        String NumHab = etRegNumHabitacion.getText().toString();

        if (TextUtils.isEmpty(email)){
            etRegEmail.setError("Email cannot be empty");
            etRegEmail.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            etRegPassword.setError("Password cannot be empty");
            etRegPassword.requestFocus();
        }else{
            Usuario usuario = new Usuario(dni, nombreApellido,"paciente",dniDoc, NumHab, email);
            //Usuario doctor = new Usuario("74743318K", "Paco Boluda", "doctor");
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    }else{
                        Toast.makeText(RegisterActivity.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            db= FirebaseFirestore.getInstance();
            db.collection("pacientes").document(dni).set(usuario);
            //db.collection("pacientes").document(nombreApellido).collection("doctor").add(doctor);
        }
    }
    public void lanzarMedicamentos(View view){
        Intent i = new Intent(this, MedicamentosActivity.class);
        startActivity(i);
    }


}