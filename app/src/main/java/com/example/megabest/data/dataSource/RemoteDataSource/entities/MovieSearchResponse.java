package com.example.megabest.data.dataSource.RemoteDataSource.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieSearchResponse {

    @SerializedName("results")
    @Expose
    private List<Movie> movieList;


    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @Override
    public String toString() {
        return "MovieSearchResponse{" +

                ", movieList=" + movieList +
                '}';
    }
}
