package com.example.megabest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.megabest.Adapters.PopularMoviesRecyclerViewAdapter;
import com.example.megabest.Adapters.SimilarMoviesRecyclerView;
import com.example.megabest.Model.FavouriteMovie;
import com.example.megabest.Model.Movie;
import com.example.megabest.Model.MovieTrailer;
import com.example.megabest.Repositories.FavouriteMovieRepositery;
import com.example.megabest.Repositories.MoviesRepository;
import com.example.megabest.ViewModel.FavouriteMovieViewModel;
import com.example.megabest.ViewModel.MovieViewModel;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class DetailsActivity extends AppCompatActivity {
    TextView movieName ;
    TextView movieOverView;
    RoundedImageView movieImage ;
    ImageView movieTrailerButton ;
    ImageView backButton ;
    ImageView favButton ;
    RecyclerView MoviesRecyclerView;
    SimilarMoviesRecyclerView similarMoviesRecyclerView;
    List<Movie> movieList = new ArrayList<>();
    MovieViewModel movieViewModel ;
    FavouriteMovieViewModel favouriteMovieViewModel;
    FavouriteMovieRepositery favouriteMovieRepositery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        setData();
        //Log.d(TAG, "onCreate: "+getIntent().getStringExtra("Movie Id"));
        setSimilarMoviesRecyclerView();
        observePopularMovies(getIntent().getStringExtra("Movie Id"));
       // setBackButton();
        setFavourite();
        openTrailer();

    }
    private void openTrailer(){
        movieTrailerButton=findViewById(R.id.movieTrailerButton);
        Intent trailerActivity = new Intent(DetailsActivity.this,TrailerPlayer.class);
        trailerActivity.putExtra("Movie Id",getIntent().getStringExtra("Movie Id"));
        movieViewModel =new ViewModelProvider(this).get(MovieViewModel.class);
        //trailerActivity.putExtra("Video Key",movieViewModel.getTrailerr(getIntent().getStringExtra("Movie Id")));
        movieViewModel.getTrailer(getIntent().getStringExtra("Movie Id")).observe(this, new Observer<List<MovieTrailer>>() {
            @Override
            public void onChanged(List<MovieTrailer> movieTrailers) {
                trailerActivity.putExtra("Video Key",movieTrailers.get(0).getKey());
                Log.d(TAG, "onChanged: trailer  "+movieTrailers.get(0).getKey());
            }
        });

        movieTrailerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(trailerActivity);
            }
        });
    }
    private void setFavourite(){
        favButton=findViewById(R.id.favButton);
        movieViewModel =new ViewModelProvider(this).get(MovieViewModel.class);
        //favouriteMovieViewModel= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(FavouriteMovieViewModel.class);
        favouriteMovieRepositery= new FavouriteMovieRepositery(this);
        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favButton.setImageResource(R.drawable.ic_baseline_favorite_24);
                favouriteMovieRepositery.insertFavouriteMovie(new FavouriteMovie(getIntent().getStringExtra("BackPath Poster"),getIntent().getStringExtra("Movie Title"),1));
              // movieViewModel.insertFavouriteMovie(new FavouriteMovie(getIntent().getStringExtra("BackPath Poster"),getIntent().getStringExtra("Movie Title"),1));
            }
        });

    }
    private void getMovieKey(){
        movieViewModel =new ViewModelProvider(this).get(MovieViewModel.class);
        movieViewModel.getTrailer(getIntent().getStringExtra("Movie Id")).observe(this, new Observer<List<MovieTrailer>>() {
            @Override
            public void onChanged(List<MovieTrailer> movieTrailers) {
                movieTrailers.get(0).getKey();
            }
        });

    }

    private void setBackButton(){
        backButton=findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  home  = new Intent(DetailsActivity.this,Home.class);
                startActivity(home);
            }
        });
    }
    private void  setData () {
        movieImage=findViewById(R.id.detailedMoviePoster);
        movieOverView=findViewById(R.id.detailedMovieOverView);
        movieName=findViewById(R.id.movieDetailedName);
        movieOverView.setText(getIntent().getStringExtra("OverView"));
        movieName.setText(getIntent().getStringExtra("Movie Title"));
        Glide.with(getApplicationContext())
                .load("https://image.tmdb.org/t/p/w500"+getIntent().getStringExtra("BackPath Poster"))
                .into(movieImage);

    }
    private void observePopularMovies(String id){
        movieViewModel =new ViewModelProvider(this).get(MovieViewModel.class);
        movieViewModel.getSimilarMoviesMutableLiveData(id).observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                movieList=movies;
                Log.d(TAG, "onChanged: moviess "+movieList.size());
                //movieListTemp=movies;
                similarMoviesRecyclerView.setMovieList(movieList);
            }
        });

    }
    private void setSimilarMoviesRecyclerView(){
        MoviesRecyclerView=findViewById(R.id.similarMovies);
       // Log.d(TAG, "setPopularMovieRecyclerView: Movie"+movieList.size());
        similarMoviesRecyclerView= new SimilarMoviesRecyclerView(this,movieList);
        MoviesRecyclerView.setAdapter(similarMoviesRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        MoviesRecyclerView.setLayoutManager(linearLayoutManager );
        MoviesRecyclerView.hasFixedSize();
    }

}