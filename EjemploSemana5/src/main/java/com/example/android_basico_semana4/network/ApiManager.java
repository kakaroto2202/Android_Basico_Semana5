package com.example.android_basico_semana4.network;

import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ApiManager {
    private static final String TAG = "ApiManager";
    private RequestQueue requestQueue;
    private Context context;

    public ApiManager(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }

    // Método para realizar una petición GET que devuelve JSON
    public void hacerPeticionJSON(String url) {
        JsonObjectRequest jsonRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Imprimir respuesta JSON en consola
                        Log.d(TAG, "Respuesta JSON: " + response.toString());
                        System.out.println("Respuesta JSON: " + response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Imprimir error en consola
                        Log.e(TAG, "Error en la petición: " + error.getMessage());
                        System.out.println("Error: " + error.getMessage());
                    }
                }
        );

        // Agregar la petición a la cola
        requestQueue.add(jsonRequest);
    }

    // Método para realizar una petición GET que devuelve String
    public void hacerPeticionString(String url) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Imprimir respuesta como String en consola
                        Log.d(TAG, "Respuesta String: " + response);
                        System.out.println("Respuesta String: " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Imprimir error en consola
                        Log.e(TAG, "Error en la petición: " + error.getMessage());
                        System.out.println("Error: " + error.getMessage());
                    }
                }
        );

        // Agregar la petición a la cola
        requestQueue.add(stringRequest);
    }

    public void hacerPeticionPOST(String url, JSONObject parametros) {
        JsonObjectRequest jsonRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                parametros, // Parámetros del POST
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "Respuesta POST: " + response.toString());
                        System.out.println("Respuesta POST: " + response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Error POST: " + error.getMessage());
                        System.out.println("Error POST: " + error.getMessage());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                // Agregar otros headers si es necesario
                return headers;
            }
        };

        requestQueue.add(jsonRequest);
    }
}