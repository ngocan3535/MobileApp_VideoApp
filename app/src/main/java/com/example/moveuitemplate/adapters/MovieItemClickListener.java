package com.example.moveuitemplate.adapters;

import android.widget.ImageView;

import com.example.moveuitemplate.models.movie;

public interface MovieItemClickListener {

    void onMovieClick(movie movie, ImageView movieImageView); //Cần imageview để tạo animation giữa 2 activity



}
