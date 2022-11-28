package com.selio30.tfc.entity.relaciones;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.selio30.tfc.entity.Localizacion;
import com.selio30.tfc.entity.Producto;
import com.selio30.tfc.entity.ProductoHabituales;

import java.util.List;

public class ProductoWithHabitual {
    @Embedded
    public Localizacion localizacion;
    @Relation(parentColumn = "id_localizacion", entityColumn = "id_producto", associateBy = @Junction(ProductoHabituales.class))
    public List<Producto> productos;
}
