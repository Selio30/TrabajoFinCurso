package com.selio30.tfc.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "inventario")
public class Inventario {
    @PrimaryKey
    @ColumnInfo(name = "id_inventario")
    @NonNull
    private int id;
    @ColumnInfo(name = "fecha")
    private String fecha;
    @ColumnInfo(name = "id_localizacion")
    private String id_localizacion;

    public Inventario() {
    }

    @Ignore
    public Inventario(String fecha, String id_localizacion) {
        this.fecha = fecha;
        this.id_localizacion = id_localizacion;
    }

    @Ignore
    public Inventario(@NonNull int id, String fecha, String id_localizacion) {
        this.id = id;
        this.fecha = fecha;
        this.id_localizacion = id_localizacion;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getId_localizacion() {
        return id_localizacion;
    }

    public void setId_localizacion(String id_localizacion) {
        this.id_localizacion = id_localizacion;
    }

    @Override
    public String toString() {
        return "Inventario{" +
                "id='" + id + '\'' +
                ", fecha=" + fecha +
                ", id_localizacion='" + id_localizacion + '\'' +
                '}';
    }
}
