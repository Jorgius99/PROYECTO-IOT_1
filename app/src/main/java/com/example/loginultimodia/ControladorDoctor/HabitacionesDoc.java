package com.example.loginultimodia.ControladorDoctor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.loginultimodia.AdaptadorHabsDoctor;
import com.example.loginultimodia.Habitacion;
import com.example.loginultimodia.databinding.FragmentDoctorHabitacionesBinding;
import com.example.loginultimodia.databinding.FragmentHabitacionesBinding;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HabitacionesDoc#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HabitacionesDoc extends Fragment {
    private FragmentDoctorHabitacionesBinding binding; //si no está
    public static AdaptadorHabsDoctor adaptador;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HabitacionesDoc() {
        // Required empty public constructor
    }




    public static HabitacionesDoc newInstance(String param1, String param2) {
        HabitacionesDoc fragment = new HabitacionesDoc();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    //Button boton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        //boton = (Button) findViewById(R.id.buttonRegistrar);
    }
/*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_doctor_habitaciones, container, false);


        View tvRegisterHere = v.findViewById(R.id.imageView8);
        tvRegisterHere.setOnClickListener(view -> {//aquiiiiiiiiiiiiiiiiiiii
            startActivity(new Intent(getContext(), RegisterActivity.class));
            //Toast.makeText(getContext(), "pulsado", Toast.LENGTH_SHORT).show();
        });

        View botonHab1 = v.findViewById(R.id.dosis1);
        botonHab1.setOnClickListener(view -> {//aquiiiiiiiiiiiiiiiiiiii
            startActivity(new Intent(getContext(), HabitacionesActivity.class));
            //Toast.makeText(getContext(), "pulsado", Toast.LENGTH_SHORT).show();
        });

        View botonHab2 = v.findViewById(R.id.dosis2);
        botonHab2.setOnClickListener(view -> {//aquiiiiiiiiiiiiiiiiiiii
            startActivity(new Intent(getContext(), HabitacionesActivity.class));
            //Toast.makeText(getContext(), "pulsado", Toast.LENGTH_SHORT).show();
        });

        View botonHab3 = v.findViewById(R.id.dosis3);
        botonHab3.setOnClickListener(view -> {//aquiiiiiiiiiiiiiiiiiiii
            startActivity(new Intent(getContext(), HabitacionesActivity.class));
            //Toast.makeText(getContext(), "pulsado", Toast.LENGTH_SHORT).show();
        });

        View botonHab4 = v.findViewById(R.id.dosis4);
        botonHab4.setOnClickListener(view -> {//aquiiiiiiiiiiiiiiiiiiii
            startActivity(new Intent(getContext(), HabitacionesActivity.class));
            //Toast.makeText(getContext(), "pulsado", Toast.LENGTH_SHORT).show();
        });
        return v;//------------------------------------------------------- ud 2 ultimo punto getContext()

    }
*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        binding = FragmentDoctorHabitacionesBinding.inflate(getLayoutInflater());
        Query query = FirebaseFirestore.getInstance()
                .collection("habitaciones");
                //.whereEqualTo("numHab", "1");


        FirestoreRecyclerOptions<Habitacion> opciones = new FirestoreRecyclerOptions
                .Builder<Habitacion>().setQuery(query, Habitacion.class).build();
        adaptador = new AdaptadorHabsDoctor(opciones, getContext());
        System.out.println(getContext());
        binding.recyclerView.setAdapter(adaptador);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // Inflate the layout for this fragment


        return binding.getRoot();
    }
    @Override
    public void onStart() {
        super.onStart();
        adaptador.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        adaptador.stopListening();
    }
}