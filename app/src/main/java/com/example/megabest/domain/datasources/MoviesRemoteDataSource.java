package com.example.megabest.domain.datasources;

import androidx.lifecycle.MutableLiveData;

import com.example.megabest.domain.core.Credentials;
import com.example.megabest.domain.core.network.RetrofitInstance;
import com.example.megabest.entities.Movie;
import com.example.megabest.entities.MovieSearchResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesRemoteDataSource {

    private MovieApi movieApi = RetrofitInstance.getMovieApi();

    public MutableLiveData<List<Movie>> searchMovies(String searchKey) {
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
}
