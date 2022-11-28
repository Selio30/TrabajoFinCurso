package com.selio30.tfc.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;

@Entity(primaryKeys = {"id_producto", "id_almacen"})
public class ProductoAlmacen {
    @NonNull
    private String id_producto;
    @NonNull
    private String id_almacen;

    public ProductoAlmacen() {
    }

    @Ignore
    public ProductoAlmacen(@NonNull String id_producto, @NonNull String id_almacen) {
        this.id_producto = id_producto;
        this.id_almacen = id_almacen;
    }

    @NonNull
    public String getId_producto() {
        return id_producto;
    }

    public void setId_producto(@NonNull String id_producto) {
        this.id_producto = id_producto;
    }

    @NonNull
    public String getId_almacen() {
        return id_almacen;
    }

    public void setId_almacen(@NonNull String id_almacen) {
        this.id_almacen = id_almacen;
    }
}
