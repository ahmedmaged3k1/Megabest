package com.example.megabest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.os.Bundle;
import android.util.Log;

import com.example.megabest.Repositories.MoviesRepository;
import com.example.megabest.ViewModel.MovieViewModel;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import static android.content.ContentValues.TAG;

public class TrailerPlayer extends YouTubeBaseActivity {
    YouTubePlayerView movieTrailerPlayer ;
    YouTubePlayer.OnInitializedListener onInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        movieTrailerPlayer=findViewById(R.id.trailerPlayer);
     //   movieViewModel =new ViewModelProvider( this).get(MovieViewModel.class);
       // String key =movieViewModel.getTrailer(getIntent().getStringExtra("Movie Id")).getKey();
       // String key = moviesRepository.getMovieTrailer(getIntent().getStringExtra("Movie Id")).getKey();
       // Log.d(TAG, "onCreate: keyy "+key);

        onInitializedListener= new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                youTubePlayer.loadVideo(getIntent().getStringExtra("Video Key"));
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        movieTrailerPlayer.initialize("AIzaSyBxr3HXSl6DmHwKui2UOcRXLN6gp9aoHxw",onInitializedListener);
    }
}