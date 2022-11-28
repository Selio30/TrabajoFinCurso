package com.selio30.tfc.entity.relaciones;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.selio30.tfc.entity.Producto;
import com.selio30.tfc.entity.ProductoUbicacion;
import com.selio30.tfc.entity.Ubicacion;

import java.util.List;

public class ProductoWithUbicacion {
    @Embedded
    public Ubicacion ubicacion;
    @Relation(parentColumn = "id_ubicacion", entityColumn = "id_producto", associateBy = @Junction(ProductoUbicacion.class))
    public List<Producto> productos;
}
