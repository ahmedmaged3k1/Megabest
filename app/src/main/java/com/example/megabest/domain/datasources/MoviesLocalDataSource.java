package com.example.megabest.domain.datasources;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.megabest.domain.datasources.Room.MoviesDataBase;
import com.example.megabest.entities.FavouriteMovie;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MoviesLocalDataSource {

    private MoviesDataBase moviesDataBase;
    private Executor executor = Executors.newSingleThreadExecutor();

    public MoviesLocalDataSource(Context context) {
        moviesDataBase = MoviesDataBase.getInstance(context);
    }

    public void insertFav(FavouriteMovie movie) {
        executor.execute((Runnable) () -> moviesDataBase.movieDao().insertFav(movie));
    }

    public void deleteFavMovie(FavouriteMovie movie) {
        executor.execute(() -> moviesDataBase.movieDao().deleteFavMovie(movie));
    }

    public LiveData<List<FavouriteMovie>> getFavMovies() {
        return moviesDataBase.movieDao().getFavMovies();
    }
}
