package com.example.loginultimodia;

import java.util.Date;

public class ObjetoDosis {

    private String Medicamento;
    private String Cantidad;
    private String DNI;
    private Date HoraFecha;
    private String Frecuencia;


    //-------------------------CONSTRUCTOR-----------------------------

    public ObjetoDosis(String med, String canti, String dni, String freq, Date fecha){
            this.Medicamento = med;
            this.Cantidad = canti;
            this.DNI = dni;
            this.HoraFecha= fecha;
            this.Frecuencia = freq;
    }

    public ObjetoDosis(){
        this.Medicamento = "Ibus";
        this.Cantidad= "7";
        this.DNI = "24567753N";
        this.HoraFecha = new Date();
        this.Frecuencia = "8";

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

//-------------------------SETTERS---------------------------------

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
