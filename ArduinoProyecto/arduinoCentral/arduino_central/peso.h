
#include "HX711.h"

const int DOUT = 5;
const int CLK = 2;

HX711 balanza;

float getpeso() {
  return balanza.get_units(20);
}

int cuantas_hay() {
  return getpeso() / 0.55;
}

void iniciar_peso(){
  balanza.begin(DOUT, CLK);
  balanza.set_scale(3500); // Establecemos la escala
  balanza.tare(20);  //El peso actual es considerado Tara.
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
