package com.example.megabest.Fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.megabest.Adapters.PopularMoviesRecyclerViewAdapter;
import com.example.megabest.R;
import com.example.megabest.Model.Movie;
import com.example.megabest.ViewModel.MovieViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class HomeFragment extends Fragment {



    private static final String ARG_PARAM1 = "param1";
    private static  String userName ;
    private static  int userid ;
    AppCompatButton searchButton ;

    private View view;
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView popularMovieRecyclerView;
    private TextView name;
    PopularMoviesRecyclerViewAdapter popularMoviesRecyclerViewAdapter;
    private TextInputEditText searchMovies ;
    private List<Movie> movieList = new ArrayList<>();
    private List<Movie> movieListTemp = new ArrayList<>();
    MovieViewModel movieViewModel;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {

    }



    public static HomeFragment newInstance(Bundle bundle) {
        HomeFragment fragment = new HomeFragment();
        boolean m = bundle==null;
        Log.d(TAG, "onCreate: bundle "+m);
        userName=bundle.get("Name").toString();
        userid=bundle.getInt("Id");
        //Bundle args = new Bundle();
      //  args.putString(ARG_PARAM1, param1);
       // args.putString(ARG_PARAM2, param2);
      //  fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* boolean m = getArguments()==null;
       // Log.d(TAG, "onCreate: "+m);

        if (getArguments() != null) {
          //  mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }*/

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        Bundle bundle =getArguments();
        //bundle.getString("Name");
      //  bundle.getInt("Id");
        setUserName();
       // getMovies();
        observePopularMovies();
        setPopularMovieRecyclerView();
        setSearchMovies();


        return view;
    }
    private void setUserName(){
        name=view.findViewById(R.id.helloName);
        name.setText("Hello "+userName+" ,");
    }
    private void setSearchMovies(){
        searchButton=view.findViewById(R.id.searchMoviesButton);
        searchMovies=view.findViewById(R.id.searchMovies);

            searchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (TextUtils.isEmpty(searchMovies.getText().toString())){
                        popularMoviesRecyclerViewAdapter.setMovieList(movieListTemp);
                        return;
                    }
                    movieViewModel =new ViewModelProvider(getActivity()).get(MovieViewModel.class);
                    movieViewModel.getSearchMoviesMutableLiveData(searchMovies.getText().toString()).observe(getActivity(), new Observer<List<Movie>>() {
                        @Override
                        public void onChanged(List<Movie> movies) {
                            movieList=movies;
                            popularMoviesRecyclerViewAdapter.setMovieList(movieList);
                        }
                    });


                }
            });


    }
    private void setPopularMovieRecyclerView(){
        popularMovieRecyclerView=view.findViewById(R.id.popularMovies);
        Log.d(TAG, "setPopularMovieRecyclerView: Movie"+movieList.size());
        popularMoviesRecyclerViewAdapter= new PopularMoviesRecyclerViewAdapter(movieList,getContext());
        popularMovieRecyclerView.setAdapter(popularMoviesRecyclerViewAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        popularMovieRecyclerView.setLayoutManager(linearLayoutManager );
        popularMovieRecyclerView.hasFixedSize();

    }
    private void observePopularMovies(){

        movieViewModel =new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        movieViewModel.getPopularMoviesMutableLiveData().observe(getActivity(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                movieList=movies;
                movieListTemp=movies;
                popularMoviesRecyclerViewAdapter.setMovieList(movieList);
            }
        });

    }
    ///// Testing Api
    /*private void getMovies(){
        MovieApi movieApi = RetrofitInstance.getMovieApi();
        searchMovies=view.findViewById(R.id.searchMovies);

        Call<MovieSearchResponse> responseCall = movieApi.getPopular(Credentials.apiKey,"1");
        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                //popularMoviesRecyclerViewAdapter.setMovieList(response.body().getMovieList());
               // Log.d(TAG, "onResponse: "+response.body().getMovieList().get(0).getOverview());
                movieList=response.body().getMovieList();
                popularMoviesRecyclerViewAdapter.setMovieList(movieList);
                Log.d(TAG, "onResponse: MOVIEEEEE "+movieList.get(7).getTitle());

                //Log.d(TAG, "onResponse: "+response.errorBody().toString());

            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {

            }
        });
       // Log.d(TAG, "onResponse: Movie size"+movieList.size());
        setPopularMovieRecyclerView();
    }*/
}