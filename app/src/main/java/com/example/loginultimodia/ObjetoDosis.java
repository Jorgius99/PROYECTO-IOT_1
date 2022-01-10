package com.example.loginultimodia;

import java.util.Date;

public class ObjetoDosis {

    private String Medicamento;
    private String Cantidad;
    private String DNI;
    private Date HoraFecha;
<<<<<<< HEAD
    private int Frecuencia;
    private String dni;
=======
    private String Frecuencia;
>>>>>>> develop


    //-------------------------CONSTRUCTOR-----------------------------

<<<<<<< HEAD:app/src/main/java/com/example/loginultimodia/objetoDosis.java
<<<<<<< HEAD
    public objetoDosis(String med, String num, int freq, String dni){
=======
    public objetoDosis(String med, String canti, String dni, String freq, Date fecha){
>>>>>>> develop
=======
    public ObjetoDosis(String med, String canti, String dni, String freq, Date fecha){
>>>>>>> develop:app/src/main/java/com/example/loginultimodia/ObjetoDosis.java
            this.Medicamento = med;
            this.Cantidad = canti;
            this.DNI = dni;
            this.HoraFecha= fecha;
            this.Frecuencia = freq;
            this.dni = dni;
    }

    public ObjetoDosis(){
        this.Medicamento = "Ibus";
        this.Cantidad= "7";
        this.DNI = "24567753N";
        this.HoraFecha = new Date();
<<<<<<< HEAD
        this.Frecuencia = 8;
        this.dni="24567753N";
=======
        this.Frecuencia = "8";

>>>>>>> develop
    }
    //-------------------------GETTERS---------------------------------

    public String getMedicamento() {
        return Medicamento;
    }

    public String getCantidad() {
        return Cantidad;
    }

    public String getDNI() {
        return DNI;
    }

    public Date getHoraFecha() {
        return HoraFecha;
    }

    public String getFrecuencia() {
        return Frecuencia;
    }

<<<<<<< HEAD
    public String getDni() {
        return dni;
    }

//-------------------------SETTERS---------------------------------


    public void setDni(String dni) {
        this.dni = dni;
    }
=======
//-------------------------SETTERS---------------------------------
>>>>>>> develop

    public void setMedicamento(String medicamento) {
        Medicamento = medicamento;
    }

    public void setCantidad(String Cantidad) {
        Cantidad = Cantidad;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public void setHoraFecha(Date horaFecha) {
        HoraFecha = horaFecha;
    }

    public void setFrecuencia(String frecuencia) {
        Frecuencia = frecuencia;
    }
}
