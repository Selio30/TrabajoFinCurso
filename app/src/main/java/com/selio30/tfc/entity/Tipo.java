package com.selio30.tfc.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tipo")
public class Tipo {
    @PrimaryKey
    @ColumnInfo(name = "id_Tipo")
    @NonNull
    private String id;
    @ColumnInfo(name = "tipos")
    private String tipos;

    public Tipo(@NonNull String id, String tipos) {
        this.id = id;
        this.tipos = tipos;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getTipos() {
        return tipos;
    }

    public void setTipos(String tipos) {
        this.tipos = tipos;
    }

    @Override
    public String toString() {
        return "Tipo{" +
                "id='" + id + '\'' +
                ", tipos='" + tipos + '\'' +
                '}';
    }
}
