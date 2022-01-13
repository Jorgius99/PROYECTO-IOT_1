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
import com.example.loginultimodia.databinding.FragmentDosisdoctorvolverBinding;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class IniciarDosisDocPaciente extends AppCompatActivity {
    private static Usuario usuarioConDatos;
    TextView tvLoginHere;//volverrrrrrrrrrrrrrrrrrrrrrrrrr

    private FragmentDosisdoctorvolverBinding binding; //si no está
    private AdaptadorDosis adaptador;
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

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Query query = db.collection("dosis")
                .whereEqualTo("dni", usuarioConDatos.getDNI());
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null) {
                    FirestoreRecyclerOptions<ObjetoDosis> opciones = new FirestoreRecyclerOptions
                            .Builder<ObjetoDosis>().setQuery(query, ObjetoDosis.class).build();
                    adaptador = new AdaptadorDosis(opciones, getApplicationContext());
                    binding = FragmentDosisdoctorvolverBinding.inflate(getLayoutInflater());
                    setContentView(binding.getRoot());
                    tvLoginHere = findViewById(R.id.volvertext);//volverrrrrrrrrrrrrrrrrrrrrrrrrr

                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    binding.recyclerViewD.setLayoutManager(layoutManager);

                    binding.recyclerViewD.setAdapter(adaptador);

                    adaptador.startListening();
                    tvLoginHere.setOnClickListener(view ->{         //volverrrrrrrrrrrrrrrrrrrrrrrrrr
                        startActivity(new Intent(IniciarDosisDocPaciente.this, DoctorSecondActivity.class));
                    });
                }

            }
        });


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
    }
    @Override
    public void onStop() {
        super.onStop();
        adaptador.stopListening();
    }*/
}



