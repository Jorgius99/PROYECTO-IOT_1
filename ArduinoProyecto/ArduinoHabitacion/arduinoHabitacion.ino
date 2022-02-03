#include <ArduinoMqttClient.h>
#include <M5Stack.h>
#include "WiFi.h"
#include "AsyncUDP.h"
#include <DHT.h>
#include "infrarrojos.h"
#include "enviar.h"
#include "sth.h"

WiFiClient wificlient;
MqttClient mqttClient(wificlient);
const char broker[] = "broker.hivemq.com";
int port = 1883;
const char topic1[] = "caph/habitacion1temperatura";
const char topic2[] = "caph/habitacion2temperatura";
const char topic3[] = "caph/habitacion1humedad";
const char topic4[] = "caph/habitacion2humedad";

/*
const long interval = 1000;
unsigned long previousMillis = 0;
int count = 0;
*/

int flag=0;
int flagHumedad=0;
int flagTemperatura=0;
float humedad_Temperatura[2];
String mensaje="";

void setup() {

    M5.begin();
    
/*
 * parte wifi
 */
 iniciarWifiEnvio();
 mqttClient.setId(String(millis()));
  if (!mqttClient.connect(broker, port)) {
 Serial.print("MQTT connection failed! Error code = ");
 Serial.println(mqttClient.connectError());
 while (1);
 }
 Serial.println("You're connected to the MQTT broker!");
 Serial.println();

/*
 * HumedadTemperatura
 */
    dht.begin();
    /*
     * infrarrojos
     */
    iniciarInfrarrojos();
   
}

void loop() {
    //M5.Speaker.mute();  
    /*
     * ponemos M5 a cero
     */
    M5.Lcd.setTextSize(3);
    M5.Lcd.setCursor(0,0);
    M5.Lcd.clear();
    
    /*
     * mostramos humedad y temperartura
     */
    humedadTemperatura(&humedad_Temperatura[0]);
    M5.Lcd.print("Humedad:");
    M5.Lcd.println(humedad_Temperatura[0]);
    
    mqttClient.beginMessage(topic3);
    String h=String(humedad_Temperatura[0])+"%";
    mqttClient.print(h);
    mqttClient.endMessage();
    
    M5.Lcd.print("Temperatura:");
    M5.Lcd.print(humedad_Temperatura[1]);
    delay(50);
    
    mqttClient.beginMessage(topic1);
    String t=String(humedad_Temperatura[1])+" C";
    mqttClient.print(t);
    mqttClient.endMessage();
    delay(200);
    /*
     * comprobamos el estado H&T
     */
    if (temperaturaAlta(humedad_Temperatura[1])) {
      if(flagTemperatura==0){
      mensaje = "temperatura alta H1 ";
      enviarTexto(mensaje, 5678);
      flagTemperatura=1;
      }
    }else{
       flagTemperatura=0;
    }
    
    if (humedadAlta(humedad_Temperatura[0])) {
      
      if(flagHumedad==0){
      mensaje = mensaje + "humedad alta H1 ";
      enviarTexto(mensaje, 5678);
      flagHumedad=1;
      }
    }else{
      flagHumedad=0;
    }
    /*
     * miramos las pastillas
     */
    if (hayAlgunaPastilla()) {
      encenderLuz();
    
    } else {
      apagarLuz();
    }
    /*
     * nos comunicamos con el otro arduino
     */
    delay(600);
    M5.update();
}
