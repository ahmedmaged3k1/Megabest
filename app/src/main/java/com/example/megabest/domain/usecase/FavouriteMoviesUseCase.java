package com.example.megabest.domain.usecase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.megabest.domain.dataSource.MovieLocalDataSource;
import com.example.megabest.domain.repositories.MovieLocalRepository;
import com.example.megabest.entities.FavouriteMovie;

import java.util.List;

public class FavouriteMoviesUseCase {
     private MovieLocalRepository movieLocalRepository;
    public FavouriteMoviesUseCase(@NonNull Application application) {
        super();
        movieLocalRepository = new MovieLocalDataSource(application);

    }


    public void insertFavouriteMovie(FavouriteMovie movie) {
        movieLocalRepository.insertFavouriteMovie(movie);
    }

    public void deleteFavouriteMovie(FavouriteMovie movie) {
        movieLocalRepository.deleteFavouriteMovie(movie);
    }

    public LiveData<List<FavouriteMovie>> getFavouriteMovies() {

        return movieLocalRepository.getFavouriteMovies();
    }

}
