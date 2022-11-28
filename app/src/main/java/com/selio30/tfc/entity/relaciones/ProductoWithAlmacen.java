package com.selio30.tfc.entity.relaciones;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.selio30.tfc.entity.Almacen;
import com.selio30.tfc.entity.Producto;
import com.selio30.tfc.entity.ProductoAlmacen;

import java.util.List;

public class ProductoWithAlmacen {
    @Embedded
    public Almacen almacen;
    @Relation(parentColumn = "id_almacen", entityColumn = "id_producto", associateBy = @Junction(ProductoAlmacen.class))
    public List<Producto> productos;
}
