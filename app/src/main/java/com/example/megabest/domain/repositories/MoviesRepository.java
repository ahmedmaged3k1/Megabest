package com.example.megabest.domain.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.megabest.entities.Movie;
import com.example.megabest.entities.MovieTrailer;
import com.example.megabest.entities.MovieSearchResponse;
import com.example.megabest.entities.MovieTrailerResponse;
import com.example.megabest.domain.core.Credentials;
import com.example.megabest.domain.dataSource.MovieApi;
import com.example.megabest.domain.core.network.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class MoviesRepository {

    private static MoviesRepository instance;

    public static MoviesRepository getInstance() {
        if (instance == null) {
            instance = new MoviesRepository();
        }
        return instance;
    }

    public MoviesRepository() {

    }


    public MutableLiveData<List<Movie>> searchMovies(String searchKey) {
        MovieApi movieApi = RetrofitInstance.getMovieApi();
        Call<MovieSearchResponse> responseCall = movieApi.searchMovie(Credentials.apiKey, searchKey, "1");
        final MutableLiveData<List<Movie>> searchMoviesList = new MutableLiveData<>();
        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
//                movieList=response.body().getMovieList();
                searchMoviesList.setValue(response.body().getMovieList());
                //   setSearchMoviesList(response.body().getMovieList());
                //Log.d(TAG, "onResponse: "+response.errorBody().toString());
                // Log.d(TAG, "onResponse: size :  "+response.body().getMovieList().size());

            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
                searchMoviesList.setValue(null);
            }
        });
        return searchMoviesList;
    }

    public MutableLiveData<List<Movie>> getPopularMovies() {

        MovieApi movieApi = RetrofitInstance.getMovieApi();
        Call<MovieSearchResponse> responseCall = movieApi.getPopular(Credentials.apiKey, "1");
        final MutableLiveData<List<Movie>> popularMoviesList = new MutableLiveData<>();
        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
//                movieList=response.body().getMovieList();
                //   popularMoviesList.addAll(response.body().getMovieList());
                popularMoviesList.setValue(response.body().getMovieList());
                Log.d(TAG, "onResponse: size : popular testttt  " + popularMoviesList.getValue().size());


                //Log.d(TAG, "onResponse: "+response.errorBody().toString());

            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: Faileddddd");
                popularMoviesList.setValue(null);
            }
        });
        // Log.d(TAG, "onResponse: size : popular testttt  "+test.getValue().size());

        return popularMoviesList;
    }

    public MutableLiveData<List<Movie>> getSimilarMovies(String id) {

        MovieApi movieApi = RetrofitInstance.getMovieApi();
        Call<MovieSearchResponse> responseCall = movieApi.getSimilar(id, Credentials.apiKey, "1");
        final MutableLiveData<List<Movie>> similarMoviesList = new MutableLiveData<>();
        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
//                movieList=response.body().getMovieList();
                //   popularMoviesList.addAll(response.body().getMovieList());
                similarMoviesList.setValue(response.body().getMovieList());
                Log.d(TAG, "onResponse: size : popular testttt  " + similarMoviesList.getValue().size());


                //Log.d(TAG, "onResponse: "+response.errorBody().toString());

            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: Faileddddd");
                similarMoviesList.setValue(null);
            }
        });
        // Log.d(TAG, "onResponse: size : popular testttt  "+test.getValue().size());

        return similarMoviesList;
    }

    public MutableLiveData<List<MovieTrailer>> getMovieTrailer(String id) {

        MovieApi movieApi = RetrofitInstance.getMovieApi();
        Call<MovieTrailerResponse> responseCall = movieApi.getVideos(id, Credentials.apiKey);
        //Log.d(TAG, "getMovieTrailer: Testing");
        final MutableLiveData<List<MovieTrailer>> movieVideos = new MutableLiveData<>();
        responseCall.enqueue(new Callback<MovieTrailerResponse>() {
            @Override
            public void onResponse(Call<MovieTrailerResponse> call, Response<MovieTrailerResponse> response) {
                Log.d(TAG, "getMovieTrailer: Testing");
                movieVideos.setValue(response.body().getMovie());
                Log.d(TAG, "Testt : video key is " + response.body().getMovie().get(0).getKey());
                //Log.d(TAG, "onResponse: size : movies testttt  "+movieVideos.getValue().size());

            }

            @Override
            public void onFailure(Call<MovieTrailerResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: faaaillll");
                movieVideos.setValue(null);

            }
        });
        // Log.d(TAG, "onResponse: size : popular testttt  "+test.getValue().size());

        return movieVideos;
    }


}
