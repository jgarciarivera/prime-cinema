package com.example.jgarciar.primecinema.models;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MoviePage
{
    @SerializedName("results")
    @Expose
    private ArrayList<Movie> movies = null;

    public ArrayList<Movie> getMovies()
    {
        return movies;
    }
}
