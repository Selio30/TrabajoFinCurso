package com.selio30.tfc.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.selio30.tfc.entity.Producto;

import java.util.List;

@Dao
public interface ProductoDAO {
    @Insert
    public void insert(Producto producto);

    @Insert
    public void insertAll(List<Producto> productoList);

    @Query("SELECT * FROM productos")
    List<Producto> getAll();

    @Query("SELECT * FROM productos WHERE nameProducto = :name")
    List<Producto> getByName(String name);

    @Query("SELECT * FROM productos WHERE id_Producto = :id limit 1")
    Producto getById(String id);
}