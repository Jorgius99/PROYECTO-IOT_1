#include <M5Stack.h>
#include <Servo.h>
#include <ArduinoJson.h>
#include "WiFi.h"
#include "AsyncUDP.h"
#include "wifiRecibo.h"
#include "peso.h"

/*
    servo variables
*/
int Peso;
String notificaciones[4];
int pos = 0;
int servoPin = 26;
Servo myservo;

/*
   setup()
*/
void setup() {
  M5.begin();
  M5.Lcd.setTextSize(2); //Tamaño del texto
  /*
     iniciamos recibo de wifi
  */
  iniciarWifiRecibo();
  /*
     iniciamos servo
  */
  
    myservo.attach(servoPin);
    myservo.write(0);
    delay(5000);
    myservo.write(60);
    
    
    /*
    iniciamos peso
  */
  iniciar_peso();
  M5.Speaker.mute();
}

void loop() {
  M5.Speaker.mute();
  /*
     mostramos peso
  */

  M5.Lcd.setTextSize(10);
  Serial.println(getpeso());
  int a = cuantas_hay();
  M5.Lcd.setCursor(0, 0);
  M5.Lcd.clear();
  M5.Lcd.println(a);
  M5.Lcd.setTextSize(4);
  M5.update();
  /*
     mostramos mensaje udp
  */
  //Serial.println("antes de entrar");
  if (rec) {
    rec = 0;
    udp.broadcastTo("Recibido", 5678); //Confirmación
    udp.broadcastTo(texto, 5678); //reenvía lo recibido
    String mensaje = String(texto);
    
    if (pos == 4) {
      pos = 0;
      
    }
    notificaciones[pos] = mensaje;
    pos++;
  }
  for (int i = 0; i < 4; i++) {
     M5.Lcd.setTextSize(2);
    M5.Lcd.println(notificaciones[i]);
  }
  /*
     movemos servo
  */
  //Serial.println("antes del boton");
  if ( M5.BtnA.read() == 1) {
    
    myservo.write(60);
    delay(400);
  }
  if ( M5.BtnC.read() == 1) {
    
    myservo.write(130);
    delay(400);
  }
  delay(800);
}
