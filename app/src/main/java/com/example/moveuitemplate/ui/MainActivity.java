package com.example.moveuitemplate.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.example.moveuitemplate.adapters.SectionsPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import com.google.android.material.tabs.TabLayout;

import android.app.ActivityOptions;
import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;


import com.example.moveuitemplate.adapters.MoviAdapter;
import com.example.moveuitemplate.adapters.MovieItemClickListener;
import com.example.moveuitemplate.R;
import com.example.moveuitemplate.adapters.SlidePagerAdapter;
import com.example.moveuitemplate.models.MovieModel;
import com.example.moveuitemplate.models.movie;
import com.example.moveuitemplate.models.slide;
import com.example.moveuitemplate.request.Servicey;
import com.example.moveuitemplate.response.MovieSearchResponse;
import com.example.moveuitemplate.utils.DataSource;
import com.example.moveuitemplate.utils.MovieApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.moveuitemplate.utils.DataSource.getPopularMovies;

public class MainActivity extends AppCompatActivity implements MovieItemClickListener {

    private List<slide> lstSlides;
    private ViewPager sliderpages;
    private TabLayout indicator;
    private RecyclerView MoviesRV, movieRVWeek;

    private ViewPager mViewPager; //bar
    private SectionsPagerAdapter mSectionsPagerAdapter; //bar
    MaterialSearchView searchView;
    Toolbar toolbar;
    Button btnXemPhimYeuThich;
    Button btnTheLoai;
    private FloatingActionButton play_fab2;

    //Dũng thêm
    Button btnTrangDieuHuong;
    String id_user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nhanDuLieu();
        iniViews();
        iniSlider();
        GetRetrofitResponseTop();
        GetRetrofitResponsePopular();
        EventButton();

        //toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);



    }

    //Bar start


    //Bar end;

    private void searchViewCode()
    {
        searchView= (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setSuggestions(getResources().getStringArray(R.array.query_suggetions));
        searchView.setEllipsize(true);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getApplicationContext(),"This is text", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        searchViewCode();
//        getMenuInflater().inflate(R.menu.my_menu,menu);
//        MenuItem item = menu.findItem(R.id.action_search);
//        searchView.setMenuItem(item);
//        GetRetrofitResponseSearch();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_search:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen())
            searchView.closeSearch();
        else
            super.onBackPressed();
    }

    private void iniWeekMovies(List<MovieModel> movies) {

        MoviAdapter weekMoviAdapter = new MoviAdapter(this, DataSource.getWeeMovies(movies), this);
        movieRVWeek.setAdapter(weekMoviAdapter);
        movieRVWeek.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


    }

    private void iniPopularMovies(List<MovieModel> movies) {
        //RecyclerView
        //init data


        MoviAdapter moviAdapter = new MoviAdapter(this, getPopularMovies(movies), this);
        MoviesRV.setAdapter(moviAdapter);
        MoviesRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void iniSlider() {
        //prepare a list of slide
        lstSlides = new ArrayList<>();
        lstSlides.add(new slide(R.drawable.anh1, "The Avergers : Endgame"));
        lstSlides.add(new slide(R.drawable.anh2, "Thor 2 : The Dark World "));
        lstSlides.add(new slide(R.drawable.anh3, "Venom 2"));
        lstSlides.add(new slide(R.drawable.anh4, "Fast and Furiour : Hobbs & Shaw "));

        SlidePagerAdapter adapter = new SlidePagerAdapter(this, lstSlides);

        sliderpages.setAdapter(adapter);

        //Cài đặt thời gian
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 4000, 6000);

        indicator.setupWithViewPager(sliderpages, true);
    }

    private void iniViews() {
        sliderpages = findViewById(R.id.slider_pager);
        indicator = findViewById(R.id.indicator);
        MoviesRV = findViewById(R.id.Rv_movies);
        movieRVWeek = findViewById(R.id.rv_movies_week);
        btnXemPhimYeuThich = findViewById(R.id.btn_favoriteFlim);
        btnTheLoai = findViewById(R.id.btn_theLoai);
        btnTrangDieuHuong = findViewById(R.id.btn_TrangDieuHuong);
        play_fab2 = findViewById(R.id.fa_playfab2);
    }

    @Override
    public void onMovieClick(movie movie, ImageView movieImageView) {
        //Hàm đưa ra thông tin chi tiết của phim

        Intent intent = new Intent(this, MovieDetailActivity.class);
        //Gửi thông tin film từ main đến detailActivity
        intent.putExtra("title", movie.getTitle());
        intent.putExtra("imgURL", movie.getThumbnail());
        intent.putExtra("imgCover", movie.getCoverPhoto());
        intent.putExtra("description", movie.getDescription());
        intent.putExtra("API", movie.getMovieID());
        intent.putExtra("userID", id_user);

        //Tạo animation
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                MainActivity.this, movieImageView, "sharedName"
        );

        startActivity(intent, options.toBundle());
        //Test nếu click hoạt động

    }

    //Chuyển trang theo thời gian
    class SliderTimer extends TimerTask{
        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(sliderpages.getCurrentItem() < lstSlides.size()- 1){
                        sliderpages.setCurrentItem(sliderpages.getCurrentItem()+1);
                    } else {
                        sliderpages.setCurrentItem(0);
                    }
                }
            });
        }
    }

    private void GetRetrofitResponsePopular() {
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
    private void GetRetrofitResponseTop() {
        MovieApi movieApi = Servicey.getMovieApi();
        Call<MovieSearchResponse> responseCall = movieApi.getTop();
        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if (response.code() == 200){
                    List<MovieModel> movies = new ArrayList<>(response.body().getMovies());
                    iniWeekMovies(movies);
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
    private void GetRetrofitResponseSearch(String movieName) {
        MovieApi movieApi = Servicey.getMovieApi();
        Call<MovieSearchResponse> responseCall = movieApi.getMovieSearch(movieName);
        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if (response.code() == 200){
                    List<MovieModel> movies = new ArrayList<>(response.body().getMovies());
                    iniWeekMovies(movies);//truyen thong tin phim sang detail
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

    //Sự kiện button

    private void EventButton(){

//        play_fab2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, MoviePlayerActivity.class);
//                startActivity(intent);
//            }
//        });

        btnXemPhimYeuThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

// -----Dũng comment phần của Phát, code của Dũng ở dưới----
//                Intent intent = new Intent(MainActivity.this,MovieLikeActivity.class);
////                intent.putExtra("TenNguoiDung", edtUserYeuThich.getText().toString().replaceAll("\\s+",""));
//                startActivity(intent);
////                setContentView(R.layout.phim_yeu_thich);


                //Phần Dũng thêm
                Intent intent = new Intent(MainActivity.this,PhimYeuThichActivity.class);
                intent.putExtra("TenNguoiDung", id_user);
                startActivity(intent);


            }
        });

        btnTheLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TheLoaiActivity.class);
                intent.putExtra("UserID", id_user);
                startActivity(intent);
            }
        });

        btnTrangDieuHuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Truyền id_user từ Main->DashboardActivity
                Intent intent = new Intent(MainActivity.this, DashBoardActivity.class);
                intent.putExtra("UserString", id_user);
                startActivity(intent);
            }
        });

    }
    private void nhanDuLieu() {
        Intent intent = getIntent();
        id_user = intent.getStringExtra("UserString");
    }
}