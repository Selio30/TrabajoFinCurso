package com.selio30.tfc.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "almacen")
public class Almacen {
    @PrimaryKey
    @ColumnInfo(name = "id_almacen")
    @NonNull
    private String id;
    @NonNull
    private String id_localizacion;
    @Ignore
    private List<Producto> productos;

    public Almacen() {
    }

    @Ignore
    public Almacen(@NonNull String id, @NonNull String id_localizacion) {
        this.id = id;
        this.id_localizacion = id_localizacion;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NonNull
    public String getId_localizacion() {
        return id_localizacion;
    }

    public void setId_localizacion(@NonNull String id_localizacion) {
        this.id_localizacion = id_localizacion;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return "Almacen{" +
                "id='" + id + '\'' +
                ", id_localizacion='" + id_localizacion + '\'' +
                '}';
    }
}
