package com.example.jgarciar.primecinema;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService
{
    String BASE_URL = "https://api.themoviedb.org";

    String API_KEY = "1878c131f288c61ec2c2e13e7819146d";

    String VOTE_AVERAGE = "vote_average.desc";

    @GET("/3/discover/movie")
    Call<MoviePage> getMoviesData(@Query("api_key") String apiKey,
                                  @Query("with_genres") String genre,
                                  @Query("primary_release_year") String year,
                                  @Query("sort_by") String voteAverage,
                                  @Query("vote_count.gte") String voteCount);
}
