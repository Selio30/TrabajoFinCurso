package com.selio30.tfc.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

import java.util.List;

@Entity(tableName = "inventarioProducto", primaryKeys = {"id_producto", "id_inventario"})
public class InventarioProducto {
    @NonNull
    private String id_producto;
    @NonNull
    private int id_inventario;
    @ColumnInfo(name = "stock")
    private String stock;
    @ColumnInfo(name = "habitual")
    private String habitual;
    @Ignore
    private List<Producto> productos;

    public InventarioProducto() {
    }

    @Ignore
    public InventarioProducto(@NonNull String id_producto, @NonNull int id_inventario, String stock, String habitual) {
        this.id_producto = id_producto;
        this.id_inventario = id_inventario;
        this.stock = stock;
        this.habitual = habitual;
    }

    @NonNull
    public String getId_producto() {
        return id_producto;
    }

    public void setId_producto(@NonNull String id_producto) {
        this.id_producto = id_producto;
    }

    @NonNull
    public int getId_inventario() {
        return id_inventario;
    }

    public void setId_inventario(@NonNull int id_inventario) {
        this.id_inventario = id_inventario;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getHabitual() {
        return habitual;
    }

    public void setHabitual(String habitual) {
        this.habitual = habitual;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return "InventarioProducto{" +
                "id_producto='" + id_producto + '\'' +
                ", id_inventario='" + id_inventario + '\'' +
                ", stock='" + stock + '\'' +
                ", habitual='" + habitual + '\'' +
                ", productos=" + productos +
                '}';
    }
}
