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

    @Query("SELECT p.id_Producto, p.nameProducto FROM ProductoUbicacion u INNER JOIN productos p ON u.id_producto = p.id_Producto WHERE u.id_Ubicacion = :id")
    List<Producto> getProductosUbicacionSelected(String id);

    @Query("SELECT p.id_producto, p.nameProducto, h.ID_producto FROM ProductoUbicacion u INNER JOIN productos p ON u.id_producto = p.id_producto INNER JOIN productohabituales h ON h.id_producto = p.id_producto WHERE u.id_ubicacion = :id AND h.id_localizacion = :id_localizacion")
    List<Producto> getProductgoUbicacionHabitual(String id, int id_localizacion);

    @Query("SELECT u.id_ubicacion, u.id_almacen, u.nombreUbicacion FROM ubicacion u INNER JOIN almacen a ON a.id_almacen = u.id_almacen WHERE a.id_localizacion = :id_localizacion")
    List<Ubicacion> getUbicaionAlmacen(int id_localizacion);

    @Transaction
    @Query("SELECT * FROM ubicacion WHERE id_Ubicacion = :id")
    List<ProductoWithUbicacion> getProductoUbicacion(String id);

    @Insert
    void insertProductoUbicacion(ProductoUbicacion productoUbicacion);

    @Query("SELECT * FROM productoubicacion WHERE id_ubicacion = :id_ubicacion AND id_producto = :id_producto")
    ProductoUbicacion getOneProductoUbicacion(String id_ubicacion, String id_producto);
}
