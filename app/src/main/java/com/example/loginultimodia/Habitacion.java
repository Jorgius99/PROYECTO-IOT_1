package com.example.loginultimodia;

public class Habitacion {

    private String numHabitacion;
    private String paciente;
    private String doctor;


     public Habitacion (String num, String paciente, String doctor){
         this.numHabitacion=num;
         this.paciente=paciente;
         this.doctor=doctor;
     }

    //-------------------------GETTERS---------------------------------
    public String getNumHabitacion() {
        return numHabitacion;
    }

    public String getPaciente() {
        return paciente;
    }

    public String getDoctor() {
        return doctor;
    }

    //-------------------------SETTERS---------------------------------

    public void setNumHabitacion(String numHabitacion) {
        this.numHabitacion = numHabitacion;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }
}
