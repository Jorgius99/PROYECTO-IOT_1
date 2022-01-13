package com.example.loginultimodia;

import static java.security.AccessController.getContext;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginultimodia.Controlador.Dosis;
import com.example.loginultimodia.databinding.FragmentDosisBinding;
import com.example.loginultimodia.databinding.FragmentHabitacionesBinding;
import com.example.loginultimodia.databinding.FragmentHabitacionesvolverBinding;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class iniciarHabsDocPaciente extends AppCompatActivity {
    private static Usuario usuarioConDatos;

    private FragmentHabitacionesvolverBinding binding; //si no está
    private AdaptadorTempHum adaptador;
    TextView tvLoginHere;// volverrrrrrrrrrrrrrrrrrrrrrr

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //  private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    // private String mParam1;
    //private String mParam2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentHabitacionesvolverBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Query query = db.collection("habitaciones")
                .whereEqualTo("DNI", usuarioConDatos.getDNI());

        FirestoreRecyclerOptions<Habitacion> opciones = new FirestoreRecyclerOptions
                .Builder<Habitacion>().setQuery(query, Habitacion.class).build();

        adaptador = new AdaptadorTempHum(opciones, getApplicationContext());
        tvLoginHere = findViewById(R.id.volverbotonhabit);//volverrrrrrrrrrrrrrrrrrrrrrrrrr


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        binding.recyclerViewH.setLayoutManager(layoutManager);

        binding.recyclerViewH.setAdapter(adaptador);
        tvLoginHere.setOnClickListener(view ->{         //volverrrrrrrrrrrrrrrrrrrrrrrrrr
            startActivity(new Intent(iniciarHabsDocPaciente.this, DoctorSecondActivity.class));
        });
        adaptador.startListening();

        /*
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (value != null) {

                    FirestoreRecyclerOptions<Habitacion> opciones = new FirestoreRecyclerOptions
                            .Builder<Habitacion>().setQuery(query, Habitacion.class).build();

                    adaptador = new AdaptadorTempHum(opciones, getApplicationContext());

                    binding = FragmentHabitacionesBinding.inflate(getLayoutInflater());
                    setContentView(binding.getRoot());

                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    binding.recyclerViewH.setLayoutManager(layoutManager);

                    binding.recyclerViewH.setAdapter(adaptador);

                    adaptador.startListening();

                }

            }
        });


         */

    }

    public static void sacaDatos(String email) {

        final Usuario[] usuarioSacado = {new Usuario()};

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("pacientes").whereEqualTo("email", email).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot document = task.getResult();
                DocumentSnapshot docOC = document.getDocuments().get(0);
                if (document != null) {
                    usuarioSacado[0] = docOC.toObject(Usuario.class);// here
                    Log.d("USUARIOSACAD0", "" + usuarioSacado[0].getEmail());
                    rellenarUsuario(usuarioSacado[0]);
                }
            }
        });
        // Log.d("NUEVOUSER", ""+nuevoUser);
    }

    public static void rellenarUsuario(Usuario ussus) {
        usuarioConDatos = ussus;
        Log.d("FAFA", "" + usuarioConDatos);
        // return usuarioConDatos;
    }
/*
    @Override
    public void onStart() {
        super.onStart();
        adaptador.startListening();
    }*/
    @Override
    public void onStop() {
        super.onStop();
        adaptador.stopListening();
    }
}



