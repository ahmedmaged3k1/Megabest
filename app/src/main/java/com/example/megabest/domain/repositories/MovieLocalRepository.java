package com.example.megabest.domain.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.megabest.entities.FavouriteMovie;
import com.example.megabest.entities.Movie;
import com.example.megabest.entities.MovieTrailer;

import java.util.List;

public interface MovieLocalRepository {
    void insertFavouriteMovie(FavouriteMovie movie);

    void deleteFavouriteMovie(FavouriteMovie movie);

    LiveData<List<FavouriteMovie>> getFavouriteMovies();



}
