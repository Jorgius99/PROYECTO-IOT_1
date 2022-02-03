
AsyncUDP udp;

const char * ssid = "@_daniiceba_";
const char * password = "danimola";

void iniciarWifiEnvio(){
   WiFi.mode(WIFI_STA);
    WiFi.begin(ssid, password);
    if (WiFi.waitForConnectResult() != WL_CONNECTED) {
        Serial.println("WiFi Failed");
        while (WiFi.waitForConnectResult() != WL_CONNECTED) {
            delay(1000);
        }
    }
    if (udp.listen(5678)) {
        Serial.print("UDP Listening on IP: ");
        Serial.println(WiFi.localIP());
        udp.onPacket([](AsyncUDPPacket packet) {
            Serial.write(packet.data(), packet.length());
            Serial.println();
        });
    }
}


void enviarTexto(String mensaje,int puerto){
    char texto[200];
    mensaje.toCharArray(texto,200);
    
    udp.broadcastTo(texto, puerto);
}
