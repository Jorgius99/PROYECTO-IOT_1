package com.example.loginultimodia;

public class Usuario {
    private String nombreApellido;
    private String tipoUser;
    private String DNI;

    public Usuario(String dni, String nombreApellido, String tipoUsuario ) {
        this.DNI=dni;
        this.nombreApellido=nombreApellido;
        this.tipoUser=tipoUsuario;
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
}
