package com.evilcorp.hristo.movielibrary;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VolleyRequest volleyRequest = new VolleyRequest(this,getString(R.string.api_url));
        volleyRequest.SendRequest();
    }



    public class VolleyRequest {
        private String url;
        private RequestQueue requestQueue;
        public VolleyRequest(Context context, String url){
            requestQueue = Volley.newRequestQueue(context);
            this.url = url;
        }
        void SendRequest(){
            // Request a string response from the provided URL.
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, "http://www.omdbapi.com/?t=Test&y=1994&plot=short&r=json", null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("Response",response.toString());

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
}
