package com.example.jgarciar.primecinema;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieNetwork
{
    private static Retrofit retrofit;

    public static Retrofit geMovieNetwork()
    {
        if (retrofit == null)
        {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(MovieService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}