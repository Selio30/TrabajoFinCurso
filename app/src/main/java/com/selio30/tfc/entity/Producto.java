package com.selio30.tfc.entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "productos")
public class Producto {
    @PrimaryKey
    @ColumnInfo(name = "id_producto")
    @NonNull
    private String id;
    @ColumnInfo(name = "nameProducto")
    private String name;
    @ColumnInfo(name = "id_Tipo")
    private String id_tipo;
    @ColumnInfo(name = "id_Formato")
    private String id_formato;
    @Ignore
    private String stock;

    public Producto(@NonNull String id, String name, String id_tipo, String id_formato) {
        this.id = id;
        this.name = name;
        this.id_tipo = id_tipo;
        this.id_formato = id_formato;
        this.stock = "0";
    }

    @Ignore
    public Producto(@NonNull String id, String name, String id_tipo, String id_formato, String stock) {
        this.id = id;
        this.name = name;
        this.id_tipo = id_tipo;
        this.id_formato = id_formato;
        this.stock = stock;
    }

    @Ignore
    public Producto(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(String id_tipo) {
        this.id_tipo = id_tipo;
    }

    public String getId_formato() {
        return id_formato;
    }

    public void setId_formato(String id_formato) {
        this.id_formato = id_formato;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", id_tipo='" + id_tipo + '\'' +
                ", id_formato='" + id_formato + '\'' +
                ", stock='" + stock + '\'' +
                '}';
    }
}
