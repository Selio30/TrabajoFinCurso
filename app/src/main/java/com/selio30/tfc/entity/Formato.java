package com.selio30.tfc.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "formato")
public class Formato {
    @PrimaryKey
    @ColumnInfo(name = "id_Formato")
    @NonNull
    private String id;
    @ColumnInfo(name = "formato")
    private String formato;
    @ColumnInfo(name = "volumen")
    private String volumen;

    public Formato() {
    }

    public Formato(@NonNull String id, String formato, String volumen) {
        this.id = id;
        this.formato = formato;
        this.volumen = volumen;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getVolumen() {
        return volumen;
    }

    public void setVolumen(String volumen) {
        this.volumen = volumen;
    }

    @Override
    public String toString() {
        return "Formato{" +
                "id='" + id + '\'' +
                ", formato='" + formato + '\'' +
                ", volumen='" + volumen + '\'' +
                '}';
    }
}
