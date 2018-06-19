package com.example.jgarciar.primecinema.adapters;

import android.content.Context;
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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.jgarciar.primecinema.network.MovieService.OMDB_API_KEY;
import static com.example.jgarciar.primecinema.network.MovieService.TMDB_POSTER_URL;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>
{
    private List<Movie> movies;

    private Context context;

    private int year;

    private int genre;

    public MovieAdapter(List<Movie> movies, Context context, int genre, int year)
    {
        this.movies = movies;
        this.context = context;
        this.genre = genre;
        this.year = year;
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        Movie mMovie;
        ImageView mMoviePoster;
        TextView mMovieTitle;
        TextView mMovieOverview;
        TextView mMovieDirector;

        MovieViewHolder(View itemView)
        {
            super(itemView);

            mMoviePoster = itemView.findViewById(R.id.iv_movie_poster);
            mMovieTitle = itemView.findViewById(R.id.tv_movie_title);
            mMovieOverview = itemView.findViewById(R.id.tv_movie_overview);
            mMovieDirector = itemView.findViewById(R.id.tv_movie_director);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
            String stringGenre = null;

            if (genre == 28)
            {
                stringGenre = "action";
            }

            else if (genre == 12)
            {
                stringGenre = "adventure";
            }

            else if (genre == 16)
            {
                stringGenre = "animation";
            }

            else if (genre == 35)
            {
                stringGenre = "comedy";
            }

            else if (genre == 80)
            {
                stringGenre = "crime";
            }

            else if (genre == 99)
            {
                stringGenre = "documentary";
            }

            else if (genre == 18)
            {
                stringGenre = "drama";
            }

            else if (genre == 10751)
            {
                stringGenre = "family";
            }

            else if (genre == 27)
            {
                stringGenre = "horror";
            }

            else if (genre == 10749)
            {
                stringGenre = "romance";
            }

            else if (genre == 53)
            {
                stringGenre = "thriller";
            }

            else if (genre == 878)
            {
                stringGenre = "science-fiction";
            }

            else
            {
                stringGenre = "(error finding genre)";
            }

            Toast.makeText(context, "Top " + stringGenre + " movies of " + year,
                    Toast.LENGTH_SHORT).show();
        }

        public void bind(Movie movie)
        {
            mMovie = movie;

            String movieTitle = mMovie.getTitle();

            String posterUrl = TMDB_POSTER_URL + movie.getPosterPath();

            Glide.with(itemView.getContext())
                    .load(posterUrl)
                    .apply(RequestOptions.overrideOf(0,0))
                    .apply(RequestOptions.fitCenterTransform())
                    .into(mMoviePoster);

            mMovieTitle.setText(movieTitle);

            MovieService service = omdbNetwork.getOmdbNetwork().create(MovieService.class);

            Call<MovieDetails> call = service.getMovieDetails(OMDB_API_KEY, movieTitle);

            Log.wtf("Second URL called: ", call.request().url() + "");

            call.enqueue(new Callback<MovieDetails>()
            {
                @Override
                public void onResponse(Call<MovieDetails> call, Response<MovieDetails> movieDetailsResponse)
                {
                    MovieDetails movieDetails = movieDetailsResponse.body();

                    mMovieDirector.setText("Director: " + movieDetails.getDirector());
                    mMovieOverview.setText(movieDetails.getPlot());
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

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.movie_item, parent, false);

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