package com.proyecto.loginultimodia;





import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.loginultimodia.R;
import com.proyecto.loginultimodia.databinding.FragmentMedicamentosBinding;

import java.util.Map;

public class MedicamentosActivity extends AppCompatActivity {

    //7 y 8.-RecyclerView


    private RecyclerView recyclerView;
    public Adaptador adaptador;
    FragmentMedicamentosBinding binding;
    //7 y 8.-RecyclerView
    static public Map<Integer,Registro> mapa ;

    // 5 a) Colecciones

    static public String  MEDICAMENTOS[]={"nolotil","enantyum","paracetamol"};
    ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentMedicamentosBinding.inflate(getLayoutInflater());
        setContentView(R.layout.fragment_medicamentos);
        setContentView(binding.getRoot());
        mapa= Registro.creaMapa(MEDICAMENTOS);
        adaptador = new Adaptador(mapa);
        recyclerView = binding.recyclerView;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptador);


    }
/*
    public void lanzarMedicamentos(View view) {
        Intent i = new Intent(this, MedicamentosActivity.class);
        startActivity(i);


    }*/
}
