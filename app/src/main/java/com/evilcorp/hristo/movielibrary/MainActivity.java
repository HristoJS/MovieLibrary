package com.evilcorp.hristo.movielibrary;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private Spinner yearSpin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Api Call for Data
        VolleyRequest volleyRequest = new VolleyRequest(this,getString(R.string.api_url));
        volleyRequest.SendRequest();
        //Dropdown values
        ArrayList<String> years = new ArrayList<>();
        years.add(getString(R.string.spinner_title));
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1980; i <= thisYear; i++) {
            years.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, years);

        yearSpin = (Spinner)findViewById(R.id.spinner);
        yearSpin.setAdapter(adapter);
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
