package com.selio30.tfc.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.selio30.tfc.entity.Almacen;
import com.selio30.tfc.entity.ProductoAlmacen;
import com.selio30.tfc.entity.relaciones.ProductoWithAlmacen;

import java.util.List;

@Dao
public interface AlmacenDAO {
    @Insert
    public void insert(Almacen almacen);

    @Insert
    public void insertAll(List<Almacen> almacenList);

    @Query("SELECT * FROM almacen")
    List<Almacen> getAll();

    @Query("SELECT * FROM almacen WHERE id_Almacen = :id limit 1")
    Almacen getById(String id);

    @Transaction
    @Query("SELECT * FROM almacen WHERE id_Almacen = :id")
    List<ProductoWithAlmacen> getProductoAlmacen(String id);

    @Insert
    void insertProductoAlmacen(ProductoAlmacen productoAlmacen);

    @Query("SELECT * FROM ProductoAlmacen WHERE id_almacen = :id_almacen AND id_producto = :id_producto")
    ProductoAlmacen getOneProductoAlmacen(String id_almacen, String id_producto);
}
