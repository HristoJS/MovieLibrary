package com.evilcorp.hristo.movielibrary;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by Hristo on 10/20/2016.
 */

public class VolleyRequest {
    private String url;
    private RequestQueue requestQueue;
    public VolleyRequest(Context context,String url){
        requestQueue = Volley.newRequestQueue(context);
        this.url = url;
    }
    void SendRequest(){
        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        // Add the request to the RequestQueue.
        requestQueue.add(jsonObjectRequest);
    }

}
