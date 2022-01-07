package com.example.moveuitemplate.adapters;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.moveuitemplate.ui.MovieDetailActivity;
import com.bumptech.glide.Glide;
import com.example.moveuitemplate.R;
import com.example.moveuitemplate.models.movie;

import java.util.List;

public class MoviLikeAdapter extends RecyclerView.Adapter<MoviLikeAdapter.MyViewHolder> {
    Context context;
    List<movie> mData;
    MovieItemClickListener movieItemClickListener;

    public MoviLikeAdapter(Context context, List<movie> mData, MovieItemClickListener listener) {
        this.context = context;
        this.mData = mData;
        movieItemClickListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_movie_like, parent, false);
        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.TvTitle.setText(mData.get(position).getTitle());
//        holder.ImgMovie.setImageURI(Uri.parse("https://image.tmdb.org/t/p/w500/" + mData.get(position).getThumbnail()));
        Glide.with(context).load("https://image.tmdb.org/t/p/w500/" + mData.get(position).getThumbnail()).into( holder.ImgMovie);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{


        private TextView TvTitle;
        private ImageView ImgMovie;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            TvTitle = itemView.findViewById(R.id.item_movie_title_like);
            ImgMovie = itemView.findViewById(R.id.item_movie_img_like);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    movieItemClickListener.onMovieClick(mData.get(getAdapterPosition()), ImgMovie);


                }
            });


        }
    }
}
