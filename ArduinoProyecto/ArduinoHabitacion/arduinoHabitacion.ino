#include "WiFi.h"
#include "AsyncUDP.h"
#include <M5Stack.h>
#include <ArduinoJson.h>
#include <DHT.h>
#include "infrarrojos.h"
#include "enviar.h"
#include "sth.h"



void setup() {

    M5.begin();
/*
 * parte wifi
 */
   iniciarWifiEnvio();
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
    M5.Speaker.mute();  
    /*
     * ponemos M5 a cero
     */
    M5.Lcd.setTextSize(3);
    M5.Lcd.setCursor(0,0);
    M5.Lcd.clear();
    /*
     * variables del programa
     */
    String mensaje="";
    float humedad_Temperatura[2];
    /*
     * mostramos humedad y temperartura
     */
    humedadTemperatura(&humedad_Temperatura[0]);
    M5.Lcd.print("Humedad:");
    M5.Lcd.println(humedad_Temperatura[0]);
    M5.Lcd.print("Temperatura:");
    M5.Lcd.print(humedad_Temperatura[1]);
    /*
     * comprobamos el estado H&T
     */
    if (temperaturaAlta(humedad_Temperatura[1])) {
        mensaje = "temperatura alta H2 ";
    }
    if (humedadAlta(humedad_Temperatura[0])) {
        mensaje = mensaje + "humedad alta H2";
    }
    /*
     * miramos las pastillas
     */
     while(hayAlgunaPastilla()){
      encenderLuz();
     }
     apagarLuz();
    
    /*
     * nos comunicamos con el otro arduino
     */
    enviarTexto(mensaje,5678);
    delay(500);
}
