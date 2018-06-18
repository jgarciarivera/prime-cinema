package com.example.jgarciar.primecinema;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService
{
    String BASE_API_URL = "https://api.themoviedb.org";

    String BASE_POSTER_URL = "https://image.tmdb.org/t/p/w185/";

    String API_KEY = "1878c131f288c61ec2c2e13e7819146d";

    String VOTE_AVERAGE = "vote_average.desc";

    String VOTE_COUNT = "40";

    @GET("/3/discover/movie")
    Call<MoviePage> getMoviePageData
            (@Query("api_key") String apiKey,
             @Query("with_genres") int genre,
             @Query("primary_release_year") int year,
             @Query("sort_by") String voteAverage,
             @Query("vote_count.gte") String voteCount);
}
