package com.example.moveuitemplate.request;

import com.example.moveuitemplate.utils.Credentials;
import com.example.moveuitemplate.utils.MovieApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Servicey {
    private static final Retrofit.Builder retrofitBuilder =  new Retrofit.Builder().baseUrl(Credentials.BASE_URL).addConverterFactory(GsonConverterFactory.create());
    private static  Retrofit retrofit = retrofitBuilder.build();
    private static MovieApi movieApi = retrofit.create(MovieApi.class);
    public static MovieApi getMovieApi()
    {
        return movieApi;
    }
}
