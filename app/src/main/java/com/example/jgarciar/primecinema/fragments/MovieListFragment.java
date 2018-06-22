package com.example.jgarciar.primecinema.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jgarciar.primecinema.R;
import com.example.jgarciar.primecinema.adapters.MovieAdapter;
import com.example.jgarciar.primecinema.models.Movie;

import java.util.ArrayList;

public class MovieListFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        ArrayList<Movie> movies = (ArrayList<Movie>) getArguments().getSerializable("theKey");

        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.rv_top_movies);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        MovieAdapter movieAdapter = new MovieAdapter(movies, getActivity());
        recyclerView.setAdapter(movieAdapter);

        return view;
    }
}
