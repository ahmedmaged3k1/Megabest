package com.example.megabest.data.dataSource.RemoteDataSource.network;

import com.example.megabest.data.core.Credentials;

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
