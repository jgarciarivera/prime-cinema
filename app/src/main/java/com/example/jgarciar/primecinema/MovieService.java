package com.example.jgarciar.primecinema;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService
{
    String BASE_URL = "https://api.themoviedb.org";

    String API_KEY = "1878c131f288c61ec2c2e13e7819146d";

    @GET("/3/discover/movie")
    Call<MoviePage> getMoviesData(@Query("api_key") String apiKey,
                                @Query("with_genres") String genre,
                                @Query("primary_release_year") String year);
}
