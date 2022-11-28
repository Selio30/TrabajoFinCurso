package com.selio30.tfc.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.selio30.tfc.R;
import com.selio30.tfc.utils.JSonConstants;

import java.util.HashMap;
import java.util.Map;


public class LoginFragment extends Fragment {

    EditText etUser, etPass;
    String username, pass;
    Button btLogin;
    ProgressBar progressBar;

    public LoginFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        innit();
    }

    private void innit() {
        etUser = getActivity().findViewById(R.id.etuser);
        etPass = getActivity().findViewById(R.id.etpwd);

        progressBar = getActivity().findViewById(R.id.progress);

        btLogin = getActivity().findViewById(R.id.btnLogin);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login() {
        if (etUser.getText().toString().equals("")) {
            Toast.makeText(getContext(), "Insert user", Toast.LENGTH_SHORT).show();
        } else if (etPass.getText().toString().equals("")) {
            Toast.makeText(getContext(), "Insert password", Toast.LENGTH_SHORT).show();
        } else {
            progressBar.setVisibility(View.VISIBLE);

            username = etUser.getText().toString().trim();
            pass = etPass.getText().toString().trim();

            StringRequest request = new StringRequest(Request.Method.POST, JSonConstants.URL_LOGIN, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressBar.setVisibility(View.GONE);

                    if (response.equalsIgnoreCase("Login Success")) {
                        etUser.setText("");
                        etPass.setText("");
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ContainerView, new MenuFragment()).addToBackStack(null).commit();
                    } else {
                        Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                        Log.d("ERROR", response);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("ERROR", error.getMessage());
                }
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("username", username);
                    params.put("password", pass);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            requestQueue.add(request);
        }
    }
}