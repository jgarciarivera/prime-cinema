package com.example.jgarciar.primecinema;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>
{

    private List<Movie> dataList;

    public MovieAdapter(List<Movie> movieList)
    {
        this.dataList = movieList;
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
        holder.mMovieTitle.setText(dataList.get(position).getTitle());
    }

    class MovieViewHolder extends RecyclerView.ViewHolder
    {
        TextView mMovieTitle;

        MovieViewHolder(View itemView)
        {
            super(itemView);
            mMovieTitle = itemView.findViewById(R.id.tv_movie_title);
        }
    }

    @Override
    public int getItemCount()
    {
        return dataList.size();
    }
}