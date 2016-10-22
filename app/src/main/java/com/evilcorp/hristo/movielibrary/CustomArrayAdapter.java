package com.evilcorp.hristo.movielibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.InputStream;
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
        Movie movie = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
//        if (convertView == null) {
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
//        }

        convertView = inflater.inflate(R.layout.list_item, null);
        // Find the child views.
        TextView titleText = (TextView) convertView.findViewById( R.id.titleText );
        TextView yearText = (TextView) convertView.findViewById( R.id.yearText );
        TextView genreText = (TextView) convertView.findViewById( R.id.genreText );
        ImageView moviePoster = (ImageView) convertView.findViewById( R.id.moviePoster );
        RatingBar ratingBar = (RatingBar) convertView.findViewById(R.id.ratingBar);

        // Set the value of each cell
        titleText.setText(movie.Title);
        yearText.setText(movie.Year);
        genreText.setText(movie.Genre);
        ratingBar.setRating(Float.valueOf(movie.imdbRating));
        // Download the image asynchronously
        new DownloadImageTask(moviePoster)
                .execute(movie.Poster);

        return convertView;
    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
