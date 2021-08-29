package com.example.megabest.app.features.movieDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.megabest.app.features.home.HomeActivity;
import com.example.megabest.R;
import com.example.megabest.entities.FavouriteMovie;
import com.example.megabest.entities.Movie;
import com.example.megabest.entities.MovieTrailer;
import com.example.megabest.app.features.favourite.FavouriteMovieViewModel;
import com.example.megabest.app.features.homeFragment.MovieViewModel;
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
    int cnt = 0 ;


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
        Intent trailerActivity = new Intent(DetailsActivity.this, TrailerPlayer.class);
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
        favouriteMovieViewModel= ViewModelProviders.of(this).get(FavouriteMovieViewModel.class);
        favouriteMovieViewModel.getFavouriteMovies().observe(this, new Observer<List<FavouriteMovie>>() {
            @Override
            public void onChanged(List<FavouriteMovie> favouriteMovies) {
                    for(int i = 0 ; i<favouriteMovies.size();i++){
                        Log.d(TAG, "setFavourite:  id is "+favouriteMovies.get(i).getId());

                        if(Integer.parseInt(getIntent().getStringExtra("Movie Id"))==favouriteMovies.get(i).getId()){
                            Log.d(TAG, "setFavourite: found  id is "+favouriteMovies.get(i).getId());
                            favouriteMovies.get(i).setFav(0);
                            cnt=1;
                            break;
                        }
                        cnt=0;
                        Log.d(TAG, "onChanged: shaddyyasdyasdasdads");

                    }
                Log.d(TAG, "setFavourite: is     "+cnt);
                if(cnt==1){
                    favButton.setImageResource(R.drawable.ic_baseline_favorite_24);
                }
            }
        });


        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cnt==1){
                    favButton.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                    favouriteMovieViewModel.deleteFavouriteMovie(new FavouriteMovie(getIntent().getStringExtra("BackPath Poster"),getIntent().getStringExtra("Movie Title")
                            ,Integer.parseInt(getIntent().getStringExtra("Movie Id")),1));
                }
                else{
                    favButton.setImageResource(R.drawable.ic_baseline_favorite_24);
                    favouriteMovieViewModel.insertFavouriteMovie(new FavouriteMovie(getIntent().getStringExtra("BackPath Poster"),getIntent().getStringExtra("Movie Title")
                            ,Integer.parseInt(getIntent().getStringExtra("Movie Id")),1));
                }

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
                Intent  home  = new Intent(DetailsActivity.this, HomeActivity.class);
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