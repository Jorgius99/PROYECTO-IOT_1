package com.example.loginultimodia.ControladorDoctor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.loginultimodia.AdaptadorDosisDoc;
import com.example.loginultimodia.Controlador.PagerControler;
import com.example.loginultimodia.DosisActivity;
import com.example.loginultimodia.Habitacion;
import com.example.loginultimodia.IniciarDosisDocPaciente;
//import com.example.loginultimodia.MasDosisActivity;
import com.example.loginultimodia.R;
import com.example.loginultimodia.Usuario;
import com.example.loginultimodia.AnyadirDosisActivity;
import com.example.loginultimodia.Habitacion;
import com.example.loginultimodia.databinding.FragmentDoctorDosisBinding;
import com.example.loginultimodia.databinding.FragmentDosisBinding;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import static com.example.jmarzoz.izquierda.Mqtt.*;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Dosis#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Dosis extends Fragment implements MqttCallback {
    private static MqttClient client;
    private String correoHab;
    private static Usuario usuarioConDatos;
   // private FragmentDosisBinding bindingDosisPaciente ; //si no estÃ¡

    private FragmentDoctorDosisBinding binding ; //si no está
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
        conectarMqtt();
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
        //View v = inflater.inflate(R.layout.fragment_doctor_dosis, container, false);

        binding = FragmentDoctorDosisBinding.inflate(getLayoutInflater());

        binding.imageView01.setOnClickListener(view -> {//aquiiiiiiiiiiiiiiiiiiii
            startActivity(new Intent(getContext(), AnyadirDosisActivity.class));
            //Toast.makeText(getContext(), "pulsado", Toast.LENGTH_SHORT).show();
        });
        /*
        empieza
         */

        binding.izquierda.setOnClickListener(view -> {//aquiiiiiiiiiiiiiiiiiiii
           publicarMqtt("DIRECCION","i");
        });
        binding.derecha.setOnClickListener(view -> {//aquiiiiiiiiiiiiiiiiiiii
            publicarMqtt("DIRECCION","d");
        });


/*
fin
 */

        Query query = FirebaseFirestore.getInstance()
                .collection("habitaciones")
                .whereEqualTo("ocupacion", true);
                //.whereEqualTo("numHab", "1");

        FirestoreRecyclerOptions<Habitacion> opciones = new FirestoreRecyclerOptions
                .Builder<Habitacion>().setQuery(query, Habitacion.class).build();
        adaptador = new AdaptadorDosisDoc(opciones, getContext());
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
                            IniciarDosisDocPaciente.rellenarUsuario(usuarioConDatos);
                            Log.d("EWEWEWEWEW",""+correoSacado[0]);
                            obtenerCorreoDeHabitacion(correoSacado[0].getEmail());
                        }else{

                                Log.d("ERROR", "Error getting documents: ", task.getException());

                        }
                        {
                            startActivity(new Intent(getContext(), IniciarDosisDocPaciente.class));

                        }
                    }
                });

               /* {
                    //com.example.loginultimodia.Controlador.Dosis.sacaDatos(correoHab);


                }*/
            }
        });
        return binding.getRoot();
        //return v;
    }
    public static Usuario rellenarUsuario(Usuario ussus)  {
        usuarioConDatos  = ussus;
        Log.d("FAFA", ""+usuarioConDatos);
        return usuarioConDatos;
    }
    public void obtenerCorreoDeHabitacion(String correuu){
    correoHab = correuu;
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
public static void conectarMqtt() {
    try {
        Log.i(TAG, "Conectando al broker " + broker);
        client = new MqttClient(broker, clientId, new MemoryPersistence());
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        connOpts.setKeepAliveInterval(60);
        connOpts.setWill(topicRoot+"WillTopic","App desconectada".getBytes(),
                qos, false);
        client.connect(connOpts);
    } catch (MqttException e) {
        Log.e(TAG, "Error al conectar.", e);
    }
}
    public static void publicarMqtt(String topic, String mensageStr) {
        try {
            MqttMessage message = new MqttMessage(mensageStr.getBytes());
            message.setQos(qos);
            message.setRetained(false);
            client.publish(topicRoot + topic, message);
            Log.i(TAG, "Publicando mensaje: " + topic+ "->"+mensageStr);
        } catch (MqttException e) {
            Log.e(TAG, "Error al publicar." + e);
        }
    }
    public static void deconectarMqtt() {
        try {
            client.disconnect();
            Log.i(TAG, "Desconectado");
        } catch (MqttException e) {
            Log.e(TAG, "Error al desconectar.", e);
        }
    }
    @Override
    public void onDestroy() {
        deconectarMqtt();
        super.onDestroy();
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

    @Override public void connectionLost(Throwable cause) {
        Log.d(TAG, "Conexión perdida");
    }
    @Override public void deliveryComplete(IMqttDeliveryToken token) {
        Log.d(TAG, "Entrega completa");
    }
    @Override public void messageArrived(String topic, MqttMessage message)
            throws Exception {
        String payload = new String(message.getPayload());
        Log.d(TAG, "Recibiendo: " + topic + "->" + payload);
    }
}
