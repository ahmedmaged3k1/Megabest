package com.example.megabest.domain.dataSource;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.megabest.domain.core.Credentials;
import com.example.megabest.domain.core.network.RetrofitInstance;
import com.example.megabest.domain.dataSource.room.MoviesDataBase;
import com.example.megabest.domain.repositories.MovieRemoteRepository;
import com.example.megabest.entities.Movie;
import com.example.megabest.entities.MovieSearchResponse;
import com.example.megabest.entities.MovieTrailer;
import com.example.megabest.entities.MovieTrailerResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class MovieRemoteDataSource implements MovieRemoteRepository {
    private static MovieRemoteDataSource instance;
    private MoviesDataBase moviesDataBase;

    public static MovieRemoteDataSource getInstance(Context context) {
        if (instance == null) {
            instance = new MovieRemoteDataSource(context );
        }
        return instance;
    }

    public MovieRemoteDataSource(Context context) {
        moviesDataBase = MoviesDataBase.getInstance(context);
    }


    @Override
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

    @Override
    public MutableLiveData<List<Movie>> getPopularMovies() {

        MovieApi movieApi = RetrofitInstance.getMovieApi();
        Call<MovieSearchResponse> responseCall = movieApi.getPopular(Credentials.apiKey, "1");
        final MutableLiveData<List<Movie>> popularMoviesList = new MutableLiveData<>();
        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {

                popularMoviesList.setValue(response.body().getMovieList());
                Log.d(TAG, "onResponse: size : popular testttt  " + popularMoviesList.getValue().size());


            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: Faileddddd");
                popularMoviesList.setValue(null);
            }
        });


        return popularMoviesList;
    }

    @Override
    public MutableLiveData<List<Movie>> getSimilarMovies(String id) {

        MovieApi movieApi = RetrofitInstance.getMovieApi();
        Call<MovieSearchResponse> responseCall = movieApi.getSimilar(id, Credentials.apiKey, "1");
        final MutableLiveData<List<Movie>> similarMoviesList = new MutableLiveData<>();
        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                similarMoviesList.setValue(response.body().getMovieList());
                Log.d(TAG, "onResponse: size : popular testttt  " + similarMoviesList.getValue().size());


            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: Faileddddd");
                similarMoviesList.setValue(null);
            }
        });


        return similarMoviesList;
    }

    @Override
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


            }

            @Override
            public void onFailure(Call<MovieTrailerResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: faaaillll");
                movieVideos.setValue(null);

            }
        });


        return movieVideos;
    }

}
