package com.evilcorp.hristo.movielibrary;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/** Custom adapter for displaying an array of Planet objects. */
public class CustomArrayAdapter extends ArrayAdapter<Movie> {

    private LayoutInflater inflater;
    private List<Movie> movies;

    public CustomArrayAdapter(Context context, List<Movie> movies) {
        //super( context, R.layout.step_item, R.id.step_view, arrayList );
        super(context,R.layout.list_item,movies);
        // Cache the LayoutInflate to avoid asking for a new one each time.
        inflater = LayoutInflater.from(context) ;
        this.movies = movies;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        boolean completed = movies.get(position).Response;
        Log.d("Test",completed+"");
        //String item = getItem(position);
        // The child views in each row.
        CheckBox checkBox ;
        TextView textView ;

        convertView = inflater.inflate(R.layout.list_item, null);

        // Find the child views.
//        textView = (TextView) convertView.findViewById( R.id.step_view );
//        checkBox = (CheckBox) convertView.findViewById( R.id.check_box );
//        checkBox.setChecked(true);
//        Log.d("Checked",checkBox.isChecked()+"");
//
//        textView.setText(item);

        return convertView;
    }
}
