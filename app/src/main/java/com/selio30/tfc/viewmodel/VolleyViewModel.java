package com.selio30.tfc.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
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

import java.util.ArrayList;
import java.util.List;

public class VolleyViewModel extends AndroidViewModel {
    private RequestQueue requestQueue;
    private MutableLiveData<List<Almacen>> almacens;
    private MutableLiveData<List<Formato>> formatos;
    private MutableLiveData<List<Inventario>> inventarios;
    private MutableLiveData<List<InventarioProducto>> inventarioproductos;
    private MutableLiveData<List<Localizacion>> localizacions;
    private MutableLiveData<List<Producto>> productos;
    private MutableLiveData<List<ProductoAlmacen>> productoalmacens;
    private MutableLiveData<List<ProductoHabituales>> productohabituales;
    private MutableLiveData<List<ProductoUbicacion>> productoubicacions;
    private MutableLiveData<List<Tipo>> tipos;
    private MutableLiveData<List<Ubicacion>> ubicacions;

    public VolleyViewModel(@NonNull Application application) {
        super(application);
    }

    public RequestQueue getRequestQueue(Context context) {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request, Context context) {
        getRequestQueue(context).add(request);
    }

    public MutableLiveData<List<Almacen>> getAlmacens() {
        if (almacens == null) {
            almacens = new MutableLiveData<>();
            almacens.setValue(new ArrayList<>());
        }
        return almacens;
    }

    public void setAlmacens(MutableLiveData<List<Almacen>> almacens) {
        this.almacens = almacens;
    }

    public MutableLiveData<List<Formato>> getFormatos() {
        if (formatos == null) {
            formatos = new MutableLiveData<>();
            formatos.setValue(new ArrayList<>());
        }
        return formatos;
    }

    public void setFormatos(MutableLiveData<List<Formato>> formatos) {
        this.formatos = formatos;
    }

    public MutableLiveData<List<Inventario>> getInventarios() {
        if (inventarios == null) {
            inventarios = new MutableLiveData<>();
            inventarios.setValue(new ArrayList<>());
        }
        return inventarios;
    }

    public void setInventarios(MutableLiveData<List<Inventario>> inventarios) {
        this.inventarios = inventarios;
    }

    public MutableLiveData<List<InventarioProducto>> getInventarioproductos() {
        if (inventarioproductos == null) {
            inventarioproductos = new MutableLiveData<>();
            inventarioproductos.setValue(new ArrayList<>());
        }
        return inventarioproductos;
    }

    public void setInventarioproductos(MutableLiveData<List<InventarioProducto>> inventarioproductos) {
        this.inventarioproductos = inventarioproductos;
    }

    public MutableLiveData<List<Localizacion>> getLocalizacions() {
        if (localizacions == null) {
            localizacions = new MutableLiveData<>();
            localizacions.setValue(new ArrayList<>());
        }
        return localizacions;
    }

    public void setLocalizacions(MutableLiveData<List<Localizacion>> localizacions) {
        this.localizacions = localizacions;
    }

    public MutableLiveData<List<Producto>> getProductos() {
        if (productos == null) {
            productos = new MutableLiveData<>();
            productos.setValue(new ArrayList<>());
        }
        return productos;
    }

    public void setProductos(MutableLiveData<List<Producto>> productos) {
        this.productos = productos;
    }

    public MutableLiveData<List<ProductoAlmacen>> getProductoalmacens() {
        if (productoalmacens == null) {
            productoalmacens = new MutableLiveData<>();
            productoalmacens.setValue(new ArrayList<>());
        }
        return productoalmacens;
    }

    public void setProductoalmacens(MutableLiveData<List<ProductoAlmacen>> productoalmacens) {
        this.productoalmacens = productoalmacens;
    }

    public MutableLiveData<List<ProductoHabituales>> getProductohabituales() {
        if (productohabituales == null) {
            productohabituales = new MutableLiveData<>();
            productohabituales.setValue(new ArrayList<>());
        }
        return productohabituales;
    }

    public void setProductohabituales(MutableLiveData<List<ProductoHabituales>> productohabituales) {
        this.productohabituales = productohabituales;
    }

    public MutableLiveData<List<ProductoUbicacion>> getProductoubicacions() {
        if (productoubicacions == null) {
            productoubicacions = new MutableLiveData<>();
            productoubicacions.setValue(new ArrayList<>());
        }
        return productoubicacions;
    }

    public void setProductoubicacions(MutableLiveData<List<ProductoUbicacion>> productoubicacions) {
        this.productoubicacions = productoubicacions;
    }

    public MutableLiveData<List<Tipo>> getTipos() {
        if (tipos == null) {
            tipos = new MutableLiveData<>();
            tipos.setValue(new ArrayList<>());
        }
        return tipos;
    }

    public void setTipos(MutableLiveData<List<Tipo>> tipos) {
        this.tipos = tipos;
    }

    public MutableLiveData<List<Ubicacion>> getUbicacions() {
        if (ubicacions == null) {
            ubicacions = new MutableLiveData<>();
            ubicacions.setValue(new ArrayList<>());
        }
        return ubicacions;
    }

    public void setUbicacions(MutableLiveData<List<Ubicacion>> ubicacions) {
        this.ubicacions = ubicacions;
    }
}
