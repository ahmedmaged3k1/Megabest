package com.example.megabest.ViewModel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.megabest.Model.FavouriteMovie;
import com.example.megabest.Repositories.FavouriteMovieRepositery;
import com.example.megabest.Repositories.MoviesRepository;

import java.util.List;

public class FavouriteMovieViewModel extends AndroidViewModel {
    private FavouriteMovieRepositery moviesRepository;
    public FavouriteMovieViewModel(@NonNull Application application) {
        super(application);
        moviesRepository= FavouriteMovieRepositery.getInstance(application);
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

