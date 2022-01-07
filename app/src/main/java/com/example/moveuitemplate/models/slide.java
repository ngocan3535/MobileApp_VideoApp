package com.example.moveuitemplate.models;

import android.transition.Slide;

public class slide {
    private int imame;
    private String Title;
    //Add more field if you want

    public slide(int imame, String title) {
        this.imame = imame;
        Title = title;
    }

    public int getImage() {
        return imame;
    }

    public String getTitle() {
        return Title;
    }

    public void setImame(int imame) {
        this.imame = imame;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
