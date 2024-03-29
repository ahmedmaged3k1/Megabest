package com.example.megabest.ui.features.movieDetails;

import android.content.Context;
import android.content.Intent;
import android.transition.Fade;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.megabest.databinding.ActivityMainBinding;
import com.example.megabest.data.dataSource.RemoteDataSource.entities.Movie;
import com.example.megabest.R;
import com.makeramen.roundedimageview.RoundedImageView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static android.content.ContentValues.TAG;

public class SimilarMoviesRecyclerView extends RecyclerView.Adapter<SimilarMoviesRecyclerView.SimilarMoviesViewHolder> {
    private Context context ;
    private List<Movie> movieList ;
    private ActivityMainBinding binding;

    public SimilarMoviesRecyclerView(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public SimilarMoviesViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.similar_movies,parent,false);
        return new SimilarMoviesRecyclerView.SimilarMoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SimilarMoviesRecyclerView.SimilarMoviesViewHolder holder, int position) {
        holder.onBindView(holder, position);
    }

    @Override
    public int getItemCount() {
        return movieList.size();

    }

    public class SimilarMoviesViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView similarMovieImage ;
        TextView similarMovieTitle ;
        Intent detailedActivity;
        CardView similarMoviesCard;
        public SimilarMoviesViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            similarMovieImage=itemView.findViewById(R.id.similarMovieImage);
            similarMovieTitle=itemView.findViewById(R.id.similarMovieTitle);
            similarMoviesCard=itemView.findViewById(R.id.similarMoviesCard);
        }
        public void onBindView(SimilarMoviesRecyclerView.SimilarMoviesViewHolder holder , int position){
            holder.similarMoviesCard.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition));
            holder.similarMovieTitle.setText(movieList.get(position).getTitle().toString());
            Glide.with(holder.itemView.getContext())
                    .load("https://image.tmdb.org/t/p/w500/"+movieList.get(position).getPosterPath())
                    .into(holder.similarMovieImage);
            holder.similarMovieImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    detailedActivity =  new Intent(context, DetailsActivity.class);
                    detailedActivity.putExtra("BackPath Poster",movieList.get(position).getBackdrop_path());
                    detailedActivity.putExtra("OverView",movieList.get(position).getOverview());
                    detailedActivity.putExtra("Movie Title",movieList.get(position).getTitle());
                    detailedActivity.putExtra("Movie Id",String.valueOf(movieList.get(position).getId()));
                    Log.d(TAG, "onClick: movie id "+movieList.get(position).getId());
                    Fade fade = new Fade();

                   // ActivityOptions optionsCompat = ActivityOptions.makeSceneTransitionAnimation((Activity) context,similarMovieImage, ViewCompat.getTransitionName(similarMovieImage));
                    context.startActivity(detailedActivity);

                }
            });

                }
            }


}
