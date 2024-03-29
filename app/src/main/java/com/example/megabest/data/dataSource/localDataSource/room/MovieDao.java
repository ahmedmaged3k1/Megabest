package com.example.megabest.data.dataSource.localDataSource.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.megabest.data.dataSource.localDataSource.entities.FavouriteMovie;

import java.util.List;

@Dao
public interface MovieDao {
    @Insert
    void  insertFav(FavouriteMovie movie);
    @Delete
    void deleteFavMovie(FavouriteMovie movie);
    @Query("select * from FavouriteMovie")
    LiveData<List<FavouriteMovie>> getFavMovies();

}
