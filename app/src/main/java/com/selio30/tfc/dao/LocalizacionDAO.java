package com.selio30.tfc.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.selio30.tfc.entity.Localizacion;
import com.selio30.tfc.entity.ProductoHabituales;
import com.selio30.tfc.entity.relaciones.ProductoWithHabitual;

import java.util.List;

@Dao
public interface LocalizacionDAO {
    @Insert
    public void insert(Localizacion localizacion);

    @Insert
    public void insertAll(List<Localizacion> localizacionList);

    @Query("SELECT * FROM localizacion")
    List<Localizacion> getAll();

    @Query("SELECT * FROM localizacion WHERE id_Localizacion = :id limit 1")
    Localizacion getById(String id);

    @Transaction
    @Query("SELECT * FROM localizacion WHERE id_Localizacion = :id")
    List<ProductoWithHabitual> getProductoHabitual(String id);

    @Insert
    void insertProductoHabitual(ProductoHabituales productoHabituales);

    @Query("SELECT * FROM ProductoHabituales WHERE id_localizacion = :id_localizacion AND id_producto = :id_producto")
    ProductoHabituales getOneProductoHabitual(String id_localizacion, String id_producto);
}
