package com.example.loginultimodia;

import java.util.Date;

public class Aviso {

    private String motivo;
    private String dni;
    private String habitacion;
    private Date fechaHora;
    private String prioridad;
<<<<<<< HEAD
    private String estado;
=======
>>>>>>> weonao

    public Aviso (){
        this.motivo="Jorge es bobo";
        this.fechaHora= new Date();
        this.prioridad="";
    }
<<<<<<< HEAD
    public Aviso(String motiv, Date fecha, String prior, String dni, String habitacion, String estado){
=======
    public Aviso(String motiv, Date fecha, String prior, String dni, String habitacion){
>>>>>>> weonao
        this.motivo=motiv;
        this.fechaHora=fecha;
        this.prioridad=prior;
        this.dni=dni;
        this.habitacion = habitacion;
<<<<<<< HEAD
        this.estado=estado;
=======
>>>>>>> weonao

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
