package com.example.jgarciar.primecinema;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;
import java.util.Random;

import static com.example.jgarciar.primecinema.MovieService.*;

public class MainActivity extends AppCompatActivity
{
    private RecyclerView mRecyclerView;

    private MovieAdapter mMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MovieService service = MovieNetwork.getMovieNetwork().create(MovieService.class);

        Call<MoviePage> call = service.getMoviePages(TMDB_API_KEY, generateRandomGenre(),
                generateRandomYear(), VOTE_AVERAGE, VOTE_COUNT);

        Log.wtf("URL called: ", call.request().url() + "");

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
                Toast.makeText(MainActivity.this, "Error fetching movie pages...",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateMovieCards(List<Movie> movieDataList)
    {
        mRecyclerView = findViewById(R.id.rv_top_movies);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);

        mMovieAdapter= new MovieAdapter(movieDataList);
        mRecyclerView.setAdapter(mMovieAdapter);
    }

    private int generateRandomGenre()
    {
        int genreIdOptions[] = {28, 12, 16, 35, 80, 99, 18, 10751, 14, 27, 9648, 878};

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
