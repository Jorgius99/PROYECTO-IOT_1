

const char * ssid = "@_daniiceba_";
const char * password = "memide25cm";
AsyncUDP udp;
char texto[200];
boolean rec = 0;


void iniciarWifiRecibo(){
  WiFi.mode(WIFI_STA);
  WiFi.begin(ssid, password);
  if (WiFi.waitForConnectResult() != WL_CONNECTED) {
    Serial.println("WiFi Failed");
    while (WiFi.waitForConnectResult() != WL_CONNECTED) {
          Serial.println("WiFi Failed");

      delay(1000);
    }
  }
  if (udp.listen(5678)) {
    Serial.print("UDP Listening on IP: ");
    Serial.println(WiFi.localIP());
    udp.onPacket([](AsyncUDPPacket packet) {
      int i = 200;
      while (i--) {
        *(texto + i) = *(packet.data() + i);
      }
      rec = 1; //recepcion de un mensaje.
    });
  }
}
