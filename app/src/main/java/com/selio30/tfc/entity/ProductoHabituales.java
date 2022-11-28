package com.selio30.tfc.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;

@Entity(primaryKeys = {"id_producto", "id_localizacion"})
public class ProductoHabituales {
    @NonNull
    private String id_producto;
    @NonNull
    private String id_localizacion;

    public ProductoHabituales() {
    }

    @Ignore
    public ProductoHabituales(@NonNull String id_producto, @NonNull String id_localizacion) {
        this.id_producto = id_producto;
        this.id_localizacion = id_localizacion;
    }

    @NonNull
    public String getId_producto() {
        return id_producto;
    }

    public void setId_producto(@NonNull String id_producto) {
        this.id_producto = id_producto;
    }

    @NonNull
    public String getId_localizacion() {
        return id_localizacion;
    }

    public void setId_localizacion(@NonNull String id_localizacion) {
        this.id_localizacion = id_localizacion;
    }
}
