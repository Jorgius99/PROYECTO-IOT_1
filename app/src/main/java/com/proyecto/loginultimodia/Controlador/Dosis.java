package com.proyecto.loginultimodia.Controlador;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.proyecto.loginultimodia.AdaptadorDosis;
import com.proyecto.loginultimodia.Usuario;
import com.proyecto.loginultimodia.ObjetoDosis;

import com.proyecto.loginultimodia.databinding.FragmentDosisBinding;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Dosis#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Dosis extends Fragment {
    private static Usuario usuarioConDatos;

    private FragmentDosisBinding binding; //si no estÃ¡
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
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_dosis, container, false);
        }
     */

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
    Date datee = new Date();
    long milisss = System.currentTimeMillis();
    binding = FragmentDosisBinding.inflate(getLayoutInflater());
    Query query = FirebaseFirestore.getInstance()

            .collection("dosis")
            .whereEqualTo("dni", usuarioConDatos.getDNI()).whereGreaterThan("milis", milisss);
    FirestoreRecyclerOptions<ObjetoDosis> opciones = new FirestoreRecyclerOptions

            .Builder<ObjetoDosis>().setQuery(query, ObjetoDosis.class).build();
    Log.d("caca", String.valueOf(datee));
    Log.d("lele", String.valueOf(milisss));

    Log.d("Weonao",""+opciones.getSnapshots());
    adaptador = new AdaptadorDosis(opciones, getContext());
    System.out.println(getContext());
    binding.recyclerViewD.setAdapter(adaptador);
    binding.recyclerViewD.setLayoutManager(new LinearLayoutManager(getContext()));
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