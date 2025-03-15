package com.example.tarea_25_grupo_3.Data;

public class Ubicacion {
    private int id;
    private String nombre;
    private double altitud;
    private double latitud;
    private double longitud;
    private String fechaCreacion;

    // Constructor vacío
    public Ubicacion() {}

    public Ubicacion(String nombre, double altitud, double latitud, double longitud) {
        this.nombre = nombre;
        this.altitud = altitud;
        this.latitud = latitud;
        this.longitud = longitud;
    }


    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getAltitud() {
        return altitud;
    }

    public void setAltitud(double altitud) {
        this.altitud = altitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
