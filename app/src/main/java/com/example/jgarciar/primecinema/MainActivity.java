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

        Call<MoviePage> call = service.getMoviePageData(API_KEY, "28",
                "2018", VOTE_AVERAGE, VOTE_COUNT);

        Log.wtf("URL called: ", call.request().url() + "");

        call.enqueue(new Callback<MoviePage>()
        {
            @Override
            public void onResponse(Call<MoviePage> call, Response<MoviePage> moviePageResponse)
            {
                generateMovieCards(moviePageResponse.body().getMoviesList());
            }

            @Override
            public void onFailure(Call<MoviePage> call, Throwable t)
            {
                Toast.makeText(MainActivity.this, "There was an error retrieving the movie data...",
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
}
