package com.selio30.tfc.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "localizacion")
public class Localizacion {
    @PrimaryKey
    @ColumnInfo(name = "id_localizacion")
    @NonNull
    private String id;
    @ColumnInfo(name = "ciudad")
    private String ciudad;
    @ColumnInfo(name = "provincia")
    private String provincia;
    @Ignore
    private List<Producto> productos;

    public Localizacion(@NonNull String id, String ciudad, String provincia) {
        this.id = id;
        this.ciudad = ciudad;
        this.provincia = provincia;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return "Localizacion{" +
                "id='" + id + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", provincia='" + provincia + '\'' +
                '}';
    }
}
