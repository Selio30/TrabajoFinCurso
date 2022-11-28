package com.selio30.tfc.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "ubicacion")
public class Ubicacion {
    @PrimaryKey
    @ColumnInfo(name = "id_ubicacion")
    @NonNull
    private String id;
    @ColumnInfo(name = "id_almacen")
    private String id_almacen;
    @ColumnInfo(name = "nombreUbicacion")
    private String name;
    @ColumnInfo(name = "orden")
    private String orden;
    @Ignore
    private List<Producto> productos;
    @Ignore
    private boolean isExpandable;

    @Ignore
    public Ubicacion(@NonNull String id, String id_almacen, String name, String orden, List<Producto> productos) {
        this.id = id;
        this.id_almacen = id_almacen;
        this.name = name;
        this.orden = orden;
        this.productos = productos;
        this.isExpandable = false;
    }

    public Ubicacion(@NonNull String id, String id_almacen, String name, String orden) {
        this.id = id;
        this.id_almacen = id_almacen;
        this.name = name;
        this.orden = orden;
        this.isExpandable = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_almacen() {
        return id_almacen;
    }

    public void setId_almacen(String id_almacen) {
        this.id_almacen = id_almacen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    @Ignore
    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public boolean isExpandable() {
        return isExpandable;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }

    @Override
    public String toString() {
        return "Ubicacion{" +
                "id='" + id + '\'' +
                ", id_almacen='" + id_almacen + '\'' +
                ", name='" + name + '\'' +
                ", orden='" + orden + '\'' +
                ", productos=" + productos +
                ", isExpandable=" + isExpandable +
                '}';
    }
}
