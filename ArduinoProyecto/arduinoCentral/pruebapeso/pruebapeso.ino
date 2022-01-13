/*
#include <M5Stack.h>
#include "HX711.h"
const int DOUT=5;
const int CLK=2;


HX711 balanza;
void setup() {
  Serial.begin(115200);
  balanza.begin(DOUT, CLK);
  Serial.print("Lectura del valor del ADC:t");
  Serial.println(balanza.read());
  Serial.println("No ponga ningún objeto sobre la balanza");
  Serial.println("Destarando...");
  balanza.set_scale(); //La escala por defecto es 1
  balanza.tare(20);  //El peso actual es considerado Tara.
  Serial.println("Coloque un peso conocido:");
}

void loop() {

  Serial.print("Valor de lectura: t");
  Serial.println(balanza.get_value(10),3);
  delay(100);
}
*/

#include "HX711.h"

#include <M5Stack.h>

const int DOUT=5;
const int CLK=2;

HX711 balanza;
float getpeso(){
  return balanza.get_units(20);
}
int cuantas_hay(){
  return getpeso()/0.0053;
}


void setup() {
  M5.begin();
  balanza.begin(DOUT, CLK);
  balanza.set_scale(785000); // Establecemos la escala
  balanza.tare(20);  //El peso actual es considerado Tara.
}

void loop() {
  Serial.println(getpeso(),5);
  M5.Lcd.setTextSize(10);
  int a=cuantas_hay();
  M5.Lcd.setCursor(0,0);
  M5.Lcd.clear();
  M5.Lcd.print(a);
  delay(500);
  
}  
                             
/*
 * montaje:
 * 
 * rojo->   e+           vcc->5v
 * negro->  e-    HX711  GND->GND   M5stack
 * verde->  a-           DT-> 5
 * blanco-> a+           SCK->2      
 */
 /*
  * Programa para tarar
#include <M5Stack.h>
#include "HX711.h"
const int DOUT=5;
const int CLK=2;


HX711 balanza;
void setup() {
  Serial.begin(115200);
  balanza.begin(DOUT, CLK);
  Serial.print("Lectura del valor del ADC:t");
  Serial.println(balanza.read());
  Serial.println("No ponga ningún objeto sobre la balanza");
  Serial.println("Destarando...");
  balanza.set_scale(); //La escala por defecto es 1
  balanza.tare(20);  //El peso actual es considerado Tara.
  Serial.println("Coloque un peso conocido:");
}

void loop() {

  Serial.print("Valor de lectura: t");
  Serial.println(balanza.get_value(10),3);
  delay(100);
}
*/
