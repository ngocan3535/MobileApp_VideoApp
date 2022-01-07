package com.example.moveuitemplate.fragment;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moveuitemplate.R;
import com.example.moveuitemplate.adapters.MoviLikeAdapter;
import com.example.moveuitemplate.adapters.MovieItemClickListener;
import com.example.moveuitemplate.models.MovieModel;
import com.example.moveuitemplate.models.movie;
import com.example.moveuitemplate.request.Servicey;
import com.example.moveuitemplate.response.MovieSearchResponse;
import com.example.moveuitemplate.ui.MovieDetailActivity;
import com.example.moveuitemplate.utils.MovieApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.moveuitemplate.utils.DataSource.getPopularMovies;
import static com.example.moveuitemplate.utils.DataSource.getWeeMovies;

//Action movie
public class Frag3_Comedy extends Fragment implements MovieItemClickListener {

    View v;
    private RecyclerView MoviesRV;
    String id_user = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.action_layout, container, false);


        return v;
    }

    @Override
    public void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id_user = getArguments().getString("UserID");
        }
        GetRetrofitResponseComedy();
    }

    private void iniComedyMovies(List<MovieModel> movies) {
        //RecyclerView
        //init data

        MoviesRV = (RecyclerView) v.findViewById(R.id.Rv_action_movie);
        MoviLikeAdapter moviLikeAdapter = new MoviLikeAdapter(getContext(), getWeeMovies(movies), this);
        MoviesRV.setAdapter(moviLikeAdapter);
        MoviesRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    private void GetRetrofitResponseComedy() {
        MovieApi movieApi = Servicey.getMovieApi();
        Call<MovieSearchResponse> responseCall = movieApi.getComedy();
        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if (response.code() == 200){
                    List<MovieModel> movies = new ArrayList<>(response.body().getMovies());
                    iniComedyMovies(movies);
                }
                else
                {
                    try {
                        Log.v("Tag","Error" + response.errorBody().string());
                    }catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onMovieClick(movie movie, ImageView movieImageView) {
        //Hàm đưa ra thông tin chi tiết của phim

        Intent intent = new Intent(v.getContext(), MovieDetailActivity.class);
        //Gửi thông tin film đến detailActivity
        intent.putExtra("title", movie.getTitle());
        intent.putExtra("imgURL", movie.getThumbnail());
        intent.putExtra("imgCover", movie.getCoverPhoto());
        intent.putExtra("description", movie.getDescription());
        intent.putExtra("API", movie.getMovieID());

        //Tạo animation
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                (Activity) v.getContext(), movieImageView, "sharedName"
        );

        startActivity(intent, options.toBundle());
        //Test nếu click hoạt động
    }
}
