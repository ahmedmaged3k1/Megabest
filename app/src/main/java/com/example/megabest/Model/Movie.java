package com.example.megabest.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

public class Movie implements Parcelable {
    private String poster_path ;
    private String title ;
    private Float vote_average ;
    private String overview;
    private Float popularity;
    private String releaseDate ;
    private String backdrop_path;

    private int id;

    public Movie(String backdrop_path, String title) {
        this.backdrop_path = backdrop_path;
        this.title = title;
    }

    public Movie(String poster_path, String title, Float vote_average, String overview, Float popularity, String releaseDate, String backdrop_path, int id) {
        this.poster_path = poster_path;
        this.title = title;
        this.vote_average = vote_average;
        this.overview = overview;
        this.popularity = popularity;
        this.releaseDate = releaseDate;
        this.backdrop_path = backdrop_path;
        this.id = id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public Float getVote_average() {
        return vote_average;
    }

    public void setVote_average(Float vote_average) {
        this.vote_average = vote_average;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    protected Movie(Parcel in) {
        poster_path = in.readString();
        title = in.readString();
        if (in.readByte() == 0) {
            vote_average = null;
        } else {
            vote_average = in.readFloat();
        }
        overview = in.readString();
        if (in.readByte() == 0) {
            popularity = null;
        } else {
            popularity = in.readFloat();
        }
        releaseDate = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getPosterPath() {
        return poster_path;
    }

    public void setPosterPath(String posterPath) {
        this.poster_path = posterPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getVoteAverage() {
        return vote_average;
    }

    public void setVoteAverage(Float voteAverage) {
        this.vote_average = voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Float getPopularity() {
        return popularity;
    }

    public void setPopularity(Float popularity) {
        this.popularity = popularity;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(poster_path);
        parcel.writeString(title);
        if (vote_average == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeFloat(vote_average);
        }
        parcel.writeString(overview);
        if (popularity == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeFloat(popularity);
        }
        parcel.writeString(releaseDate);
    }
}
