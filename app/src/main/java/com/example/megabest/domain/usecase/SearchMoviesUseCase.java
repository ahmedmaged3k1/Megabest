package com.example.megabest.domain.usecase;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.megabest.data.dataSource.RemoteDataSource.MovieRemoteDataSource;
import com.example.megabest.domain.repositories.MovieRemoteRepository;
import com.example.megabest.data.dataSource.RemoteDataSource.entities.Movie;
import com.example.megabest.data.dataSource.RemoteDataSource.entities.MovieTrailer;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SearchMoviesUseCase {
    private MovieRemoteRepository movieRemoteRepository;

    public SearchMoviesUseCase(@NotNull Application application) {
        movieRemoteRepository = new MovieRemoteDataSource(application);
    }

    public MutableLiveData<List<Movie>> getPopularMovies() {
        return movieRemoteRepository.getPopularMovies();

    }

    public MutableLiveData<List<Movie>> getSearchMoviesMutableLiveData(String searchKey) {


        return movieRemoteRepository.searchMovies(searchKey);
    }

    public MutableLiveData<List<Movie>> getSimilarMoviesMutableLiveData(String id) {


        return movieRemoteRepository.getSimilarMovies(id);
    }


    public MutableLiveData<List<MovieTrailer>> getTrailer(String id) {
        return movieRemoteRepository.getMovieTrailer(id);

    }


}
