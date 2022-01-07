package com.example.moveuitemplate.models;

import android.util.Log;

public class movie {

    private String title;
    private String description;
    private String thumbnail;
    private String studio;
    private String rating;
    private String streamingLink;
    private String coverPhoto;
    private String movieID;

    public movie(String title, String thumbnail, String coverPhoto,String overView,String movieID) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.coverPhoto = coverPhoto;
        this.description = overView;
        this.movieID = movieID;
    }

    public movie(String title, String thumbnail,String movieID) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.movieID = movieID;
    }

    public movie(String title, String description, String thumbnail, String studio, String rating, String streamingLink,String movieID) {
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.studio = studio;
        this.rating = rating;
        this.streamingLink = streamingLink;
        this.movieID = movieID;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getThumbnail() {
        return thumbnail;
    }
    public String getMovieID() {return movieID;};

    public String getStudio() {
        return studio;
    }

    public String getRating() {
        return rating;
    }

    public String getStreamingLink() {
        return streamingLink;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setStreamingLink(String streamingLink) {
        this.streamingLink = streamingLink;
    }
}
