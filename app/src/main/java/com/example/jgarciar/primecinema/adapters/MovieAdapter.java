package com.example.jgarciar.primecinema.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.jgarciar.primecinema.R;
import com.example.jgarciar.primecinema.models.Movie;
import com.example.jgarciar.primecinema.models.MovieDetails;
import com.example.jgarciar.primecinema.network.MovieService;
import com.example.jgarciar.primecinema.network.omdbNetwork;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.jgarciar.primecinema.network.MovieService.GENRE;
import static com.example.jgarciar.primecinema.network.MovieService.OMDB_API_KEY;
import static com.example.jgarciar.primecinema.network.MovieService.TMDB_POSTER_URL;
import static com.example.jgarciar.primecinema.network.MovieService.YEAR;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>
{
    private ArrayList<Movie> movies;

    private Context context;

    private String year;

    private String genre;

    public MovieAdapter(ArrayList<Movie> movies, Context context)
    {
        this.movies = movies;
        this.context = context;
        this.genre = GENRE;
        this.year = YEAR;
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView mMoviePoster;
        TextView mMovieTitle;
        TextView mMovieMpaaRating;
        TextView mMovieDirector;
        TextView mMovieOverview;

        MovieViewHolder(View itemView)
        {
            super(itemView);
            itemView.setOnClickListener(this);

            mMoviePoster = itemView.findViewById(R.id.iv_movie_poster);
            mMovieTitle = itemView.findViewById(R.id.tv_movie_title);
            mMovieMpaaRating = itemView.findViewById(R.id.tv_movie_mpaa_rating);
            mMovieDirector = itemView.findViewById(R.id.tv_movie_director);
            mMovieOverview = itemView.findViewById(R.id.tv_movie_overview);
        }

        @Override
        public void onClick(View view)
        {
            Toast.makeText(context, "Should load movie details...",
                    Toast.LENGTH_SHORT).show();

//            MainActivity myActivity = (MainActivity) view.getContext();
//
//            Fragment movieDetailsFragment = new MovieDetailsFragment();
//
//            myActivity.getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.fragment_one, movieDetailsFragment)
//                    .addToBackStack(null).commit();
        }

        public void bind(Movie movie)
        {
            mMovieTitle.setText(movie.getTitle());
            mMovieOverview.setText(movie.getOverview());

            String posterUrl = TMDB_POSTER_URL + movie.getPosterPath();
            Glide.with(itemView.getContext())
                    .load(posterUrl)
                    .apply(RequestOptions.overrideOf(0,0))
                    .apply(RequestOptions.fitCenterTransform())
                    .into(mMoviePoster);

            MovieService service = omdbNetwork.getOmdbNetwork().create(MovieService.class);
            Call<MovieDetails> call = service.getMovieDetails(OMDB_API_KEY, movie.getTitle());

            Log.wtf("Movie details network request: ", call.request().url() + "");

            call.enqueue(new Callback<MovieDetails>()
            {
                @Override
                public void onResponse(Call<MovieDetails> call, Response<MovieDetails> movieDetailsResponse)
                {
                    mMovieDirector.setText("Director: " + movieDetailsResponse.body().getDirector());
                    mMovieMpaaRating.setText(movieDetailsResponse.body().getRated());
                }

                @Override
                public void onFailure(Call<MovieDetails> call, Throwable t)
                {
                    Toast.makeText(context, "Error fetching movie details...",
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.movie_list_item, parent, false);

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position)
    {
        holder.bind(movies.get(position));
    }

    @Override
    public int getItemCount()
    {
        return movies.size();
    }
}