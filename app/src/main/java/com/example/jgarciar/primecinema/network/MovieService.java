package com.example.jgarciar.primecinema.network;

import com.example.jgarciar.primecinema.models.MovieDetails;
import com.example.jgarciar.primecinema.models.MoviePage;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService
{
    String TMDB_BASE_URL = "https://api.themoviedb.org";

    String TMDB_POSTER_URL = "https://image.tmdb.org/t/p/w185/";

    String TMDB_API_KEY = "1878c131f288c61ec2c2e13e7819146d";

    String OMDB_BASE_URL = "http://www.omdbapi.com";

    String OMDB_API_KEY = "3a15122c";

    String ORIGINAL_LANGUAGE = "en";

    String VOTE_AVERAGE = "vote_average.desc";

    String VOTE_COUNT = "vote_count.desc";

    @GET("/3/discover/movie")
    Call<MoviePage> getMoviePages
            (@Query("api_key") String apiKey,
             @Query("primary_release_year") String year,
             @Query("with_genres") String genre,
             @Query("with_original_language") String language,
             @Query("sort_by") String voteAverage,
             @Query("sort_by") String voteCount);

    @GET("/")
    Call<MovieDetails> getMovieDetails
            (@Query("apiKey") String apiKey,
             @Query("t") String title);
}
