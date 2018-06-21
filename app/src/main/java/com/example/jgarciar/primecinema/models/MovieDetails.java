package com.example.jgarciar.primecinema.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieDetails
{
    @SerializedName("Rated")
    @Expose
    private String rated;

    @SerializedName("Runtime")
    @Expose
    private String runtime;

    @SerializedName("Director")
    @Expose
    private String director;

    @SerializedName("Actors")
    @Expose
    private String actors;

    @SerializedName("Awards")
    @Expose
    private String awards;

    @SerializedName("imdbRating")
    @Expose
    private String imdbRating;

    @SerializedName("Response")
    @Expose
    private String response;

    public String getRated()
    {
        return rated;
    }

    public String getRuntime()
    {
        return runtime;
    }

    public String getDirector()
    {
        return director;
    }

    public String getActors()
    {
        return actors;
    }

    public String getAwards()
    {
        return awards;
    }

    public String getImdbRating()
    {
        return imdbRating;
    }

    public String getResponse()
    {
        return response;
    }
}
