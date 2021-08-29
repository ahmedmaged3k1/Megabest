package com.example.megabest.domain.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.megabest.entities.FavouriteMovie;
import com.example.megabest.domain.dataSource.room.MoviesDataBase;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class FavouriteMovieRepository {
    private static FavouriteMovieRepository instance ;
    private MoviesDataBase moviesDataBase;
    private Executor executor = Executors.newSingleThreadExecutor();
    public  static FavouriteMovieRepository getInstance(Context context){
        if (instance==null){
            instance= new FavouriteMovieRepository( context);
        }
        return instance;
    }
    public FavouriteMovieRepository(Context context){
        moviesDataBase = MoviesDataBase.getInstance(context);
    }
    public void insertFavouriteMovie(FavouriteMovie movie){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                moviesDataBase.movieDao().insertFav(movie);

            }
        });

    }
    public void deleteFavouriteMovie(FavouriteMovie movie){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                moviesDataBase.movieDao().deleteFavMovie(movie);
            }
        });

    }
    public LiveData<List<FavouriteMovie>> getFavouriteMovies (){
        return   moviesDataBase.movieDao().getFavMovies();
    }
}
