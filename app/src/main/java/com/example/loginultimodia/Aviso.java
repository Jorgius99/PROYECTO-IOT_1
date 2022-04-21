package com.example.loginultimodia;

import java.util.Date;

public class Aviso {

    private String motivo;
    private String dni;
    private String habitacion;
    private Date fechaHora;
    private String prioridad;
    private String estado;

    public Aviso (){
        this.motivo="Jorge es bobo";
        this.fechaHora= new Date();
        this.prioridad="";
    }
    public Aviso(String motiv, Date fecha, String prior, String dni, String habitacion, String estado){
        this.motivo=motiv;
        this.fechaHora=fecha;
        this.prioridad=prior;
        this.dni=dni;
        this.habitacion = habitacion;
        this.estado=estado;

    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public String getDni() {
        return dni;
    }

    public String getHabitacion() {
        return habitacion;
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

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setHabitacion(String habitacion) {
        this.habitacion = habitacion;
    }
}