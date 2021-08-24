package com.example.megabest.app;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.megabest.entities.MovieTrailer;
import com.example.megabest.domain.repositories.MoviesRepositoryImpl;
import com.example.megabest.entities.Movie;

import java.util.List;

public class MovieViewModel extends ViewModel {
    private MoviesRepositoryImpl moviesRepository;


    public MovieViewModel(  ) {
        moviesRepository= MoviesRepositoryImpl.getInstance();
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
