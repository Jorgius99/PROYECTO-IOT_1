package com.example.loginultimodia;

public class Habitacion {

    private String numHab;
    private String DNI;
    private boolean ocupacion;
    private float temperatura;
    private float humedad;

     public Habitacion (String num, String DNI, boolean ocupacion){
         this.numHab =num;
         this.DNI = DNI;
         this.ocupacion = ocupacion;

     }

     public Habitacion(){
         this.numHab ="666";
         this.DNI ="74362511M";
         this.ocupacion=true;
     }
    //-------------------------GETTERS---------------------------------
    public String getNumHab() {
        return numHab;
    }

    public String getDNI() {
        return DNI;
    }

    public boolean getOcupacion() {
        return ocupacion;
    }

    //-------------------------SETTERS---------------------------------

    public void setNumHab(String numHab) {
        this.numHab = numHab;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public void setOcupacion(boolean ocupacion) {
        this.ocupacion = ocupacion;
    }
}
