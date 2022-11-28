package com.selio30.tfc.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.selio30.tfc.entity.Inventario;
import com.selio30.tfc.entity.InventarioProducto;
import com.selio30.tfc.entity.relaciones.InventarioWithProducto;

import java.util.List;

@Dao
public interface InventarioDAO {
    @Insert
    public void insert(Inventario inventario);

    @Insert
    public void insertAll(List<Inventario> inventarioList);

    @Query("SELECT * FROM inventario")
    List<Inventario> getAll();

    @Query("SELECT * FROM inventario WHERE id_Inventario = :id limit 1")
    Inventario getById(String id);

    @Transaction
    @Query("SELECT * FROM inventario WHERE id_Inventario = :id")
    List<InventarioWithProducto> getInventarioProducto(String id);

    @Insert
    void insertInventarioProducto(InventarioProducto inventarioProducto);

    @Query("SELECT * FROM inventarioProducto WHERE id_inventario = :id_inventario AND id_producto = :id_producto")
    InventarioProducto getOneInventarioProducto(String id_inventario, String id_producto);

    @Query("SELECT id_inventario FROM inventario ORDER BY id_inventario DESC LIMIT 1")
    Integer getLastId();
}
