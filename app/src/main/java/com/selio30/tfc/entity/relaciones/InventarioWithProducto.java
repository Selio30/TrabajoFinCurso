package com.selio30.tfc.entity.relaciones;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.selio30.tfc.entity.Inventario;
import com.selio30.tfc.entity.InventarioProducto;
import com.selio30.tfc.entity.Producto;

import java.util.List;

public class InventarioWithProducto {
    @Embedded
    public Inventario inventario;
    @Relation(parentColumn = "id_inventario", entityColumn = "id_producto", associateBy = @Junction(InventarioProducto.class))
    public List<Producto> productos;
}
