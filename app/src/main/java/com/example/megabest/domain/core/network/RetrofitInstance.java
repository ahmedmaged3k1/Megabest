package com.example.megabest.domain.core.network;

import com.example.megabest.domain.core.Credentials;
import com.example.megabest.domain.datasources.MovieApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder().baseUrl(Credentials.baseUrl).
                    addConverterFactory(GsonConverterFactory.create());
    private static  Retrofit retrofit = retrofitBuilder.build();
    private static MovieApi movieApi = retrofit.create(MovieApi.class);
    public static MovieApi getMovieApi(){
        return movieApi;
    }
}
