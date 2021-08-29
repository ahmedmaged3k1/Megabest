package com.example.megabest.app.features.favourite;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.megabest.domain.repositories.MovieLocalRepository;
import com.example.megabest.entities.FavouriteMovie;
import com.example.megabest.R;
import com.example.megabest.domain.repositories.FavouriteMovieRepository;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


public class FavouriteFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView favMovieRecyclerView;
    private View view;
    private List<FavouriteMovie> movieList = new ArrayList<>();
    FavourtieMoviesRecyclerViewAdapter favourtieMoviesRecyclerViewAdapter;
    FavouriteMovieViewModel movieViewModel;
    FavouriteMovieRepository favouriteMovieRepositery;
    MovieLocalRepository movieLocalRepository;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FavouriteFragment() {

    }



    public static FavouriteFragment newInstance() {
        FavouriteFragment fragment = new FavouriteFragment();
        //Bundle args = new Bundle();
       // args.putString(ARG_PARAM1, param1);
       // args.putString(ARG_PARAM2, param2);
       // fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_favourite, container, false);
        setFavouriteMovieRecyclerView();
       observeFavMovies();

        return view;
    }
    private void setFavouriteMovieRecyclerView(){
        favMovieRecyclerView=view.findViewById(R.id.favMovieRecyclerView);
        Log.d(TAG, "setPopularMovieRecyclerView: Movie"+movieList.size());
        favourtieMoviesRecyclerViewAdapter= new FavourtieMoviesRecyclerViewAdapter(getContext(),movieList);
        favMovieRecyclerView.setAdapter(favourtieMoviesRecyclerViewAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        favMovieRecyclerView.setLayoutManager(gridLayoutManager );
        favMovieRecyclerView.hasFixedSize();

    }
    private void observeFavMovies(){
        favouriteMovieRepositery= FavouriteMovieRepository.getInstance(getContext());
        favouriteMovieRepositery.getFavouriteMovies().observe(getActivity(), new Observer<List<FavouriteMovie>>() {
            @Override
            public void onChanged(List<FavouriteMovie> favouriteMovies) {
                movieList=favouriteMovies;
                favourtieMoviesRecyclerViewAdapter.setMovieList(movieList);
            }
        });
      //  movieViewModel = new FavouriteMovieViewModel(getActivity().getApplication());
        //Log.d(TAG, "observeFavMovies: "+movieViewModel.getFavouriteMovies().getValue().size());
//        Log.d(TAG, "observeFavMovies: "+movieLocalRepository.getFavouriteMovies().getValue().size());

    }
}