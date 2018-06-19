package com.example.jgarciar.primecinema.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movie
{
    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    public String getTitle()
    {
        return title;
    }

    public String getPosterPath()
    {
        return posterPath;
    }
}
