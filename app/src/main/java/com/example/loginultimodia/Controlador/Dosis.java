package com.example.loginultimodia.Controlador;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.example.loginultimodia.AdaptadorDosis;
import com.example.loginultimodia.Usuario;
import com.example.loginultimodia.objetoDosis;
import com.example.loginultimodia.R;

import com.example.loginultimodia.databinding.FragmentDosisBinding;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Dosis#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Dosis extends Fragment {
    private static Usuario usuarioConDatos;

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
    public static void sacaDatos(String email){

        final Usuario[] usuarioSacado = {new Usuario()};

        //Usuario nuevoUser = new Usuario();
       /* Query query = FirebaseFirestore.getInstance()
                .collection("pacientes")
                .whereEqualTo("email", usuario.getEmail());*/
        FirebaseFirestore db = FirebaseFirestore.getInstance();
       /* Task nuevoUser = db.collection("pacientes").whereEqualTo("email",email).get();

        if(nuevoUser.isComplete()){
            rellenarUsuario(nuevoUser);
        }*/
        db.collection("pacientes").whereEqualTo("email",email).get().addOnCompleteListener(task ->  {
            if (task.isSuccessful()) {
                QuerySnapshot document = task.getResult();
                DocumentSnapshot docOC = document.getDocuments().get(0);

                if (document != null) {
                    usuarioSacado[0] = docOC.toObject(Usuario.class);// here
                    Log.d("USUARIOSACAD0",""+ usuarioSacado[0].getEmail());

                    rellenarUsuario(usuarioSacado[0]);

                   /* nuevoUser.setEmail(usuarioSacado[0].getEmail());
                    nuevoUser.setDNI(usuarioSacado[0].getDNI());
                    nuevoUser.setTipoUser(usuarioSacado[0].getTipoUser());
                    nuevoUser.setDNIDOCTOR(usuarioSacado[0].getDNIDOCTOR());
                    nuevoUser.setNombreApellido(usuarioSacado[0].getNombreApellido());
                    nuevoUser.setNumHabitacion(usuarioSacado[0].getNumHabitacion());
                    Log.d("NUEVOUSEREEE", ""+nuevoUser);*/
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
    binding = FragmentDosisBinding.inflate(getLayoutInflater());
    Query query = FirebaseFirestore.getInstance()
            .collection("dosis")
            .whereEqualTo("dni", usuarioConDatos.getDNI());
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