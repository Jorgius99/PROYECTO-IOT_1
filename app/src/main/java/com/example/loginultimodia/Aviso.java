package com.example.loginultimodia;

public class Aviso {

    private String motivo;
    private String fechaHora;
    private String prioridad;

    public Aviso(String motiv, String fecha, String prior){
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

    public String getFechaHora() {
        return fechaHora;
    }

    //-------------------------SETTERS---------------------------------
    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }


    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }
}
