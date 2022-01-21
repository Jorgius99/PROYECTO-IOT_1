package com.example.dmesmun_upv.raspprueba;



import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.Date;

public class MqttManager {


        public static final String TAG = "MQTT";

        public static final String topicRoot = "caph/";
        public static final int qos = 2;
        public static final String broker = "tcp://broker.hivemq.com:1883";
        public static final String clientId = String.valueOf(System.currentTimeMillis());

        public static MqttClient client;

        public static void suscribirMqtt(String topic, MqttCallback listener) {
            try {
                System.out.println("Suscrito a " + topicRoot + topic);
                client.setCallback(listener);
                System.out.println("Anadi el callback");
                client.subscribe(topicRoot + topic +"#", qos);
                System.out.println("Me suscribí");

            } catch (MqttException e) {
                System.out.print("Error al suscribir.");
            }
        }

        public static void conectarMqtt() {
            try {
                System.out.print("Conectando al broker " + broker);
                client = new MqttClient(broker, clientId, new MemoryPersistence());
                client.connect();
            } catch (MqttException e) {
                System.out.print("Error al conectar.");
            }
        }

        public static void desconectarMqtt() {
            try {
                client.disconnect();
                System.out.print("Desconectado");
            } catch (MqttException e) {
                System.out.print("Error al desconectar.");
            }
        }

    }

