package com.example.moveuitemplate.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.moveuitemplate.R;
import com.example.moveuitemplate.adapters.SectionsPagerAdapter;
import com.example.moveuitemplate.fragment.Frag1_Adventure;
import com.example.moveuitemplate.fragment.Frag2_Horror;
import com.example.moveuitemplate.fragment.Frag3_Comedy;
import com.example.moveuitemplate.fragment.Frag4_Documentary;
import com.example.moveuitemplate.fragment.Frag5_History;
import com.google.android.material.tabs.TabLayout;

public class TheLoaiActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private SectionsPagerAdapter adapter;

    String id_user = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        Intent intent = getIntent();
        id_user = intent.getStringExtra("UserID");


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);//bar
        viewPager = (ViewPager) findViewById(R.id.viewPage_id); //bar
        adapter = new SectionsPagerAdapter(getSupportFragmentManager());

//        adapter.AddFragment(new Frag1_Adventure(), "ADVENTURE");
//        adapter.AddFragment(new Frag2_Horror(), "HORROR");
//        adapter.AddFragment(new Frag3_Comedy(), "COMEDY");
//        adapter.AddFragment(new Frag4_Documentary(), "DOCUMENTARY");
//        adapter.AddFragment(new Frag5_History(), "HISTORY");

        Frag1_Adventure f1 = new Frag1_Adventure();
        Frag2_Horror f2 = new Frag2_Horror();
        Frag3_Comedy f3 = new Frag3_Comedy();
        Frag4_Documentary f4 = new Frag4_Documentary();
        Frag5_History f5 = new Frag5_History();

        Bundle bundle = new Bundle();
        bundle.putString("userID", id_user);
        // set MyFragment Arguments
        f1.setArguments(bundle);
        f2.setArguments(bundle);
        f3.setArguments(bundle);
        f4.setArguments(bundle);
        f5.setArguments(bundle);

        adapter.AddFragment(f1, "ADVENTURE");
        adapter.AddFragment(f2, "HORROR");
        adapter.AddFragment(f3, "COMEDY");
        adapter.AddFragment(f4, "DOCUMENTARY");
        adapter.AddFragment(f5, "HISTORY");

        viewPager.setAdapter(adapter);//bar
        tabLayout.setupWithViewPager(viewPager);//bar

        viewPager.setAdapter(adapter);//bar
        tabLayout.setupWithViewPager(viewPager);//bar
    }
}
