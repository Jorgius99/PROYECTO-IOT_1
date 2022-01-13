package com.proyecto.loginultimodia;

public class Habitacion {

    private String numHab;
    private String DNI;
    private boolean ocupacion;
    private String Temperatura;
    private String Humedad;
    private String dniDoctor;

     public Habitacion (String num, String DNI, boolean ocupacion, String temperatura, String humedad){
         this.numHab =num;
         this.DNI = DNI;
         this.ocupacion = ocupacion;
         this.Temperatura =temperatura;
         this.Humedad =humedad;
     }

    public Habitacion (String num, String DNI, boolean ocupacion, String temperatura, String humedad, String dniDoctor){
        this.numHab =num;
        this.DNI = DNI;
        this.ocupacion = ocupacion;
        this.Temperatura =temperatura;
        this.dniDoctor = dniDoctor;
        this.Humedad =humedad;
    }

     public Habitacion(){
         this.numHab ="666";
         this.DNI ="74362511M";
         this.ocupacion=true;
         this.Humedad ="45%";
         this.dniDoctor = "32456215H";
         this.Temperatura ="30ºC";
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

    public String getTemperatura() {
        return Temperatura;
    }

    public String getHumedad() {
        return Humedad;
    }

    public String getDniDoctor() {
        return dniDoctor;
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

    public void setTemperatura(String temperatura) {
        this.Temperatura = temperatura;
    }

    public void setHumedad(String humedad) {
        this.Humedad = humedad;
    }

    public void setDniDoctor(String dniDoctor) {
        this.dniDoctor = dniDoctor;
    }
}
//-------------------------------------------------------------------------
