package com.selio30.tfc.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.selio30.tfc.R;
import com.selio30.tfc.entity.Ubicacion;
import com.selio30.tfc.recyclerviews.RVItemAdapter;
import com.selio30.tfc.utils.GestionProductos;
import com.selio30.tfc.viewmodel.ProductoViewModel;
import com.selio30.tfc.viewmodel.VolleyViewModel;

import java.util.List;

public class ListHabitualesFragment extends Fragment {

    private static final String TAG = "JSON";
    private Button btnInsertar;
    private RecyclerView rv;

    private ProductoViewModel productoViewModel;
    private MutableLiveData<List<Ubicacion>> mutableLiveData;

    private VolleyViewModel volleyViewModel;

    public ListHabitualesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        volleyViewModel = new ViewModelProvider(requireActivity()).get(VolleyViewModel.class);
        prepareViewModel();
        prepareRecyclerView();
        initRecycler();
        createButton();
    }

    private void prepareViewModel() {
        productoViewModel = new ViewModelProvider(requireActivity()).get(ProductoViewModel.class);
        mutableLiveData = productoViewModel.getUbicacion_DB();
    }

    public void prepareRecyclerView() {
        GestionProductos gestionProductos = new GestionProductos(requireActivity(), requireActivity(), requireActivity(), requireView());

        gestionProductos.getProductosHabitual();

        rv = getView().findViewById(R.id.prodList);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rv.setAdapter(new RVItemAdapter(getContext(), mutableLiveData.getValue()));
        Log.d(TAG, String.valueOf(mutableLiveData.getValue()));
    }

    /**
     * Preparamos el recycler view para mostrar todos los productos guardados en la base de datos
     */
    private void initRecycler() {
        productoViewModel.getUbicacion_DB().observe(requireActivity(), new Observer<List<Ubicacion>>() {
            @Override
            public void onChanged(List<Ubicacion> ubicacions) {
                rv.getAdapter().notifyDataSetChanged();
            }
        });
    }

    /**
     * Accion del boton para añadir un inventario nuevo
     */
    private void createButton() {
        GestionProductos gestionProductos = new GestionProductos(requireActivity(), requireActivity(), requireActivity(), requireView());
        btnInsertar = getActivity().findViewById(R.id.btn_Inventario);

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setMessage("¿Desea realizar el inventario?")
                        .setCancelable(false)
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                gestionProductos.addInventarioProductoHabitual();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog titulo = alert.create();
                titulo.setTitle("Insert");
                titulo.show();
            }
        });
    }
}
