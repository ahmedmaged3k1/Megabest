package com.example.megabest.app.Adapters

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.transition.Fade
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.megabest.R
import com.example.megabest.app.Adapters.SimilarMoviesRecyclerView.SimilarMoviesViewHolder
import com.example.megabest.app.DetailsActivity
import com.example.megabest.entities.Movie
import com.makeramen.roundedimageview.RoundedImageView
import java.util.*

class SimilarMoviesRecyclerView(var context: Context, movieList: List<Movie>) :
    RecyclerView.Adapter<SimilarMoviesViewHolder>() {
    private var movieList: List<Movie> = ArrayList()
    fun getMovieList(): List<Movie> {
        return movieList
    }

    fun setMovieList(movieList: List<Movie>) {
        this.movieList = movieList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarMoviesViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.similar_movies, parent, false)
        return SimilarMoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: SimilarMoviesViewHolder, position: Int) {
        holder.onBindView(holder, position)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    inner class SimilarMoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var similarMovieImage: RoundedImageView
        var similarMovieTitle: TextView
        var detailedActivity: Intent? = null
        var similarMoviesCard: CardView
        fun onBindView(holder: SimilarMoviesViewHolder, position: Int) {
            holder.similarMoviesCard.animation = AnimationUtils.loadAnimation(
                context, R.anim.fade_transition
            )
            holder.similarMovieTitle.text = movieList[position].title.toString()
            Glide.with(holder.itemView.context)
                .load("https://image.tmdb.org/t/p/w500/" + movieList[position].posterPath)
                .into(holder.similarMovieImage)
            holder.similarMovieImage.setOnClickListener {
                detailedActivity = Intent(context, DetailsActivity::class.java)
                detailedActivity!!.putExtra("BackPath Poster", movieList[position].backdrop_path)
                detailedActivity!!.putExtra("OverView", movieList[position].overview)
                detailedActivity!!.putExtra("Movie Title", movieList[position].title)
                detailedActivity!!.putExtra("Movie Id", movieList[position].id.toString())
                Log.d(ContentValues.TAG, "onClick: movie id " + movieList[position].id)
                val fade = Fade()

                // ActivityOptions optionsCompat = ActivityOptions.makeSceneTransitionAnimation((Activity) context,similarMovieImage, ViewCompat.getTransitionName(similarMovieImage));
                context.startActivity(detailedActivity)
            }
        }

        init {
            similarMovieImage = itemView.findViewById(R.id.similarMovieImage)
            similarMovieTitle = itemView.findViewById(R.id.similarMovieTitle)
            similarMoviesCard = itemView.findViewById(R.id.similarMoviesCard)
        }
    }

    init {
        this.movieList = movieList
    }
}