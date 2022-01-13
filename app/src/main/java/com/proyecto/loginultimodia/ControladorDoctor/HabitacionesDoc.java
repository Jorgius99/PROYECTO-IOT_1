package com.proyecto.loginultimodia.ControladorDoctor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.proyecto.loginultimodia.AdaptadorHabsDoctor;
import com.proyecto.loginultimodia.Habitacion;
import com.proyecto.loginultimodia.Usuario;
import com.proyecto.loginultimodia.databinding.FragmentDoctorHabitacionesBinding;
import com.proyecto.loginultimodia.iniciarHabsDocPaciente;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HabitacionesDoc#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HabitacionesDoc extends Fragment {
    private FragmentDoctorHabitacionesBinding binding; //si no está
    public static AdaptadorHabsDoctor adaptador;
    private static Usuario usuarioConDatos;
    private String correoHab;



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
*/ /*
public static void sacaDatos(String email){
    final Doctor[] usuarioSacado = {new Doctor()};

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    db.collection("doctor").whereEqualTo("email",email).get().addOnCompleteListener(task ->  {
        if (task.isSuccessful()) {
            QuerySnapshot document = task.getResult();
            DocumentSnapshot docOC = document.getDocuments().get(0);
            Log.d("JEUAJE2","eueueueuueueueueueueueueu");

            if (document != null) {
                usuarioSacado[0] = docOC.toObject(Doctor.class);// here
                Log.d("JEUAJE",""+ usuarioSacado[0].getEmail());
                rellenarUsuario(usuarioSacado[0]);
            }
        }
    });
    // Log.d("NUEVOUSER", ""+nuevoUser);
}*/
    public static Usuario rellenarUsuario(Usuario ussus)  {
        usuarioConDatos  = ussus;
        Log.d("FAFA", ""+usuarioConDatos);
        return usuarioConDatos;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View v = inflater.inflate(R.layout.fragment_doctor_dosis, container, false);

        binding = FragmentDoctorHabitacionesBinding.inflate(getLayoutInflater());

        Query query = FirebaseFirestore.getInstance()
                .collection("habitaciones");
        //.whereEqualTo("numHab", "1");

        FirestoreRecyclerOptions<Habitacion> opciones = new FirestoreRecyclerOptions
                .Builder<Habitacion>().setQuery(query, Habitacion.class).build();
        adaptador = new AdaptadorHabsDoctor(opciones, getContext());
        System.out.println(getContext());
        binding.recyclerView1.setAdapter(adaptador);
        binding.recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));

        adaptador.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getContext(), PagerControler.class));
                // int pos = binding.recyclerView.getChildAdapterPosition(v);
                final Usuario[] correoSacado = {new Usuario()};

                String habitacion = (String) (v.getTag());
                Toast.makeText(getContext(), "" + habitacion, Toast.LENGTH_SHORT).show();
                FirebaseFirestore db = FirebaseFirestore.getInstance();

                db.collection("pacientes").whereEqualTo("numHabitacion",habitacion).get().addOnCompleteListener(task ->  {
                    if (task.isSuccessful()) {
                        QuerySnapshot document = task.getResult();
                        DocumentSnapshot docOC = document.getDocuments().get(0);
                        if (document != null) {
                            correoSacado[0] = docOC.toObject(Usuario.class);// here
                            //Log.d("USUARIOSACAD0",""+ correoSacado[0].getEmail());
                            rellenarUsuario(correoSacado[0]);
                            iniciarHabsDocPaciente.rellenarUsuario(usuarioConDatos);
                            Log.d("EWEWEWEWEW",""+correoSacado[0]);
                            obtenerCorreoDeHabitacion(correoSacado[0].getEmail());
                        }else{

                            Log.d("ERRORhfjhfkjshkdf", "Error getting documents: ", task.getException());

                        }
                        {
                            startActivity(new Intent(getContext(), iniciarHabsDocPaciente.class));

                        }
                    }
                });

               /* {
                    //Dosis.sacaDatos(correoHab);


                }*/
            }
        });
        return binding.getRoot();
        //return v;
    }
    public void obtenerCorreoDeHabitacion(String correuu){
        correoHab = correuu;
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