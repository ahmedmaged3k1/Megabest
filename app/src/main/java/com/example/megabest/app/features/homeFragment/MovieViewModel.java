package com.example.megabest.app.features.homeFragment;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.megabest.domain.dataSource.MovieRemoteDataSource;
import com.example.megabest.domain.repositories.MovieRemoteRepository;
import com.example.megabest.entities.MovieTrailer;
import com.example.megabest.domain.repositories.MoviesRepository;
import com.example.megabest.entities.Movie;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MovieViewModel extends ViewModel {
    private MoviesRepository moviesRepository;
    private MovieRemoteRepository movieRemoteRepository ;


    public MovieViewModel() {
        moviesRepository=MoviesRepository.getInstance();
        //movieRemoteRepository = new MovieRemoteDataSource(application);
    }


    public MutableLiveData<List<Movie>> getSearchMoviesMutableLiveData(String searchKey) {

        //Log.d(TAG, "getSearchMoviesMutableLiveData: "+moviesRepository.searchMovies(searchKey).size());

        return moviesRepository.searchMovies(searchKey);
    }
    public MutableLiveData<List<Movie>> getSimilarMoviesMutableLiveData(String id) {

        //Log.d(TAG, "getSearchMoviesMutableLiveData: "+moviesRepository.searchMovies(searchKey).size());

        return moviesRepository.getSimilarMovies(id);
    }

    public MutableLiveData<List<Movie>> getPopularMoviesMutableLiveData( ) {
      //  moviesRepository=MoviesRepository.getInstance();

        return moviesRepository.getPopularMovies();
    }
    public MutableLiveData<List<MovieTrailer>> getTrailer(String id){
        return moviesRepository.getMovieTrailer(id);

    }



}
