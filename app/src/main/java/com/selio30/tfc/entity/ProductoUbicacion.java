package com.selio30.tfc.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"id_producto", "id_ubicacion"})
public class ProductoUbicacion {
    @NonNull
    private String id_producto;
    @NonNull
    private String id_ubicacion;

    public ProductoUbicacion(@NonNull String id_producto, @NonNull String id_ubicacion) {
        this.id_producto = id_producto;
        this.id_ubicacion = id_ubicacion;
    }

    @NonNull
    public String getId_producto() {
        return id_producto;
    }

    public void setId_producto(@NonNull String id_producto) {
        this.id_producto = id_producto;
    }

    @NonNull
    public String getId_ubicacion() {
        return id_ubicacion;
    }

    public void setId_ubicacion(@NonNull String id_ubicacion) {
        this.id_ubicacion = id_ubicacion;
    }
}
