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

    @SerializedName("results")
    @Expose
    private List<Movie> moviesList = null;

    public List<Movie> getMoviesList()
    {
        return moviesList;
    }

    public void setMoviesList(List<Movie> moviesList)
    {
        this.moviesList = moviesList;
    }

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
}
