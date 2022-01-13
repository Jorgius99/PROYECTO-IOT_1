package com.example.loginultimodia.Controlador;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.loginultimodia.AdaptadorAvisos;
import com.example.loginultimodia.Aviso;
import com.example.loginultimodia.R;
import com.example.loginultimodia.Usuario;
import com.example.loginultimodia.databinding.FragmentAvisosBinding;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Avisos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Avisos extends Fragment {
    private FragmentAvisosBinding binding; //si no está
    public static AdaptadorAvisos adaptador;
    private static Usuario usuarioConDatos;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Avisos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Avisos.
     */
    // TODO: Rename and change types and number of parameters
    public static Avisos newInstance(String param1, String param2) {
        Avisos fragment = new Avisos();
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
    }/*
    public static void sacaDatos(String email){

        final Usuario[] usuarioSacado = {new Usuario()};
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("pacientes").whereEqualTo("email",email).get().addOnCompleteListener(task ->  {
            if (task.isSuccessful()) {
                QuerySnapshot document = task.getResult();
                DocumentSnapshot docOC = document.getDocuments().get(0);
                if (document != null) {
                    usuarioSacado[0] = docOC.toObject(Usuario.class);// here
                    Log.d("USUARIOSACAD034",""+ usuarioSacado[0].getEmail());
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
            binding = FragmentAvisosBinding.inflate(getLayoutInflater());
            //Log.d("AVERSISTA", "" + usuarioConDatos);
            Query query = FirebaseFirestore.getInstance()
                    .collection("avisos")
                    .whereEqualTo("dni", usuarioConDatos.getDNI());
            FirestoreRecyclerOptions<Aviso> opciones = new FirestoreRecyclerOptions
                    .Builder<Aviso>().setQuery(query, Aviso.class).build();
            adaptador = new AdaptadorAvisos(opciones, getContext());
            System.out.println(getContext());
            binding.recyclerView1.setAdapter(adaptador);
            binding.recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));
            // Inflate the layout for this fragment
            adaptador.startListening();
            return binding.getRoot();
    }
/*
    @Override
    public void onStart() {
        super.onStart();
    }
    @Override
    public void onStop() {
        super.onStop();
        adaptador.stopListening();
    }*/

}