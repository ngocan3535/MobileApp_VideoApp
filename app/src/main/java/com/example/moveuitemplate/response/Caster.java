package com.example.moveuitemplate.response;

import com.example.moveuitemplate.models.dienvien;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Caster {
    @SerializedName("credits")
    @Expose()
    Map<String,List<Map<String,String>>> cast = new HashMap<>();

    public Map<String,List<Map<String,String>>> getCast() {
        return cast;
    }
}
