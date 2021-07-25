package com.example.megabest.Repositories;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.megabest.Model.FavouriteMovie;
import com.example.megabest.Room.MoviesDataBase;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;

import static android.content.ContentValues.TAG;

public class FavouriteMovieRepositery {
    private static FavouriteMovieRepositery instance ;
    private MoviesDataBase moviesDataBase;
    private Executor executor = Executors.newSingleThreadExecutor();
    public  static  FavouriteMovieRepositery getInstance(Context context){
        if (instance==null){
            instance= new FavouriteMovieRepositery( context);
        }
        return instance;
    }
    public FavouriteMovieRepositery(Context context){
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
