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
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.jgarciar.primecinema.network.MovieService.ORIGINAL_LANGUAGE;
import static com.example.jgarciar.primecinema.network.MovieService.TMDB_API_KEY;
import static com.example.jgarciar.primecinema.network.MovieService.VOTE_AVERAGE;
import static com.example.jgarciar.primecinema.network.MovieService.VOTE_COUNT;

public class MainActivity extends AppCompatActivity
{
    public static String YEAR = generateRandomYear();

    public static String GENRE_ID = generateRandomGenre();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchMoviePages();
    }

    private void fetchMoviePages()
    {
        MovieService service = tmdbNetwork.getTmdbNetwork().create(MovieService.class);

        Call<MoviePage> call = service.getMoviePages(TMDB_API_KEY, YEAR, GENRE_ID,
                ORIGINAL_LANGUAGE, VOTE_AVERAGE, VOTE_COUNT);

        Log.wtf("First network request: ", call.request().url() + "");

        call.enqueue(new Callback<MoviePage>()
        {
            @Override
            public void onResponse(Call<MoviePage> call, Response<MoviePage> response)
            {
                loadMovieListFragment(response.body().getMovies());
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

    private static String generateRandomYear()
    {
        String releaseYearOptions[] = {"2014", "2015", "2016", "2017", "2018"};

        Random random = new Random();
        int index = random.nextInt(releaseYearOptions.length);

        return releaseYearOptions[index];
    }

    private static String generateRandomGenre()
    {
        String genreIdOptions[] = {"28", "16", "35", "80", "99", "18", "27", "10749", "878"};

        Random random = new Random();
        int index = random.nextInt(genreIdOptions.length);

        return genreIdOptions[index];
    }
}
