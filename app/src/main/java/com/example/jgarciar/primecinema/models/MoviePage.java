package com.example.jgarciar.primecinema.models;

import java.util.List;

import com.example.jgarciar.primecinema.models.Movie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MoviePage
{
    @SerializedName("results")
    @Expose
    private List<Movie> movies = null;

    public List<Movie> getMovies()
    {
        return movies;
    }
}
