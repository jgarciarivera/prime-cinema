package com.example.jgarciar.primecinema.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable
{
    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    @SerializedName("overview")
    @Expose
    private String overview;

    public String getOverview()
    {
        return overview;
    }

    public String getTitle()
    {
        return title;
    }

    public String getPosterPath()
    {
        return posterPath;
    }
}
