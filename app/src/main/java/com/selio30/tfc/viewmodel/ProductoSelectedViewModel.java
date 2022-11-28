package com.selio30.tfc.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.selio30.tfc.entity.Producto;
import com.selio30.tfc.entity.Ubicacion;

import java.util.ArrayList;
import java.util.List;

public class ProductoSelectedViewModel extends ViewModel {
    private MutableLiveData<Ubicacion> ubicacion;
    private MutableLiveData<List<Producto>> productoList;

    public MutableLiveData<Ubicacion> getUbicacion() {
        if (ubicacion == null) {
            ubicacion = new MutableLiveData<>();
        }
        return ubicacion;
    }

    public void setUbicacion(MutableLiveData<Ubicacion> ubicacion) {
        this.ubicacion = ubicacion;
    }

    public MutableLiveData<List<Producto>> getProductoList() {
        if (productoList == null) {
            productoList = new MutableLiveData<>();
            productoList.setValue(new ArrayList<>());
        }
        return productoList;
    }

    public void setProductoList(MutableLiveData<List<Producto>> productoList) {
        this.productoList = productoList;
    }
}
