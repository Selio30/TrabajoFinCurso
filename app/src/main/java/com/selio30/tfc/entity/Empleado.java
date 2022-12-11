package com.selio30.tfc.entity;

import androidx.room.Ignore;

public class Empleado {
    private String nombre;
    private String apellidos;
    private int id_localizacion;
    private String nombreRol;

    public Empleado() {
    }

   @Ignore
    public Empleado(String nombre, String apellidos, int id_localizacion, String nombreRol) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.id_localizacion = id_localizacion;
        this.nombreRol = nombreRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getId_localizacion() {
        return id_localizacion;
    }

    public void setId_localizacion(int id_localizacion) {
        this.id_localizacion = id_localizacion;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", id_localizacion=" + id_localizacion +
                ", nombreRol='" + nombreRol + '\'' +
                '}';
    }
}
