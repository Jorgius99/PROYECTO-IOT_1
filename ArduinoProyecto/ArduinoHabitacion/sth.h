

#define DHTPIN 26 // pin de salida
#define DHTTYPE DHT11 // DHT 11
DHT dht(DHTPIN, DHTTYPE);



void humedadTemperatura(float *casilla){
  float h = dht.readHumidity();
  float t = dht.readTemperature();
//calibracion
/*
 * Serial.print(h);
 * Serial.print(t)
 */
  casilla[0]=h;
  casilla[1]=t;
  
}
bool temperaturaAlta(float lectura){
    if (lectura>35){
        return true;
    }
    return false;
}
bool humedadAlta(float lectura){
    if (lectura>75){
        return true;
    }
    return false;
}
