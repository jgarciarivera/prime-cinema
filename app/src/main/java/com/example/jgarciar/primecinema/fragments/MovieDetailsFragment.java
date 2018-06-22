package com.example.jgarciar.primecinema.fragments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jgarciar.primecinema.R;
import com.example.jgarciar.primecinema.models.Movie;

import java.util.ArrayList;

public class MovieDetailsFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        String fragmentLoaded = "MovieDetailsFragment loaded successfully...";
        Log.wtf("----------", fragmentLoaded);

        return inflater.inflate(R.layout.fragment_movie_details, container, false);
    }
}
