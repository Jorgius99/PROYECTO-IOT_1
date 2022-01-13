package com.proyecto.loginultimodia;

import java.sql.Date;
import java.util.Map;

public class Doctor {

    private String DNI;
    private String email;
    private String nombreApellidos;

    public Doctor (){ }
    public Doctor(String dni, String email, String nombreApellidos){
        this.DNI=dni;
        this.email=email;
        this.nombreApellidos=nombreApellidos;
    }


    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombreApellidos() {
        return nombreApellidos;
    }

    public void setNombreApellidos(String nombreApellidos) {
        this.nombreApellidos = nombreApellidos;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "DNI='" + DNI + '\'' +
                ", email='" + email + '\'' +
                ", nombreApellidos='" + nombreApellidos + '\'' +
                '}';
    }
}
