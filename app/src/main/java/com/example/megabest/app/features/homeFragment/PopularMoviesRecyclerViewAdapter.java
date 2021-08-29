package com.example.megabest.app.features.homeFragment;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.megabest.app.features.movieDetails.DetailsActivity;
import com.example.megabest.R;
import com.example.megabest.entities.Movie;
import com.makeramen.roundedimageview.RoundedImageView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class PopularMoviesRecyclerViewAdapter  extends RecyclerView.Adapter<PopularMoviesRecyclerViewAdapter.PopularMoviesHolder> {

    private List<Movie> movieList = new ArrayList<>();
    private Context context;

    public PopularMoviesRecyclerViewAdapter(List<Movie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public PopularMoviesRecyclerViewAdapter.PopularMoviesHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.popular_movie,parent,false);
        return new PopularMoviesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PopularMoviesRecyclerViewAdapter.PopularMoviesHolder holder, int position) {
        holder.onBindView(holder, position);


    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class PopularMoviesHolder extends RecyclerView.ViewHolder{
        RoundedImageView movieImage ;
        TextView movieTitle ;
        RatingBar movieRate ;
        Intent detailedActivity;
        CardView popularMoviesCard;
        public PopularMoviesHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            movieImage=itemView.findViewById(R.id.popularMoviePoster);
            movieTitle=itemView.findViewById(R.id.popularMovieName);
            movieRate=itemView.findViewById(R.id.popularMovieRate);
            popularMoviesCard=itemView.findViewById(R.id.popularMoviesCard);
        }
        public void onBindView(PopularMoviesHolder holder , int position){
            popularMoviesCard.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale));
            holder.movieRate.setRating((movieList.get(position).getVoteAverage()/2));
            holder.movieTitle.setText(movieList.get(position).getTitle().toString());
            Glide.with(holder.itemView.getContext())
                    .load("https://image.tmdb.org/t/p/w500/"+movieList.get(position).getPosterPath())
                    .into(holder.movieImage);
            holder.movieImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    detailedActivity =  new Intent(context, DetailsActivity.class);
                    detailedActivity.putExtra("BackPath Poster",movieList.get(position).getBackdrop_path());
                    detailedActivity.putExtra("OverView",movieList.get(position).getOverview());
                    detailedActivity.putExtra("Movie Title",movieList.get(position).getTitle());
                    detailedActivity.putExtra("Movie Id",String.valueOf(movieList.get(position).getId()));
                    Log.d(TAG, "onClick: movie id "+movieList.get(position).getId());
                 //   ActivityOptions optionsCompat = ActivityOptions.makeSceneTransitionAnimation((Activity) context,movieImage,"image");
                    context.startActivity(detailedActivity);


                }
            });
        }
    }

}
