package com.example.jgarciar.primecinema.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class omdbNetwork
{
    private static Retrofit retrofit;

    public static Retrofit getOmdbNetwork()
    {
        if (retrofit == null)
        {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(MovieService.OMDB_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
