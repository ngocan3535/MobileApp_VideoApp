package com.example.moveuitemplate.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.moveuitemplate.R;
import com.example.moveuitemplate.adapters.DienVienAdapter;
import com.example.moveuitemplate.models.MovieModel;
import com.example.moveuitemplate.models.Phim;
import com.example.moveuitemplate.models.dienvien;
import com.example.moveuitemplate.models.movie;
import com.example.moveuitemplate.request.Servicey;
import com.example.moveuitemplate.response.Caster;
import com.example.moveuitemplate.response.MovieSearchResponse;
import com.example.moveuitemplate.utils.CheckConnection;
import com.example.moveuitemplate.utils.HttpParse;
import com.example.moveuitemplate.utils.MovieApi;
import com.example.moveuitemplate.utils.Server;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends AppCompatActivity {

    private ImageView MovieThumbnailImg, MovieCoverImg;
    private TextView tv_title, tv_description;
    private FloatingActionButton play_fab;
    private RecyclerView Rv_dienvien;
    private DienVienAdapter dienVienAdapter;
    private List<dienvien> cast = new ArrayList<>();


    String movieTitle = "";
    String imageResourceId = "";
    String imageCover = "";
    String overViewMovie = "";
    String movie_API = "";

    String id_user = "";
    HashMap<String,String> hashMap = new HashMap<>();
    CheckBox cbLike;
    HttpParse httpParse = new HttpParse();
    Boolean likeStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);


        //khởi tạo view
        initView();
        getFavoriteFilmFunction(id_user, movie_API);
        EventButton();


    }

    void initView(){
        cbLike = findViewById(R.id.cb_like);

        Rv_dienvien = findViewById(R.id.rv_dienvien);
        play_fab = findViewById(R.id.play_fab);
        movieTitle = getIntent().getExtras().getString("title");
        imageResourceId = getIntent().getExtras().getString("imgURL");
        imageCover = getIntent().getExtras().getString("imgCover");
        overViewMovie = getIntent().getExtras().getString("description");
        movie_API = getIntent().getExtras().getString("API");

        //Đang bí
        id_user = getIntent().getStringExtra("userID");


        movieTitle = movieTitle.replaceAll("'", "");
        overViewMovie = overViewMovie.replaceAll("'", "");


        GetRetrofitResponseCaster(movie_API);
        MovieThumbnailImg = findViewById(R.id.detail_movie_img);
        Glide.with(this).load("https://image.tmdb.org/t/p/w500/" + imageResourceId).into(MovieThumbnailImg);

        MovieCoverImg = findViewById(R.id.detail_movie_cover);
        Glide.with(this).load("https://image.tmdb.org/t/p/w500/" + imageCover).into(MovieCoverImg);

        tv_title = findViewById(R.id.detail_movie_title);
        tv_title.setText(movieTitle);
        getSupportActionBar().setTitle(movieTitle);

        tv_description = findViewById(R.id.detail_movie_desc);
        tv_description.setText(overViewMovie);


        //Cài đặt animation
        MovieCoverImg.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));
        play_fab.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));


        StringBuilder stringBuilder = new StringBuilder();
        //stringBuilder.append("\tId user: " + id_user);
        stringBuilder.append("\tID film: " + movie_API);
        stringBuilder.append("\tTitle: " + movieTitle);
        stringBuilder.append("\tImg: " + imageResourceId);
        stringBuilder.append("\tImg: " + imageResourceId);

        Log.d(" - Infomation: ", stringBuilder.toString());

    }

    //Hàm show diễn viên
    void setupRvCast(){
        dienVienAdapter = new DienVienAdapter(this, cast);
        Rv_dienvien.setAdapter(dienVienAdapter);
        Rv_dienvien.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

    }
    private void GetRetrofitResponseCaster(String movie_API) {
        MovieApi movieApi = Servicey.getMovieApi();
        Call<Caster> responseCall = movieApi.getMovieDetail(movie_API);
        responseCall.enqueue(new Callback<Caster>() {
            @Override
            public void onResponse(Call<Caster> call, Response<Caster> response) {
                if (response.code() == 200){
                    Map<String,List<Map<String,String>>> casters = response.body().getCast();
                    //casters.get("cast")
                    for (Map<String,String> info: casters.get("cast")) {
                        dienvien castInfo = new dienvien(info.get("name"),info.get("profile_path"));
                        cast.add(castInfo);

                    }

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
                setupRvCast();
            }

            @Override
            public void onFailure(Call<Caster> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @SuppressLint("LongLogTag")
    private void EventButton(){

        play_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieDetailActivity.this, MoviePlayerActivity.class);
                startActivity(intent);
            }
        });


        cbLike.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    insertFavoriteFilmFunction(id_user, movie_API);
                    Log.d("Kiem tra yeu thich cbLike.setOnCheckedChangeListener: ", likeStatus.toString());
                }
                else {
                    deleteFavoriteFilmFunction(id_user, movie_API);
                    Log.d("Kiem tra yeu thich cbLike.setOnCheckedChangeListener: ", likeStatus.toString());
                }
            }
        });

    }


    @SuppressLint("LongLogTag")
    public void getFavoriteFilmFunction(String userID, String filmID) {
        class GetFavoriteFilmClass extends AsyncTask<String, Void, String> {

            @Override
            protected void onPostExecute(String httpResponseMsg) {
                super.onPostExecute(httpResponseMsg);

                if(httpResponseMsg.equalsIgnoreCase("YES")) {
                    likeStatus = true;  //Có trong danh sách yêu thích
                    Log.d("Kiem tra likeStatus: ", " *** [True]");
                }
                else {
                    likeStatus = false;
                    Log.d("Kiem tra likeStatus: ", " *** [False]");
                }

                if (likeStatus == true) {
                    cbLike.setChecked(true);
                    Log.d("Kiem tra cb.setChecked: ", " *** [True]");
                }
                else {
                    cbLike.setChecked(false);
                    Log.d("Kiem tra cb.setChecked: ", " *** [False]");
                }
            }

            @Override
            protected String doInBackground(String... params) {
                //gửi IDUser và IDFilm qua $_POST
                hashMap.put("tendangnhap",params[0]);
                hashMap.put("idphim",params[1]);

                String result = httpParse.postRequest(hashMap, Server.ulrGetLineFavoriteFilm);
                return result;
            }
        }

        GetFavoriteFilmClass getFavoriteFilmClass = new GetFavoriteFilmClass();
        getFavoriteFilmClass.execute(id_user, filmID);

    }

    public void deleteFavoriteFilmFunction(final String iduser, final String idphim){

        class DeleteFavoriteFilmClass extends AsyncTask<String,Void,String> {

            @SuppressLint("LongLogTag")
            @Override
            protected void onPostExecute(String httpResponseMsg) {
                super.onPostExecute(httpResponseMsg);
                Log.d("*** Trang thai: ", httpResponseMsg);
                Toast.makeText(MovieDetailActivity.this, httpResponseMsg, Toast.LENGTH_SHORT).show();

            }


            @SuppressLint("LongLogTag")
            @Override
            protected String doInBackground(String... params) {

                //gửi IDUser và IDFilm qua $_POST
                hashMap.put("id_user",params[0]);
                hashMap.put("id_film",params[1]);

                String finalResult = httpParse.postRequest(hashMap, Server.urlDeleteFavoriteFilm);

                Log.d(" - Kiem tra finalResult trong DeleteFavoriteFilmClass - MovieDetailActivity", finalResult);

                return finalResult;
            }
        }

        DeleteFavoriteFilmClass deleteFavoriteFilmClass= new DeleteFavoriteFilmClass();

        deleteFavoriteFilmClass.execute(iduser, String.valueOf(idphim));
    }

    private void insertFavoriteFilmFunction(String id_user, String idphim) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.urlInsertFavoriteFilm, new com.android.volley.Response.Listener<String>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(String response) {
                Log.d("***! Trang thai", response);
                Toast.makeText(MovieDetailActivity.this, response, Toast.LENGTH_SHORT).show();
            }
        }, new com.android.volley.Response.ErrorListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onErrorResponse(VolleyError error) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("ID film: " + movie_API);
                stringBuilder.append("\tTitle: " + movieTitle);
                stringBuilder.append("\tImg: " + imageResourceId);
                Log.d(" - Infomation2: ", stringBuilder.toString());

                Log.d("Trang thai onErrorRespone: ", " *** Kiểm tra lại kết nối nha~~~");
                Toast.makeText(MovieDetailActivity.this, "Kiểm tra lại kết nối nha ~~~", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("id_user", id_user);
                hashMap.put("id_phim", idphim);
                hashMap.put("ten_phim", movieTitle);
                hashMap.put("hinh_anh_phim", imageResourceId);
                return hashMap;
            }
        };
        requestQueue.add(stringRequest);



    }


}