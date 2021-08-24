package com.example.megabest.domain.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.megabest.entities.Movie;

import java.util.List;

public interface MoviesRepository {
    MutableLiveData<List<Movie>> searchMovies(String searchKey);
    List<Movie> getCachedMovies();
}
