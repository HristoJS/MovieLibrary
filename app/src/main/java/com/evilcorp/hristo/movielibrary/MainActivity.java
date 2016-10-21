package com.evilcorp.hristo.movielibrary;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private Spinner yearSpin;
    private ArrayList<Movie> movies;
    private ListView movieList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Api Call for Data
        final VolleyRequest volleyRequest = new VolleyRequest(this,getString(R.string.api_url));

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

        //SearchBar
        SearchView searchView = (SearchView) findViewById(R.id.searchview);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                volleyRequest.SendRequest(query,yearSpin.getSelectedItem().toString());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        //ListView
        movieList = (ListView) findViewById(R.id.listview);
        movieList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                movies.get(position);
            }
        });

    }

    void ParseData(String response){
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject json = (JsonObject)parser.parse(response);
        Type listType = new TypeToken<Movie>(){}.getType();
        movies = new ArrayList<>();
        Movie movie = gson.fromJson(json, listType);
        movies.add(movie);
        VisualizeData();
    }

    void VisualizeData(){
        if(movies!=null){
            if(movies.get(0).Response){
                movieList.setAdapter(new CustomArrayAdapter(this, movies));
            }
            else{
                Log.d("Response","No Movies found");
            }
        }
    }

    public class VolleyRequest {
        private String url;
        private RequestQueue requestQueue;
        VolleyRequest(Context context, String url){
            requestQueue = Volley.newRequestQueue(context);
            this.url = url;
        }
        void SendRequest(final String title,final String year){
            // Request a string response from the provided URL.
           StringRequest jsonObjectRequest = new StringRequest
                    (Request.Method.POST, String.format(url,title,year), new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            Log.d("Response",response);
                            ParseData(response);
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });
            // Add the request to the RequestQueue.
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    15000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(jsonObjectRequest);
        }

    }
}
