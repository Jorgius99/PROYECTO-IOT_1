package com.example.loginultimodia.ControladorDoctor;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.loginultimodia.AdaptadorDosisDoc;
import com.example.loginultimodia.Habitacion;
import com.example.loginultimodia.MasDosisActivity;
import com.example.loginultimodia.R;
import com.example.loginultimodia.databinding.FragmentDoctorDosisBinding;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Dosis#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Dosis extends Fragment {
    private FragmentDoctorDosisBinding binding; //si no está
    public static AdaptadorDosisDoc adaptador;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Dosis() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Dosis.
     */
    // TODO: Rename and change types and number of parameters
    public static Dosis newInstance(String param1, String param2) {
        Dosis fragment = new Dosis();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }
/*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doctor_dosis, container, false);
    }
    */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_doctor_dosis, container, false);

        View botonHab0 = v.findViewById(R.id.imageView01);
        botonHab0.setOnClickListener(view -> {//aquiiiiiiiiiiiiiiiiiiii
            startActivity(new Intent(getContext(), MasDosisActivity.class));
            //Toast.makeText(getContext(), "pulsado", Toast.LENGTH_SHORT).show();
        });
        binding = FragmentDoctorDosisBinding.inflate(getLayoutInflater());
        Query query = FirebaseFirestore.getInstance()
                .collection("habitaciones");
                //.whereEqualTo("numHab", "1");
        FirestoreRecyclerOptions<Habitacion> opciones = new FirestoreRecyclerOptions
                .Builder<Habitacion>().setQuery(query, Habitacion.class).build();
        adaptador = new AdaptadorDosisDoc(opciones, getContext());
        System.out.println(getContext());
        binding.recyclerView.setAdapter(adaptador);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Inflate the layout for this fragment
        //return binding.getRoot();
        return v;


    }
/*
    @Override
    public View onCreateView2(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_doctor_dosis, container, false);

        View botonHab0 = v.findViewById(R.id.imageView01);
        botonHab0.setOnClickListener(view -> {//aquiiiiiiiiiiiiiiiiiiii
            startActivity(new Intent(getContext(), MasDosisActivity.class));
            //Toast.makeText(getContext(), "pulsado", Toast.LENGTH_SHORT).show();

        // Inflate the layout for this fragment
        return v;


    }
    */

/*
    @Override
    public void añadirDosis(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {

            View v = inflater.inflate(R.layout.fragment_doctor_dosis, container, false);

            View botonHab0 = v.findViewById(R.id.imageView01);
            botonHab0.setOnClickListener(view -> {//aquiiiiiiiiiiiiiiiiiiii
                startActivity(new Intent(getContext(), MasDosisActivity.class));
                //Toast.makeText(getContext(), "pulsado", Toast.LENGTH_SHORT).show();
            });
            return v;
    }
  */
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
