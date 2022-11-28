package com.selio30.tfc.webservice;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.selio30.tfc.entity.Producto;
import com.selio30.tfc.utils.JSonConstants;
import com.selio30.tfc.viewmodel.VolleyViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ProductoService {
    private static final String TAG = "PRODUCTO_CONTROLLER";
    private Context context;
    private View view;
    private Gson gson = new Gson();
    private VolleyViewModel volleyViewModel;

    public ProductoService(Context context, View view) {
        this.context = context;
        this.view = view;
        volleyViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(VolleyViewModel.class);
    }

    /**
     * Crea el JSon para poder enviarlo a la API, para su inserción.
     *
     * @param producto
     */

    public void create(Producto producto) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", producto.getId());
        map.put("nombre", producto.getName());
        map.put("id_tipo", producto.getId_tipo());
        map.put("id_formato", producto.getId_formato());

        JSONObject jsonObject = new JSONObject(map);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, JSonConstants.URL_POST_PRODUCTO, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                showResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Error Volley: " + error.getMessage());
                Log.d(TAG, "Error. JSON enviado: " + jsonObject.toString());
            }
        });
        volleyViewModel.addToRequestQueue(jsonObjectRequest, context);
    }

    /**
     * Lectura de la API
     */

    public void read() {
        MutableLiveData<List<Producto>> mutableLiveData = volleyViewModel.getProductos();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, JSonConstants.URL_GET_PRODUCTO, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                readParseResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, error.toString());
                if (error != null && error.networkResponse.statusCode == 404) {
                    Log.d("No registros", "no registros");
                } else Log.d(TAG, "Error en la ejecucion de Volley: " + error.getMessage());
            }
        });
        volleyViewModel.addToRequestQueue(request, context);
    }

    public void readParseResponse(JSONObject response) {
        try {
            if (response.getString("body") != null) {
                JSONArray body = response.getJSONArray("body");
                List<Producto> productoList = new ArrayList(Arrays.asList(gson.fromJson(body.toString(), Producto[].class)));
                volleyViewModel.getProductos().setValue(productoList);
            } else {
                if (response.getString("message") != null) {
                    Log.d("ERROR JSON", response.getString("message"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void showResponse(JSONObject response) {
        try {
            switch (response.getString("response_code")) {
                case "201": //Correcto
                    Toast.makeText(context, "Operación correcta", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Operación correcta");
                    break;
                case "400": // error
                    Toast.makeText(context, "No se ha realizado la operación. Falta información", Toast.LENGTH_SHORT).show();
                    break;
                case "503": // error
                    Toast.makeText(context, "No se ha realizado la operación. Servicio no disponible. ", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(context, "No se ha realizado la operación. Error general. ", Toast.LENGTH_SHORT).show();
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}