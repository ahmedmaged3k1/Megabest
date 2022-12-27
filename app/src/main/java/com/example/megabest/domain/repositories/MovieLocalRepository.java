package com.example.megabest.domain.repositories;

import androidx.lifecycle.LiveData;

import com.example.megabest.data.dataSource.localDataSource.entities.FavouriteMovie;

import java.util.List;

public interface MovieLocalRepository {
    void insertFavouriteMovie(FavouriteMovie movie);

    void deleteFavouriteMovie(FavouriteMovie movie);

    LiveData<List<FavouriteMovie>> getFavouriteMovies();



}
