package com.selio30.tfc.db;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.selio30.tfc.dao.AlmacenDAO;
import com.selio30.tfc.dao.FormatoDAO;
import com.selio30.tfc.dao.InventarioDAO;
import com.selio30.tfc.dao.LocalizacionDAO;
import com.selio30.tfc.dao.ProductoDAO;
import com.selio30.tfc.dao.TipoDAO;
import com.selio30.tfc.dao.UbicacionDAO;
import com.selio30.tfc.entity.Almacen;
import com.selio30.tfc.entity.Formato;
import com.selio30.tfc.entity.Inventario;
import com.selio30.tfc.entity.InventarioProducto;
import com.selio30.tfc.entity.Localizacion;
import com.selio30.tfc.entity.Producto;
import com.selio30.tfc.entity.ProductoAlmacen;
import com.selio30.tfc.entity.ProductoHabituales;
import com.selio30.tfc.entity.ProductoUbicacion;
import com.selio30.tfc.entity.Tipo;
import com.selio30.tfc.entity.Ubicacion;


@androidx.room.Database(entities =
        {
                Almacen.class,
                Formato.class,
                Inventario.class,
                InventarioProducto.class,
                Localizacion.class,
                Producto.class,
                ProductoAlmacen.class,
                ProductoHabituales.class,
                ProductoUbicacion.class,
                Tipo.class,
                Ubicacion.class
        },
        version = 1,
        exportSchema = false)
public abstract class Database extends RoomDatabase {
    public abstract AlmacenDAO almacenDAO();
    public abstract FormatoDAO formatoDAO();
    public abstract InventarioDAO inventarioDAO();
    public abstract LocalizacionDAO localizacionDAO();
    public abstract ProductoDAO productoDAO();
    public abstract TipoDAO tipoDAO();
    public abstract UbicacionDAO ubicacionDAO();

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
