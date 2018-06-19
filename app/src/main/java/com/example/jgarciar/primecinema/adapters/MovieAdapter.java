package com.example.jgarciar.primecinema.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.jgarciar.primecinema.R;
import com.example.jgarciar.primecinema.models.Movie;

import java.util.List;

import static com.example.jgarciar.primecinema.utilities.MovieService.*;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>
{
    private List<Movie> movies;

    public MovieAdapter(List<Movie> movies)
    {
        this.movies = movies;
    }

    class MovieViewHolder extends RecyclerView.ViewHolder
    {
        ImageView mMoviePoster;
        TextView mMovieTitle;
        TextView mMovieDirector;
        TextView mMovieMpaaRating;
        TextView mMovieOverview;

        MovieViewHolder(View itemView)
        {
            super(itemView);

            mMoviePoster = itemView.findViewById(R.id.iv_movie_poster);
            mMovieTitle = itemView.findViewById(R.id.tv_movie_title);
            mMovieDirector = itemView.findViewById(R.id.tv_movie_director);
            mMovieMpaaRating = itemView.findViewById(R.id.tv_movie_mpaa_rating);
            mMovieOverview = itemView.findViewById(R.id.tv_movie_overview);
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
        String posterUrl = TMDB_POSTER_URL + movies.get(position).getPosterPath();

        Glide.with(holder.itemView.getContext())
                .load(posterUrl)
                .apply(RequestOptions.overrideOf(0,0))
                .apply(RequestOptions.fitCenterTransform())
                .into(holder.mMoviePoster);

        holder.mMovieTitle.setText(movies.get(position).getTitle());
        holder.mMovieDirector.setText("Directed by: ");
        holder.mMovieMpaaRating.setText("PG-13");
        holder.mMovieOverview.setText(movies.get(position).getOverview());
    }

    @Override
    public int getItemCount()
    {
        return movies.size();
    }
}