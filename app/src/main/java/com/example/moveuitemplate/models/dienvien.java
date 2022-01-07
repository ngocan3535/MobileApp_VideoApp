package com.example.moveuitemplate.models;

import android.os.Parcel;
import android.os.Parcelable;

public class dienvien{
    private String name;
    private String profile_path;

    public dienvien(String name, String profile_path) {
        this.name = name;
        this.profile_path = profile_path;
    }

    public String getName()
    {
        return name;
    }
    public String getImages ()
    {
        return profile_path;
    }

}
