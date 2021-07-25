package com.example.megabest.Model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FavouriteMovie {
    private String poster_path ;
    private String title ;
    @PrimaryKey (autoGenerate = false)
    private int id ;
    private int fav ;

    public FavouriteMovie(String poster_path, String title, int id, int fav) {
        this.poster_path = poster_path;
        this.title = title;
        this.id = id;
        this.fav = fav;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getFav() {
        return fav;
    }

    public void setFav(int fav) {
        this.fav = fav;
    }
}
