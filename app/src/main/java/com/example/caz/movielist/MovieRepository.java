package com.example.caz.movielist;

import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class MovieRepository {

    private ArrayList<Movie> movies;

    public MovieRepository() {
        this.movies = new ArrayList<>();
    }

    public MutableLiveData<ArrayList<Movie>> getMovies() {
        MutableLiveData<ArrayList<Movie>> liveDataList = new MutableLiveData<>();
        liveDataList.setValue(movies);
        return liveDataList;
    }

    public ArrayList<Movie> addMovie(Movie movie) {
        if(movie.getId() == Movie.NO_ID) {
            int movieIndex = movies.size();
            movie.setId(movieIndex);
            movies.add(movie);
        } else {
            movies.set(movie.getId(), movie);
        }

        return movies;
    }
}
