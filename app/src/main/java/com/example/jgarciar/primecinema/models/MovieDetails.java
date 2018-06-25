package com.example.jgarciar.primecinema.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieDetails
{
    @SerializedName("Rated")
    @Expose
    private String rated;

    @SerializedName("Director")
    @Expose
    private String director;

    public String getRated()
    {
        return rated;
    }

    public String getDirector()
    {
        return director;
    }
}
