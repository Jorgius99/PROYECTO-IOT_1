package com.proyecto.loginultimodia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 5 b) COLECCIONES
public class Registro {

    private String medicamento;

    public Registro(String medicamento) {
        this.medicamento = medicamento;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }
//metodo  que recibe tres arrays como los anteriores y devuelve en un diccionario tipo Map

    static Map<Integer,Registro> creaMapa(String[] medicamento) {
        Map<Integer,Registro> mapa = new HashMap<>();
        for (int n = 0; n<medicamento.length; n++) {
            mapa.put(n+1,new Registro(medicamento[n]));
        }
        return mapa;
    }

    // 5 c) COLECCIONES
    List<Registro> creaLista(String[] medicamento) {
        List<Registro> lista = new ArrayList<>();
        for (int n = 0; n<medicamento.length; n++) {
            lista.add(new Registro(medicamento[n]));
        }
        return lista;
    }
}