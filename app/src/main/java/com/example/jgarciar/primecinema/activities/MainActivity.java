package com.example.jgarciar.primecinema.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.jgarciar.primecinema.R;
import com.example.jgarciar.primecinema.fragments.MovieListFragment;
import com.example.jgarciar.primecinema.models.Movie;
import com.example.jgarciar.primecinema.models.MoviePage;
import com.example.jgarciar.primecinema.network.MovieService;
import com.example.jgarciar.primecinema.network.tmdbNetwork;

import java.util.ArrayList;
import java.util.List;

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
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchMoviePages();

        displayMoviesToast();
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
            public void onResponse(Call<MoviePage> call, Response<MoviePage> response)
            {
                if (response.isSuccessful() && response.body() != null)
                {
                    loadMovieListFragment(response.body().getMovies());
                }
            }

            @Override
            public void onFailure(Call<MoviePage> call, Throwable t)
            {
                Toast.makeText(MainActivity.this, "Error fetching movie pages...",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadMovieListFragment(List<Movie> movies)
    {
        Bundle bundle = new Bundle();
        bundle.putSerializable("Movies", new ArrayList<>(movies));

        MovieListFragment movieListFragment = new MovieListFragment();
        movieListFragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.activity_main, movieListFragment).commit();
    }

    private void displayMoviesToast()
    {
        Toast.makeText(MainActivity.this, "Top action films of 2017",
                Toast.LENGTH_LONG).show();
    }
}
