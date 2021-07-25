package com.example.megabest.Room;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.megabest.Model.FavouriteMovie;
import com.example.megabest.Model.Movie;

    @Database(entities = FavouriteMovie.class ,version = 6)
public  abstract class MoviesDataBase extends RoomDatabase  {
    private static MoviesDataBase instance ;
    public  abstract MovieDao movieDao();
    public static synchronized MoviesDataBase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MoviesDataBase.class, "MovieDatabase")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }


}
