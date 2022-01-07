package com.example.moveuitemplate.ui;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moveuitemplate.R;
import com.example.moveuitemplate.adapters.MoviAdapter;
import com.example.moveuitemplate.adapters.MoviLikeAdapter;
import com.example.moveuitemplate.adapters.MovieItemClickListener;
import com.example.moveuitemplate.models.MovieModel;
import com.example.moveuitemplate.models.movie;
import com.example.moveuitemplate.request.Servicey;
import com.example.moveuitemplate.response.MovieSearchResponse;
import com.example.moveuitemplate.utils.MovieApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.moveuitemplate.utils.DataSource.getPopularMovies;

public class MovieLikeActivity extends AppCompatActivity implements MovieItemClickListener {

    private RecyclerView MoviesRVLike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_like);

        iniViews();
        GetRetrofitResponseLike();
    }

    private void iniViews() {

        MoviesRVLike = findViewById(R.id.Rv_movies_like);

    }

    private void iniPopularMovies(List<MovieModel> movies) {
        //RecyclerView
        //init data


        MoviLikeAdapter moviAdapter = new MoviLikeAdapter(this, getPopularMovies(movies),  this);
        MoviesRVLike.setAdapter(moviAdapter);
        MoviesRVLike.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    private void GetRetrofitResponseLike() {
        MovieApi movieApi = Servicey.getMovieApi();
        Call<MovieSearchResponse> responseCall = movieApi.getPopularMovie();
        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if (response.code() == 200){
                    List<MovieModel> movies = new ArrayList<>(response.body().getMovies());
                    iniPopularMovies(movies);
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

        Intent intent = new Intent(this, MovieDetailActivity.class);
        //Gửi thông tin film đến detailActivity
        intent.putExtra("title", movie.getTitle());
        intent.putExtra("imgURL", movie.getThumbnail());
        intent.putExtra("imgCover", movie.getCoverPhoto());
        intent.putExtra("description", movie.getDescription());
        intent.putExtra("API", movie.getMovieID());

        //Tạo animation
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                MovieLikeActivity.this, movieImageView, "sharedName"
        );

        startActivity(intent, options.toBundle());
        //Test nếu click hoạt động
    }
}
