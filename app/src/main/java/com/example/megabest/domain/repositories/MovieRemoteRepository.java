package com.example.megabest.domain.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.megabest.data.dataSource.RemoteDataSource.entities.Movie;
import com.example.megabest.data.dataSource.RemoteDataSource.entities.MovieTrailer;

import java.util.List;

public interface MovieRemoteRepository {
    public MutableLiveData<List<Movie>> searchMovies(String searchKey);

    public MutableLiveData<List<Movie>> getPopularMovies();

    public MutableLiveData<List<Movie>> getSimilarMovies(String id);

    public MutableLiveData<List<MovieTrailer>> getMovieTrailer(String id);
}
