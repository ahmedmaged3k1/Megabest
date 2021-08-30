package com.example.megabest.app.features.homeFragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.megabest.R;
import com.example.megabest.entities.Movie;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class HomeFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static String userName;
    private static int userid;
    AppCompatButton searchButton;

    private View view;
    private RecyclerView popularMovieRecyclerView;
    private TextView name;
    PopularMoviesRecyclerViewAdapter popularMoviesRecyclerViewAdapter;
    private TextInputEditText searchMovies;
    private List<Movie> movieList = new ArrayList<>();
    private List<Movie> movieListTemp = new ArrayList<>();
    MovieViewModel movieViewModel;

    public HomeFragment() {

    }


    public static HomeFragment newInstance(Bundle bundle) {
        HomeFragment fragment = new HomeFragment();
        boolean m = bundle == null;
        Log.d(TAG, "onCreate: bundle " + m);
        userName = bundle.get("Name").toString();
        userid = bundle.getInt("Id");
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        Bundle bundle = getArguments();
        setUserName();
        observePopularMovies();
        setPopularMovieRecyclerView();
        setSearchMovies();


        return view;
    }

    private void setUserName() {
        name = view.findViewById(R.id.helloName);
        name.setText("Hello " + userName + " ,");
    }

    private void setSearchMovies() {
        searchButton = view.findViewById(R.id.searchMoviesButton);
        searchMovies = view.findViewById(R.id.searchMovies);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(searchMovies.getText().toString())) {
                    popularMoviesRecyclerViewAdapter.setMovieList(movieListTemp);
                    return;
                }
                //movieViewModel =new ViewModelProvider(getActivity()).get(MovieViewModel.class);
                movieViewModel = new MovieViewModel(getActivity().getApplication());
                movieViewModel.getSearchMoviesMutableLiveData(searchMovies.getText().toString()).observe(getActivity(), new Observer<List<Movie>>() {
                    @Override
                    public void onChanged(List<Movie> movies) {
                        movieList = movies;
                        popularMoviesRecyclerViewAdapter.setMovieList(movieList);
                    }
                });


            }
        });


    }

    private void setPopularMovieRecyclerView() {
        popularMovieRecyclerView = view.findViewById(R.id.popularMovies);
        Log.d(TAG, "setPopularMovieRecyclerView: Movie" + movieList.size());
        popularMoviesRecyclerViewAdapter = new PopularMoviesRecyclerViewAdapter(movieList, getContext());
        popularMovieRecyclerView.setAdapter(popularMoviesRecyclerViewAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        popularMovieRecyclerView.setLayoutManager(linearLayoutManager);
        popularMovieRecyclerView.hasFixedSize();

    }

    private void observePopularMovies() {


        // movieViewModel =new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        movieViewModel = new MovieViewModel(getActivity().getApplication());
        movieViewModel.getPopularMoviesMutableLiveData().observe(getActivity(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                Log.d(TAG, "onChanged: movieModel " + movies.size());
                movieList = movies;
                movieListTemp = movies;
                popularMoviesRecyclerViewAdapter.setMovieList(movieList);
            }
        });

    }

}