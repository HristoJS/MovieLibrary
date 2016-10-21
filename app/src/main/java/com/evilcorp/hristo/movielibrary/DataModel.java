package com.evilcorp.hristo.movielibrary;

import java.util.Date;

public class DataModel {
    boolean Response;    //true if movie found

    //ListView Data
    String Title;
    int Year;
    String Genre;
    String Poster;
    int imdbRating;

    //Dialog Data
    String Runtime;
    String Director;
    String[] Writer;
    String Country;
    String Awards;
    String[]Actors;
    String Plot;

    //Unused
    String Rated;
    Date Released;
    String Language;
    int Metascore;
    int imdbVotes;
    String imdbID;
    String Type;
}
