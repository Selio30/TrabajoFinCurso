package com.selio30.tfc.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.selio30.tfc.entity.Producto;
import com.selio30.tfc.entity.ProductoUbicacion;
import com.selio30.tfc.entity.Ubicacion;
import com.selio30.tfc.entity.relaciones.ProductoWithUbicacion;

import java.util.List;

@Dao
public interface UbicacionDAO {
    @Insert
    public void insert(Ubicacion ubicacion);

    @Query("SELECT * FROM ubicacion")
    List<Ubicacion> getAll();

    @Query("SELECT * FROM ubicacion WHERE id_Ubicacion = :id")
    Ubicacion getById(String id);

    @Query("SELECT p.id_Producto, p.nameProducto FROM ProductoUbicacion u INNER JOIN productos p ON u.id_Ubicacion = p.id_Producto WHERE u.id_Ubicacion = :id")
    List<Producto> getProductosUbicacionSelected(String id);

    @Transaction
    @Query("SELECT * FROM ubicacion WHERE id_Ubicacion = :id")
    List<ProductoWithUbicacion> getProductoUbicacion(String id);

    @Insert
    void insertProductoUbicacion(ProductoUbicacion productoUbicacion);

    @Query("SELECT * FROM productoubicacion WHERE id_ubicacion = :id_ubicacion AND id_producto = :id_producto")
    ProductoUbicacion getOneProductoUbicacion(String id_ubicacion, String id_producto);
}
