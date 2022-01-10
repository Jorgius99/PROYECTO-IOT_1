
int led=2;
int detector=25;

void iniciarInfrarrojos(){
  pinMode(detector,INPUT);
    pinMode(led,OUTPUT);
}


bool hayAlgunaPastilla() {
    int value = 0;
    value = digitalRead(detector);

    if (value == LOW) {
        return true;

    }
    return false;
}
void encenderLuz(){
    digitalWrite(led,HIGH);
}
void apagarLuz(){
    digitalWrite(led,LOW);
}
