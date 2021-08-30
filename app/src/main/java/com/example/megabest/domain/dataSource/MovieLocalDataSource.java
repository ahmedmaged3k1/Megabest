package com.example.megabest.domain.dataSource;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.megabest.domain.dataSource.room.MoviesDataBase;
import com.example.megabest.domain.repositories.FavouriteMovieRepository;
import com.example.megabest.domain.repositories.MovieLocalRepository;
import com.example.megabest.entities.FavouriteMovie;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static android.content.ContentValues.TAG;

public class MovieLocalDataSource implements MovieLocalRepository {
    private static MovieLocalDataSource instance;
    private MoviesDataBase moviesDataBase;
    private Executor executor = Executors.newSingleThreadExecutor();

    public static MovieLocalDataSource getInstance(Context context) {
        if (instance == null) {
            instance = new MovieLocalDataSource(context);
        }
        return instance;
    }

    public MovieLocalDataSource(Context context) {
        moviesDataBase = MoviesDataBase.getInstance(context);
    }

    @Override
    public void insertFavouriteMovie(FavouriteMovie movie) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                moviesDataBase.movieDao().insertFav(movie);

            }
        });

    }

    @Override
    public void deleteFavouriteMovie(FavouriteMovie movie) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                moviesDataBase.movieDao().deleteFavMovie(movie);
            }
        });

    }

    @Override
    public LiveData<List<FavouriteMovie>> getFavouriteMovies() {
        return moviesDataBase.movieDao().getFavMovies();
    }
}
