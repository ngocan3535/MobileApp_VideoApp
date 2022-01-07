package com.example.moveuitemplate.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.moveuitemplate.R;
import com.example.moveuitemplate.models.slide;
import com.example.moveuitemplate.ui.MainActivity;
import com.example.moveuitemplate.ui.MoviePlayerActivity;
import com.google.android.exoplayer2.util.Log;

import java.util.List;

public class SlidePagerAdapter extends PagerAdapter {

    private Context mcontext;
    private List<slide> mList;

    MovieItemClickListener movieItemClickListener;


    public SlidePagerAdapter(Context mcontext, List<slide> mList) {
        this.mcontext = mcontext;
        this.mList = mList;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View slideLayout = inflater.inflate(R.layout.slider_item, null);

        ImageView slideImg = slideLayout.findViewById(R.id.slide_img);
        TextView slideText = slideLayout.findViewById(R.id.slide_title);
        slideImg.setImageResource(mList.get(position).getImage());
        slideText.setText(mList.get(position).getTitle());



        slideLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG", "This page was clicked: " + position);
                Intent intent = new Intent(mcontext, MoviePlayerActivity.class);
                startUpdate(container);
            }
        });

        container.addView(slideLayout);
        return slideLayout;

    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
