package com.example.megabest.ui.features.homeFragment;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.megabest.domain.usecase.SearchMoviesUseCase;
import com.example.megabest.data.dataSource.RemoteDataSource.entities.Movie;
import com.example.megabest.data.dataSource.RemoteDataSource.entities.MovieTrailer;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MovieViewModel extends ViewModel {

    private SearchMoviesUseCase searchMoviesUseCase;


    public MovieViewModel(@NotNull Application application) {
        super();
        searchMoviesUseCase = new SearchMoviesUseCase(application);
    }


    public MutableLiveData<List<Movie>> getSearchMoviesMutableLiveData(String searchKey) {


        return searchMoviesUseCase.getSearchMoviesMutableLiveData(searchKey);
    }

    public MutableLiveData<List<Movie>> getSimilarMoviesMutableLiveData(String id) {


        return searchMoviesUseCase.getSimilarMoviesMutableLiveData(id);
    }

    public MutableLiveData<List<Movie>> getPopularMoviesMutableLiveData() {
        return searchMoviesUseCase.getPopularMovies();
    }

    public MutableLiveData<List<MovieTrailer>> getTrailer(String id) {
        return searchMoviesUseCase.getTrailer(id);

    }


}
