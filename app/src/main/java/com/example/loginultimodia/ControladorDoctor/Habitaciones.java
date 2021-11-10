package com.example.loginultimodia.ControladorDoctor;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.loginultimodia.DoctorSecondActivity;
import com.example.loginultimodia.R;
import com.example.loginultimodia.RegisterActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Habitaciones#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Habitaciones extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Habitaciones() {
        // Required empty public constructor
    }


    public static Habitaciones newInstance(String param1, String param2) {
        Habitaciones fragment = new Habitaciones();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_doctor_habitaciones, container, false);
         Button tvRegisterHere = v.findViewById(R.id.tvRegisterHeress);

        tvRegisterHere.setOnClickListener(view -> {//aquiiiiiiiiiiiiiiiiiiii
            startActivity(new Intent(getContext(), RegisterActivity.class));
            //Toast.makeText(getContext(), "pulsado", Toast.LENGTH_SHORT).show();

        });
        return v;//------------------------------------------------------- ud 2 ultimo punto getContext()

    }

}