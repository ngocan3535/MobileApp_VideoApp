package com.example.moveuitemplate.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moveuitemplate.R;
import com.example.moveuitemplate.models.Phim;
import com.example.moveuitemplate.ui.PhimYeuThichActivity;

import java.util.ArrayList;

public class PhimYeuThichAdapter extends BaseAdapter {

    Context context;
    ArrayList<Phim> arrayPhim;

    public PhimYeuThichAdapter(Context context, ArrayList<Phim> arrayPhim) {
        this.context = context;
        this.arrayPhim = arrayPhim;
    }

    @Override
    public int getCount() {
        return arrayPhim.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayPhim.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        public TextView  tvTenPhim;
        public ImageView ivImgPhim;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_phimyeuthich, null);
            viewHolder.tvTenPhim = view.findViewById(R.id.tv_tenphim);
            viewHolder.ivImgPhim = view.findViewById(R.id.item_movie_img_like);

            view.setTag(viewHolder);

        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Phim phim = (Phim) getItem(i);
        String urlImg = phim.getThumbnail();
        Glide.with(context).load("https://image.tmdb.org/t/p/w500/" + urlImg).into(viewHolder.ivImgPhim);
        viewHolder.tvTenPhim.setText(phim.getTenPhim());



        return view;
    }


}