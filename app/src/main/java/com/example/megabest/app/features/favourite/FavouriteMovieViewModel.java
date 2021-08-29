package com.example.megabest.app.features.favourite;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.megabest.domain.dataSource.MovieLocalDataSource;
import com.example.megabest.domain.repositories.MovieLocalRepository;
import com.example.megabest.entities.FavouriteMovie;
import com.example.megabest.domain.repositories.FavouriteMovieRepository;

import java.util.List;

import static android.content.ContentValues.TAG;

public class FavouriteMovieViewModel extends AndroidViewModel {
    private FavouriteMovieRepository moviesRepository;
    private MovieLocalRepository movieLocalRepository ;

    public FavouriteMovieViewModel(@NonNull Application application) {
        super(application);
        moviesRepository= FavouriteMovieRepository.getInstance(application);
        MovieLocalDataSource movieLocalDataSource = MovieLocalDataSource.getInstance(application);

    }


    public void insertFavouriteMovie(FavouriteMovie movie){
        moviesRepository.insertFavouriteMovie(movie);
    }

    public void deleteFavouriteMovie(FavouriteMovie movie){
        moviesRepository.deleteFavouriteMovie(movie);
    }
    public LiveData<List<FavouriteMovie>> getFavouriteMovies(){
//        Log.d(TAG, "getFavouriteMovies: "+movieLocalRepository.getFavouriteMovies().getValue().size());
        return moviesRepository.getFavouriteMovies();
    }

}

