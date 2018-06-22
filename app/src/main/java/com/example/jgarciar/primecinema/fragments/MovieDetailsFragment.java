package com.example.jgarciar.primecinema.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jgarciar.primecinema.R;

public class MovieDetailsFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        String fragmentLoaded = "MovieDetailsFragment loaded successfully...";
        Log.wtf("----------", fragmentLoaded);

        return inflater.inflate(R.layout.movie_details_fragment, container, false);
    }
}
