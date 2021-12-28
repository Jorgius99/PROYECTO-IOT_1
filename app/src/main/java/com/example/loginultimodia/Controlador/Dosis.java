package com.example.loginultimodia.Controlador;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.example.loginultimodia.AdaptadorDosis;
import com.example.loginultimodia.objetoDosis;
import com.example.loginultimodia.R;

import com.example.loginultimodia.databinding.FragmentDosisBinding;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Dosis#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Dosis extends Fragment {
    private FragmentDosisBinding binding; //si no está
    public static AdaptadorDosis adaptador;
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
        return inflater.inflate(R.layout.fragment_dosis, container, false);
    }
 */
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
    binding = FragmentDosisBinding.inflate(getLayoutInflater());
    Query query = FirebaseFirestore.getInstance()
            .collection("dosis")
            .whereEqualTo("dni", "44896786g");
    FirestoreRecyclerOptions<objetoDosis> opciones = new FirestoreRecyclerOptions
            .Builder<objetoDosis>().setQuery(query, objetoDosis.class).build();
    adaptador = new AdaptadorDosis(opciones, getContext());
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