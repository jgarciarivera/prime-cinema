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
import com.example.jgarciar.primecinema.activities.MainActivity;
import com.example.jgarciar.primecinema.models.Movie;
import com.example.jgarciar.primecinema.models.MovieDetails;
import com.example.jgarciar.primecinema.network.MovieService;
import com.example.jgarciar.primecinema.network.omdbNetwork;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.jgarciar.primecinema.activities.MainActivity.GENRE_ID;
import static com.example.jgarciar.primecinema.activities.MainActivity.YEAR;
import static com.example.jgarciar.primecinema.network.MovieService.OMDB_API_KEY;
import static com.example.jgarciar.primecinema.network.MovieService.TMDB_POSTER_URL;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>
{
    private ArrayList<Movie> movies;

    private Context context;

    private String year;

    private String genre;

    public MovieAdapter(ArrayList<Movie> movies, Context context, String year, String genre)
    {
        this.movies = movies;
        this.context = context;
        this.genre = year;
        this.year = genre;
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
                public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response)
                {
                    mMovieDirector.setText("Director: " + response.body().getDirector());
                    mMovieMpaaRating.setText(response.body().getRated());
                }

                @Override
                public void onFailure(Call<MovieDetails> call, Throwable t)
                {
                    Toast.makeText(context, "Error fetching movie details...",
                            Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onClick(View view)
        {
            Toast.makeText(context, "Top " + mapIdToGenre(GENRE_ID) + " films of "
                    + YEAR, Toast.LENGTH_LONG).show();
        }

        private String mapIdToGenre(String genreId)
        {
            HashMap<String, String> map = new HashMap<>();

            map.put("28", "Action");
            map.put("16", "Animated");
            map.put("35", "Comedy");
            map.put("80", "Crime");
            map.put("99", "Documentary");
            map.put("18", "Drama");
            map.put("27", "Horror");
            map.put("10749", "Romance");
            map.put("878", "Science-Fiction");

            return map.get(genreId);
        }
    }

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