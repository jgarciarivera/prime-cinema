package com.example.jgarciar.primecinema.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
            public void onResponse(Call<MoviePage> call, Response<MoviePage> moviePageResponse)
            {
                loadMovieListFragment(moviePageResponse.body().getMovies());
            }

            @Override
            public void onFailure(Call<MoviePage> call, Throwable t)
            {
                Toast.makeText(MainActivity.this, "Error fetching movie pages...",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadMovieListFragment(ArrayList<Movie> movies)
    {
        MovieListFragment movieListFragment = new MovieListFragment();

        Bundle bundle = new Bundle();

        bundle.putSerializable("theKey", movies);

        movieListFragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.activity_main, movieListFragment);
        fragmentTransaction.commit();

//        mRecyclerView = findViewById(R.id.rv_top_movies);
//
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
//        mRecyclerView.setLayoutManager(layoutManager);
//
//        mMovieAdapter= new MovieAdapter(movies, mContext, GENRE, YEAR);
//        mRecyclerView.setAdapter(mMovieAdapter);
    }

    private void displayMoviesToast()
    {
        Toast.makeText(MainActivity.this, "Top action films of 2018",
                Toast.LENGTH_LONG).show();
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
