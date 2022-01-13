package com.proyecto.loginultimodia;

import java.util.Date;

public class ObjetoDosis {

    private String Medicamento;
    private String Cantidad;
    private Date HoraFecha;
    private String dni;
    private String frecuencia;
    private long Milis;


    //-------------------------CONSTRUCTOR-----------------------------


    public ObjetoDosis(String med, String canti, String dni, String freq, Date fecha, long milsec){
            this.Medicamento = med;
            this.Cantidad = canti;
            this.HoraFecha= fecha;
            this.frecuencia = freq;
            this.dni = dni;
            this.Milis = milsec;

    }

    public ObjetoDosis(){
        this.Medicamento = "Ibus";
        this.Cantidad= "7";
        this.HoraFecha = new Date();
        this.dni="24567753N";
        this.frecuencia = "8";
        this.Milis = 1641835675;


    }
    //-------------------------GETTERS---------------------------------

    public String getMedicamento() {
        return Medicamento;
    }

    public String getCantidad() {
        return Cantidad;
    }



    public Date getHoraFecha() {
        return HoraFecha;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public String getDni() {
        return dni;
    }

    public long getMilis() {
        return Milis;
    }
    //-------------------------SETTERS---------------------------------


    public void setDni(String dni) {
        this.dni = dni;
    }


    public void setMedicamento(String medicamento) {
        Medicamento = medicamento;
    }

    public void setCantidad(String Cantidad) {
        Cantidad = Cantidad;
    }


    public void setHoraFecha(Date horaFecha) {
        HoraFecha = horaFecha;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public void setMilis(long milis) {
        Milis = milis;
    }
}
