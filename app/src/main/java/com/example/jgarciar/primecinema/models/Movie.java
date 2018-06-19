package com.example.jgarciar.primecinema.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movie
{
    @SerializedName("id")
    @Expose
    private Integer theMovieDatabaseId;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    @SerializedName("overview")
    @Expose
    private String overview;

    public int getTheMovieDatabaseId()
    {
        return theMovieDatabaseId;
    }

    public String getTitle()
    {
        return title;
    }

    public String getPosterPath()
    {
        return posterPath;
    }

    public String getOverview()
    {
        return overview;
    }
}
