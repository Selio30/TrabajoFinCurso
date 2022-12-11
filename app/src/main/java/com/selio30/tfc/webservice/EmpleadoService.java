package com.selio30.tfc.webservice;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.selio30.tfc.entity.Empleado;
import com.selio30.tfc.utils.JSonConstants;
import com.selio30.tfc.viewmodel.VolleyViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmpleadoService {
    private static final String TAG = "EMPLEADO_CONTROLLER";
    private Context context;
    private View view;
    private Gson gson = new Gson();
    private VolleyViewModel volleyViewModel;

    public EmpleadoService(Context context, View view) {
        this.context = context;
        this.view = view;
        volleyViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(VolleyViewModel.class);
    }

    public void read(String username) {
        MutableLiveData<List<Empleado>> mutableLiveData = volleyViewModel.getEmpleados();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, JSonConstants.URL_GET_EMPLEADO + "?username=" + username, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("URL", JSonConstants.URL_GET_EMPLEADO + "?username=" + username);
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
                List<Empleado> empleadoList = new ArrayList(Arrays.asList(gson.fromJson(body.toString(), Empleado[].class)));
                volleyViewModel.getEmpleados().setValue(empleadoList);
                Log.d("EMPLEADO", String.valueOf(volleyViewModel.getEmpleados().getValue()));
            } else {
                if (response.getString("message") != null) {
                    Log.d("ERROR JSON", response.getString("message"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
