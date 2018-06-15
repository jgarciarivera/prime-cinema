package com.example.jgarciar.primecinema;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MoviePage
{
    @SerializedName("results")
    @Expose
    private List<Movie> moviesList = null;

    public List<Movie> getMoviesList()
    {
        return moviesList;
    }
}
