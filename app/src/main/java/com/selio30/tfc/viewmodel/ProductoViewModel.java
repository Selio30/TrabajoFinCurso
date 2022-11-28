package com.selio30.tfc.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.selio30.tfc.entity.Ubicacion;

import java.util.ArrayList;
import java.util.List;

public class ProductoViewModel extends ViewModel {
    private MutableLiveData<List<Ubicacion>> ubicacion_DB;
    private String prodSelected;

    /**
     * Busquedas de la Base de datos
     */
    public MutableLiveData<List<Ubicacion>> getUbicacion_DB() {
        if (ubicacion_DB == null) {
            ubicacion_DB = new MutableLiveData<>();
            ubicacion_DB.setValue(new ArrayList<>());
        }
        return ubicacion_DB;
    }

    public void setUbicacion_DB(MutableLiveData<List<Ubicacion>> ubicacion_DB) {
        this.ubicacion_DB = ubicacion_DB;
    }

    public String getProdSelected() {
        if (prodSelected == null) {
            prodSelected = new String();
        }
        return prodSelected;
    }

    public void setProdSelected(String prodSelected) {
        this.prodSelected = prodSelected;
    }
}
