package com.example.dmesmun_upv.raspprueba;

import static com.example.dmesmun_upv.raspprueba.MqttManager.suscribirMqtt;


import com.google.api.core.ApiFuture;
import com.google.api.services.storage.Storage;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;

import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;


import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import io.grpc.LoadBalancerRegistry;
import io.grpc.internal.PickFirstLoadBalancerProvider;


public class RaspPrueba {


    public static String PATH_TO_CREDENTIALS =
            //"C:\\rasp\\asincronaejercicio-firebase-adminsdk-q6e7j-f16117548b.json";
"./asincronaejercicio-firebase-adminsdk-q6e7j-f16117548b.json";

    static void guardarFirestore(String numhab, String medida, String tipo) {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("habitaciones").document(numhab);
        ApiFuture<WriteResult> result = docRef.update(tipo, medida);

    }

    public static void main(String[] args) {

        System.out.println("Starting Raspberry");
        System.getProperty("java.classpath");
    /*
    cambiarlo luego
     */
        LoadBalancerRegistry.getDefaultRegistry().register(new PickFirstLoadBalancerProvider());
        try {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(
                            new FileInputStream(PATH_TO_CREDENTIALS)))
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // guardarFirestore("ilhoaihfoñais","bduf");
        //-------------------------------------------------------------------
        MqttManager.conectarMqtt();

        suscribirMqtt("", new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
            }

            @Override
            public void messageArrived(String arg0, MqttMessage arg1) {

                System.out.println("  Topic:\t" + arg0 + "  Message:\t" + new String(arg1.getPayload()));
                if (arg0.equals("caph/habitacion1temperatura")) {
                    //db.collection("habitaciones").document("1").update({"Temperatura": new String(arg1.getPayload())});
                    guardarFirestore("1",new String(arg1.getPayload()),"Temperatura");

                }
                if (arg0.equals("caph/habitacion2temperatura")) {
                    //db.collection("habitaciones").document("2").update( {"Temperatura" : new String(arg1.getPayload())});
                    guardarFirestore("2",new String(arg1.getPayload()),"Temperatura");
                }
                if (arg0.equals("caph/habitacion1humedad")) {
                    // db.collection("habitaciones").document("1").update( {"Humedad" : new String(arg1.getPayload())});
                    guardarFirestore("1",new String(arg1.getPayload()),"Humedad");
                }
                    if (arg0.equals("caph/habitacion2humedad")) {
                        // db.collection("habitaciones").document("2").update( {"Humedad" : new String(arg1.getPayload())});
                        guardarFirestore("2",new String(arg1.getPayload()),"Humedad");
                    }

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken arg0) {
            }

        });
    }

    //Methods


}