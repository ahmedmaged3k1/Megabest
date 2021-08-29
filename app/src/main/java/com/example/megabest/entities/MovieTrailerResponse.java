package com.example.megabest.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieTrailerResponse {
    @SerializedName("results")
    @Expose
    private List<MovieTrailer> movie;

    public List<MovieTrailer> getMovie() {
        return movie;
    }

    public void setMovie(List<MovieTrailer> movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "MovieTrailerResponse{" +
                "movie=" + movie +
                '}';
    }
}
