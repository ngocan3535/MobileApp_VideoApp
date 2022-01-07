package com.example.moveuitemplate.utils;

import android.util.Log;

import com.example.moveuitemplate.R;
import com.example.moveuitemplate.models.MovieModel;
import com.example.moveuitemplate.models.movie;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

    public static List<movie> getPopularMovies(List<MovieModel> movies){
        List<movie> lstMovies = new ArrayList<>();
        for (MovieModel movie : movies) {

            lstMovies.add(new movie(movie.getTitle(), movie.getPoster_path(), movie.getBackdrop_path(),movie.getOverview(),movie.getMovieID()));

        }
        return lstMovies;
    }


    public static List<movie> getWeeMovies(List<MovieModel> movies){
        List<movie> lstMovies = new ArrayList<>();
//        lstMovies.add(new movie("SOUL", R.drawable.film3, R.drawable.soulcover));
//        lstMovies.add(new movie("DORAEMON", R.drawable.film4, R.drawable.doremoncover));
//        lstMovies.add(new movie("ONWARD", R.drawable.film5, R.drawable.onwardcover));
//        lstMovies.add(new movie("Cuộc phiêu lưu của SGOOBY - DOO", R.drawable.film1, R.drawable.scoobydoocover));
//        lstMovies.add(new movie("Vương quốc bánh kẹo", R.drawable.film2, R.drawable.vuongquocbanhkeocover));

//        return lstMovies;

        for (MovieModel movie : movies) {

            lstMovies.add(new movie(movie.getTitle(), movie.getPoster_path(), movie.getBackdrop_path(),movie.getOverview(),movie.getMovieID()));

        }
        return lstMovies;
    }


}
