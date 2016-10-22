package com.evilcorp.hristo.movielibrary;

import java.util.Date;

//Data Model
public class Movie {
    boolean Response;    //true if movie found
    String Error;

    //ListView Data
    String Title;
    String Year;
    String Genre;
    String Poster;
    String imdbRating;

    //Dialog Data
    String Runtime;
    String Director;
    String Writer;
    String Country;
    String Awards;
    String Actors;
    String Plot;

    //Unused
    String Rated;
    String Released;
    String Language;
    String Metascore;
    String imdbVotes;
    String imdbID;
    String Type;
}
