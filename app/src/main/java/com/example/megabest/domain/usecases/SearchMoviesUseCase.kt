package com.example.megabest.domain.usecases

import androidx.lifecycle.MutableLiveData
import com.example.megabest.domain.repositories.MoviesRepository
import com.example.megabest.domain.repositories.MoviesRepositoryImpl
import com.example.megabest.entities.Movie

class SearchMoviesUseCase(private val repository: MoviesRepository = MoviesRepositoryImpl()) {


    fun searchMovies(searchQuery: String?): MutableLiveData<List<Movie>> {
        val movies: List<Movie> = repository.searchMovies(searchQuery)
        return if (movies.isEmpty()) {
            repository.cachedMovies
        } else {
            movies
        }
    }
}