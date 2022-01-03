package com.example.loginultimodia;

import static java.security.AccessController.getContext;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginultimodia.Controlador.Dosis;
import com.example.loginultimodia.databinding.FragmentDosisBinding;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class IniciarDosisDocPaciente extends AppCompatActivity {
    private static Usuario usuarioConDatos = new Usuario();

   // private FragmentDosisBinding binding; //si no está
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
        //usuarioConDatos.setDNI("24567753N");

        RecyclerView recyclerView = findViewById(R.id.recyclerViewD);
//        recyclerView.setHasFixedSize(true);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        //binding = FragmentDosisBinding.inflate(getLayoutInflater());
        Query query = db.collection("dosis")
                .whereEqualTo("dni","24567753N");
query.addSnapshotListener(new EventListener<QuerySnapshot>() {
    @Override
    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

        if(value!=null){

            FirestoreRecyclerOptions<objetoDosis> opciones = new FirestoreRecyclerOptions
                    .Builder<objetoDosis>().setQuery(query, objetoDosis.class).build();
            Log.d("OPCIONESSSSSSSSS",""+query.get().isComplete());
            Log.d("APPLICATIONNNNNNN",""+getApplicationContext().toString());
            adaptador = new AdaptadorDosis(opciones, getApplicationContext());
            Log.d("ADAPTADOORRR",""+adaptador.getSnapshots());
            recyclerView.setAdapter(adaptador);
            adaptador.startListening();
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        }

    }
});





        //System.out.println(getContext());
       // binding.recyclerView1.setAdapter(adaptador);
        //binding.recyclerView1.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        // Inflate the layout for this fragment

    }

    public static void sacaDatos(String email){

        final Usuario[] usuarioSacado = {new Usuario()};

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("pacientes").whereEqualTo("email",email).get().addOnCompleteListener(task ->  {
            if (task.isSuccessful()) {
                QuerySnapshot document = task.getResult();
                DocumentSnapshot docOC = document.getDocuments().get(0);
                if (document != null) {
                    usuarioSacado[0] = docOC.toObject(Usuario.class);// here
                    Log.d("USUARIOSACAD0",""+ usuarioSacado[0].getEmail());
                    rellenarUsuario(usuarioSacado[0]);
                }
            }
        });
        // Log.d("NUEVOUSER", ""+nuevoUser);
    }
    public static Usuario rellenarUsuario(Usuario ussus)  {
        usuarioConDatos  = ussus;
        Log.d("FAFA", ""+usuarioConDatos);
        return usuarioConDatos;
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



