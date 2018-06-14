package com.example.jgarciar.primecinema;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MoviePage
{
    @SerializedName("page")
    @Expose
    private Integer page;

    @SerializedName("total_results")
    @Expose
    private Integer totalResults;

    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;

    @SerializedName("movies")
    @Expose
    private List<Movie> movies;

    // Constructor

    public Integer getPage()
    {
        return page;
    }

    public void setPage(Integer page)
    {
        this.page = page;
    }

    public Integer getTotalResults()
    {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults)
    {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages()
    {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages)
    {
        this.totalPages = totalPages;
    }

    public List<Movie> getMovies()
    {
        return movies;
    }

    public void setResults(List<Movie> movie)
    {
        this.movies = movie;
    }
}
