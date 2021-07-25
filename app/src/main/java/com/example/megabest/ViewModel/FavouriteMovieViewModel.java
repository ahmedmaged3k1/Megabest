package com.example.megabest.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.megabest.Model.FavouriteMovie;
import com.example.megabest.Repositories.FavouriteMovieRepositery;
import com.example.megabest.Repositories.MoviesRepository;

import java.util.List;

public class FavouriteMovieViewModel extends ViewModel {
    private FavouriteMovieRepositery moviesRepository;
    public FavouriteMovieViewModel(Context context) {
        moviesRepository= FavouriteMovieRepositery.getInstance(context);
    }


    public void insertFavouriteMovie(FavouriteMovie movie){
        moviesRepository.insertFavouriteMovie(movie);
    }

    public void deleteFavouriteMovie(FavouriteMovie movie){
        moviesRepository.deleteFavouriteMovie(movie);
    }
    public LiveData<List<FavouriteMovie>> getFavouriteMovies(){
        return moviesRepository.getFavouriteMovies();
    }

}

