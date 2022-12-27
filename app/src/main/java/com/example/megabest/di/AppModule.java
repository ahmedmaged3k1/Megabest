package com.example.megabest.di;

import android.app.Application;

import com.example.megabest.data.core.Credentials;
import com.example.megabest.data.dataSource.RemoteDataSource.network.MovieApi;
import com.example.megabest.data.dataSource.localDataSource.room.MovieDao;
import com.example.megabest.data.dataSource.localDataSource.room.MoviesDataBase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    public MoviesDataBase provideRoomDb(Application context) {
        return MoviesDataBase.getInstance(context);
    }

    @Provides
    @Singleton
    public MovieDao provideRoomDao(MoviesDataBase moviesDataBase) {
        return moviesDataBase.movieDao();

    }

    @Singleton
    @Provides
    public Retrofit provideRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(Credentials.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    public MovieApi provideApiService(Retrofit retrofit) {
        return retrofit.create(MovieApi.class);
    }
}



