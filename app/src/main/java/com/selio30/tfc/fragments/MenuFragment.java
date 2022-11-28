package com.selio30.tfc.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.selio30.tfc.R;
import com.selio30.tfc.utils.GestionProductos;


public class MenuFragment extends Fragment {
    Button btnCompleta, btnMedia;

    public MenuFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        btnCompleta = getActivity().findViewById(R.id.btnCompleta);
        btnMedia = getActivity().findViewById(R.id.btnMedia);

        init();
        prepareBtn();
    }

    private void init() {
        GestionProductos gestionProductos = new GestionProductos(requireActivity(), requireActivity(), requireActivity(), requireView());

        //PARTE DE LA BUSQUEDA REMOTA PARA AÑADIRLOS
        gestionProductos.getRemoteAlmacen();
        gestionProductos.getRemoteFormato();
        gestionProductos.getRemoteInventario();
        gestionProductos.getRemoteLocalizacion();
        gestionProductos.getRemoteProducto();
        gestionProductos.getRemoteTipo();
        gestionProductos.getRemoteUbicacion();
        gestionProductos.getRemoteProductoAlmacen();
        gestionProductos.getRemoteProductoHabitual();
        gestionProductos.getRemoteProductoUbicacion();
        //FIN PARTE REMOTA
    }

    private void prepareBtn() {
        btnCompleta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.ContainerView, new ListFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        btnMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                getActivity()
//                        .getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.ContainerView, new ListHabitualesFragment())
//                        .addToBackStack(null)
//                        .commit();
                Toast.makeText(getContext(), "Coming soon", Toast.LENGTH_SHORT).show();
            }
        });
    }
}