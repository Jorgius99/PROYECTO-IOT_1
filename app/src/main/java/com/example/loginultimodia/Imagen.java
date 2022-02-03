package com.example.loginultimodia;

import java.util.Date;


public class Imagen {
    private String titulo;
    private String url;
    private long tiempo;


public Imagen(String titulo, String url) {
    this.titulo = titulo;
    this.url = url;
    tiempo = new Date().getTime();

      }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getTiempo() {
        return tiempo;
    }

    public void setTiempo(long tiempo) {
        this.tiempo = tiempo;
    }

    public Imagen() {
    }
}


