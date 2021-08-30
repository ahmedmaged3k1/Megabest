package com.example.megabest.app.features.favouriteFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.megabest.R;
import com.example.megabest.entities.FavouriteMovie;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


public class FavouriteFragment extends Fragment {


    private RecyclerView favMovieRecyclerView;
    private View view;
    private List<FavouriteMovie> movieList = new ArrayList<>();
    FavourtieMoviesRecyclerViewAdapter favourtieMoviesRecyclerViewAdapter;
    FavouriteMovieViewModel favouriteMovieViewModel;


    public FavouriteFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_favourite, container, false);
        setFavouriteMovieRecyclerView();
        observeFavMovies();

        return view;
    }

    private void setFavouriteMovieRecyclerView() {
        favMovieRecyclerView = view.findViewById(R.id.favMovieRecyclerView);
        Log.d(TAG, "setPopularMovieRecyclerView: Movie" + movieList.size());
        favourtieMoviesRecyclerViewAdapter = new FavourtieMoviesRecyclerViewAdapter(getContext(), movieList);
        favMovieRecyclerView.setAdapter(favourtieMoviesRecyclerViewAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        favMovieRecyclerView.setLayoutManager(gridLayoutManager);
        favMovieRecyclerView.hasFixedSize();

    }

    private void observeFavMovies() {
        favouriteMovieViewModel = new FavouriteMovieViewModel(getActivity().getApplication());
        //favouriteMovieRepositery= FavouriteMovieRepository.getInstance(getContext());
        favouriteMovieViewModel.getFavouriteMovies().observe(getActivity(), new Observer<List<FavouriteMovie>>() {
            @Override
            public void onChanged(List<FavouriteMovie> favouriteMovies) {
                movieList = favouriteMovies;
                favourtieMoviesRecyclerViewAdapter.setMovieList(movieList);
            }
        });

    }
}