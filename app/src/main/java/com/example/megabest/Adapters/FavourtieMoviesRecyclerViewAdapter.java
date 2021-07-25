package com.example.megabest.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.megabest.DetailsActivity;
import com.example.megabest.Model.FavouriteMovie;
import com.example.megabest.Model.Movie;
import com.example.megabest.R;
import com.makeramen.roundedimageview.RoundedImageView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
public class FavourtieMoviesRecyclerViewAdapter extends RecyclerView.Adapter<FavourtieMoviesRecyclerViewAdapter.FavouriteMoviesViewHolder> {
    private Context context ;
    private List<FavouriteMovie> movieList = new ArrayList<>();

    public FavourtieMoviesRecyclerViewAdapter(Context context, List<FavouriteMovie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<FavouriteMovie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<FavouriteMovie> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public FavouriteMoviesViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.favourite_movies,parent,false);
        return new FavourtieMoviesRecyclerViewAdapter.FavouriteMoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FavourtieMoviesRecyclerViewAdapter.FavouriteMoviesViewHolder holder, int position) {
        holder.onBindView(holder, position);
    }

    @Override
    public int getItemCount() {
        return movieList.size();

    }

    public class FavouriteMoviesViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView favMovieImage ;
        TextView favMovieTitle ;

        public FavouriteMoviesViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            favMovieImage=itemView.findViewById(R.id.favMovieImage);
            favMovieTitle=itemView.findViewById(R.id.favMovieTitle);
        }
        public void onBindView(FavourtieMoviesRecyclerViewAdapter.FavouriteMoviesViewHolder holder , int position){
            holder.favMovieTitle.setText(movieList.get(position).getTitle().toString());
            Glide.with(holder.itemView.getContext())
                    .load("https://image.tmdb.org/t/p/w500/"+movieList.get(position).getPoster_path())
                    .into(holder.favMovieImage);


        }
    }


}

