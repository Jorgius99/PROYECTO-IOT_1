#include <SPI.h>//https://www.arduino.cc/en/reference/SPI
#include <MFRC522.h>//https://github.com/miguelbalboa/rfid
#include <Servo.h>
//Constants
#define SS_PIN 5
#define RST_PIN 0

#define abierto 33
#define cerrado 26
//servo
int pos = 0;
int servoPin = 25;
Servo myservo;
//Parameters
const int ipaddress[4] = {103, 97, 67, 25};

//Variables
byte nuidPICC[4] = {0, 0, 0, 0};
MFRC522::MIFARE_Key key;
MFRC522 rfid = MFRC522(SS_PIN, RST_PIN);

void setup() {
  //Init Serial USB
  Serial.begin(115200);
  Serial.println(F("Initialize System"));
  //init rfid D8,D5,D6,D7
  SPI.begin();
  rfid.PCD_Init();

  Serial.print(F("Reader :"));
  rfid.PCD_DumpVersionToSerial();
  pinMode(abierto, OUTPUT);
  pinMode(cerrado, OUTPUT);
  /*
     iniciamos el servo
  */
  myservo.attach(servoPin);
  myservo.write(90);

}

void loop() {

  comprobarRFID();

}

void comprobarRFID() { /* function readRFID */
  ////Read RFID card

  for (byte i = 0; i < 6; i++) {
    key.keyByte[i] = 0xFF;
  }
  // Look for new 1 cards
  if ( ! rfid.PICC_IsNewCardPresent())
    return;

  // Verify if the NUID has been readed
  if (  !rfid.PICC_ReadCardSerial())
    return;

  // Store NUID into nuidPICC array
  for (byte i = 0; i < 4; i++) {
    nuidPICC[i] = rfid.uid.uidByte[i];
  }

  //Serial.print(F("RFID In hex: "));
  printHex(rfid.uid.uidByte, rfid.uid.size);
  if (parseToString(rfid.uid.uidByte, rfid.uid.size) == "17b9f63" || parseToString(rfid.uid.uidByte, rfid.uid.size) == "e27f3811") {
    digitalWrite(abierto, HIGH);
    myservo.write(180
    );
    Serial.println("abierto");
    delay(1000);
    digitalWrite(abierto, LOW);
    myservo.write(90);
  } else {
    digitalWrite(cerrado, HIGH);
    delay(1000);
    digitalWrite(cerrado, LOW);
  }

  // Halt PICC
  rfid.PICC_HaltA();

  // Stop encryption on PCD
  rfid.PCD_StopCrypto1();

}


/**
   Helper routine to dump a byte array as hex values to Serial.
*/
void printHex(byte *buffer, byte bufferSize) {
  for (byte i = 0; i < bufferSize; i++) {
    Serial.print(buffer[i] < 0x10 ? " 0" : " ");
    Serial.print(buffer[i], HEX);
  }
}
String parseToString(byte *buffer, byte bufferSize) {
  double s;
  String solucion;
  for (byte i = 0; i < bufferSize; i++) {
    solucion = solucion + String(buffer[i], HEX);
  }
  Serial.println(solucion);
  return solucion;
}

/**
   Helper routine to dump a byte array as dec values to Serial.
*/
void printDec(byte *buffer, byte bufferSize) {
  for (byte i = 0; i < bufferSize; i++) {
    Serial.print(buffer[i] < 0x10 ? " 0" : " ");
    Serial.print(buffer[i], DEC);
  }
}
