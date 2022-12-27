package com.example.megabest.data.dataSource.RemoteDataSource.network;

import com.example.megabest.data.dataSource.RemoteDataSource.entities.MovieSearchResponse;
import com.example.megabest.data.dataSource.RemoteDataSource.entities.MovieTrailerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("3/movie/popular")
    Call<MovieSearchResponse> getPopular(@Query("api_key")String key, @Query("page") String page);
    //3/movie/{movie_id}/similar
    @GET("3/movie/{movie_id}/similar")
    Call<MovieSearchResponse> getSimilar(@Path("movie_id") String id, @Query("api_key") String key, @Query("page") String page);

    @GET("3/movie/{movie_id}/videos")
    Call<MovieTrailerResponse> getVideos(@Path("movie_id") String id, @Query("api_key") String key);


    @GET("3/search/movie")
    Call<MovieSearchResponse> searchMovie(
            @Query("api_key")String key ,
            @Query("query") String query,
            @Query("page") String page
    );

}
