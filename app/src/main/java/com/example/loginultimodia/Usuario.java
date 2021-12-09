package com.example.loginultimodia;

import android.content.Intent;

public class Usuario {
    private String nombreApellido;
    private String tipoUser;
    private String DNI;
    private String DNIDOCTOR;
    private String NumHabitacion;

    public Usuario(String dni, String nombreApellido, String tipoUsuario,String dnidoctor, String numhabitacion  ) {
        this.DNI=dni;
        this.nombreApellido= nombreApellido;
        this.tipoUser= tipoUsuario;
        this.DNIDOCTOR= dnidoctor;
        this.NumHabitacion= numhabitacion;

    }

    //-------------------------GETTERS---------------------------------
    public String getNombreApellido() {
        return nombreApellido;
    }

    public String getDNI() {
        return DNI;
    }

    public String getTipoUser() {
        return tipoUser;
    }

    public String getDNIDOCTOR() {
        return DNIDOCTOR;
    }


    public String getNumHabitacion() {
        return NumHabitacion;
    }

    //-------------------------SETTERS---------------------------------
    public void setTipoUser(String tipoUser) {
        this.tipoUser = tipoUser;
    }

    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public void setDNIDOCTOR(String DNIDOCTOR) {
        this.DNIDOCTOR = DNIDOCTOR;
    }

    public void setNumHabitacion(String numHabitacion) {
        NumHabitacion = numHabitacion;
    }
}
