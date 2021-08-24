package com.example.megabest.app.features.favourites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.megabest.domain.repositories.MoviesRepository
import com.example.megabest.domain.repositories.MoviesRepositoryImpl
import com.example.megabest.domain.usecases.SearchMoviesUseCase
import com.example.megabest.entities.FavouriteMovie
import com.example.megabest.entities.Movie

class FavouriteMovieViewModel(
    application: Application,
    private val searchMoviesUseCase: SearchMoviesUseCase = SearchMoviesUseCase()
) : AndroidViewModel(application) {

    fun insertFavouriteMovie(movie: FavouriteMovie?) {
        searchMoviesUseCase.insertFavouriteMovie(movie)
    }

    fun deleteFavouriteMovie(movie: FavouriteMovie?) {
        searchMoviesUseCase.deleteFavouriteMovie(movie)
    }

    val favouriteMovies: LiveData<List<FavouriteMovie>>
        get() = searchMoviesUseCase.getFavouriteMovies()
}