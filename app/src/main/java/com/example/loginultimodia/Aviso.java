package com.example.loginultimodia;

import java.util.Date;

public class Aviso {

    private String motivo;
    private Date fechaHora;
    private String prioridad;

    public Aviso (){
        this.motivo="Jorge es bobo";
        this.fechaHora= new Date();
        this.prioridad="";
    }
    public Aviso(String motiv, Date fecha, String prior){
        this.motivo=motiv;
        this.fechaHora=fecha;
        this.prioridad=prior;
    }

    //-------------------------GETTERS---------------------------------
    public String getMotivo() {
        return motivo;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    //-------------------------SETTERS---------------------------------
    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }


    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }
}
