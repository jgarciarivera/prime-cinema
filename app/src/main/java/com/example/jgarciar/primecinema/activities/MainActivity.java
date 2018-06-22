package com.example.jgarciar.primecinema.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.jgarciar.primecinema.R;
import com.example.jgarciar.primecinema.adapters.MovieAdapter;
import com.example.jgarciar.primecinema.models.Movie;
import com.example.jgarciar.primecinema.models.MoviePage;
import com.example.jgarciar.primecinema.network.MovieService;
import com.example.jgarciar.primecinema.network.tmdbNetwork;

import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.jgarciar.primecinema.network.MovieService.GENRE;
import static com.example.jgarciar.primecinema.network.MovieService.ORIGINAL_LANGUAGE;
import static com.example.jgarciar.primecinema.network.MovieService.TMDB_API_KEY;
import static com.example.jgarciar.primecinema.network.MovieService.VOTE_AVERAGE;
import static com.example.jgarciar.primecinema.network.MovieService.VOTE_COUNT;
import static com.example.jgarciar.primecinema.network.MovieService.YEAR;

public class MainActivity extends AppCompatActivity
{
    private RecyclerView mRecyclerView;

    private MovieAdapter mMovieAdapter;

    private Context mContext = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchMoviePages();

        Toast.makeText(mContext, "Top action films of 2018",
                Toast.LENGTH_LONG).show();
    }

    private void fetchMoviePages()
    {
        MovieService service = tmdbNetwork.getTmdbNetwork().create(MovieService.class);
        Call<MoviePage> call = service.getMoviePages(TMDB_API_KEY, YEAR, GENRE,
                ORIGINAL_LANGUAGE, VOTE_AVERAGE, VOTE_COUNT);

        Log.wtf("First network request: ", call.request().url() + "");

        call.enqueue(new Callback<MoviePage>()
        {
            @Override
            public void onResponse(Call<MoviePage> call, Response<MoviePage> moviePageResponse)
            {
                generateMovieCards(moviePageResponse.body().getMovies());
            }

            @Override
            public void onFailure(Call<MoviePage> call, Throwable t)
            {
                Toast.makeText(mContext, "Error fetching movie pages...",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateMovieCards(List<Movie> movies)
    {
        mRecyclerView = findViewById(R.id.rv_top_movies);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(layoutManager);

        mMovieAdapter= new MovieAdapter(movies, mContext, GENRE, YEAR);
        mRecyclerView.setAdapter(mMovieAdapter);
    }

    private int generateRandomGenre()
    {
        int genreIdOptions[] = {28, 12, 16, 35, 80, 99, 18, 27, 10749, 53, 878};

        Random random = new Random();
        int index = random.nextInt(genreIdOptions.length);

        return genreIdOptions[index];
    }

    private int generateRandomYear()
    {
        int releaseYearOptions[] = {2014, 2015, 2016, 2017, 2018};

        Random random = new Random();
        int index = random.nextInt(releaseYearOptions.length);

        return releaseYearOptions[index];
    }
}
