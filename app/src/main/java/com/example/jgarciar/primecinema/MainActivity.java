package com.example.jgarciar.primecinema;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
{

    private RecyclerView mRecyclerView;

    private MovieAdapter mMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MovieService service = MovieNetwork.geMovieNetwork().create(MovieService.class);

        Call<MoviePage> call = service.getMoviesData(MovieService.API_KEY, "28", "2018");

        Log.wtf("URL called: ", call.request().url() + "");

        call.enqueue(new Callback<MoviePage>()
        {
            @Override
            public void onResponse(Call<MoviePage> call, Response<MoviePage> response)
            {
                generateMovieList(response.body().getMoviesList());
            }

            @Override
            public void onFailure(Call<MoviePage> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateMovieList(List<Movie> movieDataList)
    {
        mRecyclerView = findViewById(R.id.rv_top_movies);

        mMovieAdapter= new MovieAdapter(movieDataList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setAdapter(mMovieAdapter);
    }
}
