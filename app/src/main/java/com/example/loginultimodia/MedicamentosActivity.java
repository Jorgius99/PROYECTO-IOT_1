package com.example.loginultimodia;





import static com.example.loginultimodia.Registro.creaMapa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.loginultimodia.databinding.FragmentMedicamentosBinding;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Map;

public class MedicamentosActivity extends AppCompatActivity {

    //7 y 8.-RecyclerView


    private RecyclerView recyclerView;

    FragmentMedicamentosBinding binding;
    private AdaptadorImagenes adaptador;// quiza es private o public

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentMedicamentosBinding.inflate(getLayoutInflater());
        setContentView(R.layout.fragment_medicamentos);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        Query query = FirebaseFirestore.getInstance()
                .collection("foto");
        FirestoreRecyclerOptions<Imagen> opciones = new FirestoreRecyclerOptions
                .Builder<Imagen>().setQuery(query, Imagen.class).build();
        adaptador = new AdaptadorImagenes(this, opciones);
        recyclerView.setAdapter(adaptador);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
            }

@Override public void onStart() {
    super.onStart();
    adaptador.startListening();
}
    @Override public void onStop() {
        super.onStop();
        adaptador.stopListening();
    }

}
