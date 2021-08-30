package com.example.megabest.app.features.favouriteFragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.megabest.domain.usecase.FavouriteMoviesUseCase;
import com.example.megabest.entities.FavouriteMovie;

import java.util.List;

public class FavouriteMovieViewModel extends ViewModel {
    private FavouriteMoviesUseCase favouriteMoviesUseCase;

    public FavouriteMovieViewModel(@NonNull Application application) {
        super();

        favouriteMoviesUseCase = new FavouriteMoviesUseCase(application);

    }

    public void insertFavouriteMovie(FavouriteMovie movie) {
        favouriteMoviesUseCase.insertFavouriteMovie(movie);
    }

    public void deleteFavouriteMovie(FavouriteMovie movie) {
        favouriteMoviesUseCase.deleteFavouriteMovie(movie);
    }

    public LiveData<List<FavouriteMovie>> getFavouriteMovies() {

        return favouriteMoviesUseCase.getFavouriteMovies();
    }

}

