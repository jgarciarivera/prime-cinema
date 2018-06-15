package com.example.jgarciar.primecinema;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import static com.example.jgarciar.primecinema.MovieService.*;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>
{
    private List<Movie> movieList;

    public MovieAdapter(List<Movie> movieList)
    {
        this.movieList = movieList;
    }

    class MovieViewHolder extends RecyclerView.ViewHolder
    {
        ImageView mMoviePoster;
        TextView mMovieTitle;
        TextView mMovieVoteAverage;
        TextView mMovieOverview;

        MovieViewHolder(View itemView)
        {
            super(itemView);
            mMoviePoster = itemView.findViewById(R.id.iv_movie_poster);
            mMovieTitle = itemView.findViewById(R.id.tv_movie_title);
            mMovieVoteAverage = itemView.findViewById(R.id.tv_movie_vote_average);
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
        String posterUrl = BASE_POSTER_URL + movieList.get(position).getPosterPath();

        Glide.with(holder.itemView.getContext())
                .load(posterUrl)
                .apply(RequestOptions.overrideOf(0,0))
                .apply(RequestOptions.fitCenterTransform())
                .into(holder.mMoviePoster);

        holder.mMovieVoteAverage.setText(movieList.get(position).getVoteAverage());
        holder.mMovieTitle.setText(movieList.get(position).getTitle());
        holder.mMovieOverview.setText(movieList.get(position).getOverview());
    }

    @Override
    public int getItemCount()
    {
        return movieList.size();
    }
}