package com.example.megabest.ViewModel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.megabest.Model.FavouriteMovie;
import com.example.megabest.Model.MovieTrailer;
import com.example.megabest.Repositories.MoviesRepository;
import com.example.megabest.Model.Movie;

import java.util.List;

import static android.content.ContentValues.TAG;

public class MovieViewModel extends ViewModel {
    private MoviesRepository moviesRepository;


    public MovieViewModel(  ) {
        moviesRepository=MoviesRepository.getInstance();
    }

    public MutableLiveData<List<Movie>> getSearchMoviesMutableLiveData(String searchKey) {

        //Log.d(TAG, "getSearchMoviesMutableLiveData: "+moviesRepository.searchMovies(searchKey).size());

        return moviesRepository.searchMovies(searchKey);
    }
    public MutableLiveData<List<Movie>> getSimilarMoviesMutableLiveData(String id) {

        //Log.d(TAG, "getSearchMoviesMutableLiveData: "+moviesRepository.searchMovies(searchKey).size());

        return moviesRepository.getSimilarMovies(id);
    }

    public MutableLiveData<List<Movie>> getPopularMoviesMutableLiveData( ) {
      //  moviesRepository=MoviesRepository.getInstance();

        return moviesRepository.getPopularMovies();
    }
    public MutableLiveData<List<MovieTrailer>> getTrailer(String id){
        return moviesRepository.getMovieTrailer(id);

    }



}
