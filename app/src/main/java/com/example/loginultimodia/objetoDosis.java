package com.example.loginultimodia;

import java.util.Date;

public class objetoDosis {

    private String Medicamento;
    private String NumPastillas;
    private Date HoraFecha;
    private int Frecuencia;


    //-------------------------CONSTRUCTOR-----------------------------

    public objetoDosis(String med, String num, int freq){
            this.Medicamento = med;
            this.NumPastillas = num;
            this.HoraFecha = new Date();
            this.Frecuencia = freq;
    }

    public objetoDosis(){
        this.Medicamento = "Ibus";
        this.NumPastillas= "7";
        this.HoraFecha = new Date();
        this.Frecuencia = 8;

    }
    //-------------------------GETTERS---------------------------------

    public String getMedicamento() {
        return Medicamento;
    }

    public String getNumPastillas() {
        return NumPastillas;
    }

    public Date getHoraFecha() {
        return HoraFecha;
    }

    public int getFrecuencia() {
        return Frecuencia;
    }


    //-------------------------SETTERS---------------------------------

    public void setMedicamento(String medicamento) {
        Medicamento = medicamento;
    }

    public void setNumPastillas(String numPastillas) {
        NumPastillas = numPastillas;
    }

    public void setHoraFecha(Date horaFecha) {
        HoraFecha = horaFecha;
    }

    public void setFrecuencia(int frecuencia) {
        Frecuencia = frecuencia;
    }


}
