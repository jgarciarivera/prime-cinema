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

    public MovieAdapter(Context context, List<Movie> movies, int genre, int year)
    {
        this.movies = movies;
        this.context = context;
        this.genre = genre;
        this.year = year;
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView mMoviePoster;
        TextView mMovieTitle;
        TextView mMovieOverview;
        TextView mMovieDirector;
        Movie mMovie;

        MovieViewHolder(View itemView)
        {
            super(itemView);

            itemView.setOnClickListener(this);

            mMoviePoster = itemView.findViewById(R.id.iv_movie_poster);
            mMovieTitle = itemView.findViewById(R.id.tv_movie_title);
            mMovieOverview = itemView.findViewById(R.id.tv_movie_overview);
            mMovieDirector = itemView.findViewById(R.id.tv_movie_director);
        }

        @Override
        public void onClick(View view)
        {
            String stringGenre = null;

            if (genre == 28)
            {
                stringGenre = "Action";
            }

            else if (genre == 12)
            {
                stringGenre = "Adventure";
            }

            else if (genre == 16)
            {
                stringGenre = "Animation";
            }

            else if (genre == 35)
            {
                stringGenre = "Comedy";
            }

            else if (genre == 80)
            {
                stringGenre = "Crime";
            }

            else if (genre == 99)
            {
                stringGenre = "Documentary";
            }

            else if (genre == 18)
            {
                stringGenre = "Drama";
            }

            else if (genre == 10751)
            {
                stringGenre = "Family";
            }

            else if (genre == 14)
            {
                stringGenre = "Fantasy";
            }

            else if (genre == 36)
            {
                stringGenre = "History";
            }

            else if (genre == 27)
            {
                stringGenre = "Horror";
            }

            else if (genre == 10402)
            {
                stringGenre = "Music";
            }

            else if (genre == 9648)
            {
                stringGenre = "Mystery";
            }

            else if (genre == 878)
            {
                stringGenre = "Science Fiction";
            }

            else if (genre == 12)
            {
                stringGenre = "Adventure";
            }


            Toast.makeText(context, "Best " + stringGenre + " movies of " + year,
                    Toast.LENGTH_SHORT).show();

            generateMovieDetailsFragment();
        }

        public void bind(Movie movie)
        {
            mMovie = movie;

            String posterUrl = TMDB_POSTER_URL + movie.getPosterPath();

            Glide.with(itemView.getContext())
                    .load(posterUrl)
                    .apply(RequestOptions.overrideOf(0,0))
                    .apply(RequestOptions.fitCenterTransform())
                    .into(mMoviePoster);

            mMovieTitle.setText(movie.getTitle());

            String movieTitle = mMovie.getTitle();

            MovieService service = omdbNetwork.getOmdbNetwork().create(MovieService.class);

            Call<MovieDetails> call = service.getMovieDetails(OMDB_API_KEY, movieTitle);

            Log.wtf("Second URL called: ", call.request().url() + "");

            call.enqueue(new Callback<MovieDetails>()
            {
                @Override
                public void onResponse(Call<MovieDetails> call, Response<MovieDetails> movieDetailsResponse)
                {
                    MovieDetails movieDetails = movieDetailsResponse.body();
//                    generateMovieDetailsFragment(movieDetails);

                    mMovieDirector.setText("Director: " + movieDetails.getDirector());
                    mMovieOverview.setText(movieDetails.getPlot());
                    //mMovieMpaaRating.setText(movieDetails.getRated());
                }

                @Override
                public void onFailure(Call<MovieDetails> call, Throwable t)
                {
                    Toast.makeText(context, "Error fetching movie pages...",
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

    public void generateMovieDetailsFragment()
    {

    }
}