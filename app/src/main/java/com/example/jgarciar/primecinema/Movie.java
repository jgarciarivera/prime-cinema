package com.example.jgarciar.primecinema;

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

    @SerializedName("overview")
    @Expose
    private String overview;

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getOverview() {
        return overview;
    }
}
