#include <M5Stack.h>
#include <Servo.h>
#include <ArduinoJson.h>
#include "WiFi.h"
#include "AsyncUDP.h"
#include "wifiRecibo.h"
#include "peso.h"


/*
 *  servo variables
 */

int pos = 0;
int servoPin = 26;
Servo myservo;

/*
 * setup()
 */
void setup() {
  M5.begin();
  M5.Lcd.setTextSize(2); //Tamaño del texto
  
  /*
   * iniciamos recibo de wifi
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
 * iniciamos peso
 */
  iniciar_peso();
M5.Speaker.mute();  
}

void loop() {
  M5.Speaker.mute();  
/*
 * mostramos peso
 */
  //Serial.println(getpeso(), 5);
  M5.Lcd.setTextSize(10);
  int a = cuantas_hay();
  M5.Lcd.setCursor(0, 0);
  M5.Lcd.clear();
  M5.Lcd.println(a);
  M5.Lcd.setTextSize(4);
  M5.update();
  /*
   * mostramos mensaje udp
   */
  if (rec) {
    rec = 0;
    udp.broadcastTo("Recibido", 5678); //Confirmación
    udp.broadcastTo(texto, 5678); //reenvía lo recibido

    String mensaje = String(texto);
    M5.Lcd.println(mensaje);
  }
  /*
   * movemos servo
   */
  if ( M5.BtnA.read()==1) {
    myservo.write(60);
    delay(400);
  }
  if ( M5.BtnB.read()==1) {
    myservo.write(130);
    delay(400);
  }

  delay(800);
}
